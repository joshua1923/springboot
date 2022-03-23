package dae.mn.springbootgraphqlengframework.post;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import daemn.postService.grpc.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.protobuf.util.Timestamps.toMillis;

@Component
public class PostResource {

    @GrpcClient("posts")
    protected PostServiceGrpc.PostServiceBlockingStub postServiceBlockingStub;

    public Post createPost(String body, String userID) {
        CreatePostResponse response = postServiceBlockingStub.createPost(CreatePostRequest.newBuilder()
                .setBody(body)
                .setUserID(userID.toString())
                .build());
        return buildPost((UUID.fromString(response.getUserID()).toString()),
                response.getBody(),
                toMillis(response.getCreatedAt()),
                UUID.fromString(response.getPostID()));
    }

    public String deletePost(UUID postID) {
        postServiceBlockingStub.deletePost(DeletePostRequest.newBuilder()
                .setPostID(postID.toString())
                .build());
        return "Post deletion successful.";
    }

    public Post updatePost(UUID postID, String body) {
        UpdatePostResponse response = postServiceBlockingStub.updatePost(UpdatePostRequest.newBuilder()
                .setBody(body)
                .setPostID(postID.toString())
                .build());

        return buildPost((UUID.fromString(response.getUserID()).toString()),
                response.getBody(),
                toMillis(response.getCreatedAt()),
                UUID.fromString(response.getPostID()));
    }

    public Post requestPost(UUID postID) {
        RequestPostResponse response = postServiceBlockingStub.requestPost(RequestPostRequest.newBuilder()
                .setPostID(postID.toString())
                .build());

        return buildPost((UUID.fromString(response.getUserID()).toString()),
                response.getBody(),
                toMillis(response.getCreatedAt()),
                UUID.fromString(response.getPostID()));
    }

    public List<Post> listPostsByUserID(String userID) {
        ListPostsByUserResponse response = postServiceBlockingStub.listPostsByUser(ListPostsByUserRequest.newBuilder()
                .setUserID(userID.toString())
                .build());
        return response.getRequestPostResponseList()
                .stream()
                .map(PostResource::toPost)
                .collect(Collectors.toList());
    }

    public List<Post> listAllPosts() {
        ListAllPostsResponse response = postServiceBlockingStub.listAllPosts(Empty.newBuilder().build());
        return response.getRequestPostResponseList()
                .stream()
                .map(PostResource::toPost)
                .collect(Collectors.toList());
    }

    private static RequestPostResponse toRequestPostResponse(Post post){
        return RequestPostResponse.newBuilder()
                .setBody(post.getBody())
                .setPostID((post.getPostID()).toString())
                .setUserID((post.getUserID()).toString())
                .setCreatedAt(Timestamp
                        .newBuilder()
                        .setSeconds((post.getCreatedAt()) / 1000)
                        .build())
                .build();
    }

    private Post buildPost(String userID, String body, Long createdAt, UUID postID) {
        return Post.newBuilder()
                .setUserID(userID)
                .setBody(body)
                .setCreatedAt(createdAt)
                .setPostID(postID)
                .build();
    }

    private static Post toPost(RequestPostResponse response){
        return Post.newBuilder()
                .setPostID(UUID.fromString(response.getPostID()))
                .setCreatedAt(toMillis(response.getCreatedAt()))
                .setBody(response.getBody())
                .setUserID((UUID.fromString(response.getUserID())).toString())
                .build();
    }

}
