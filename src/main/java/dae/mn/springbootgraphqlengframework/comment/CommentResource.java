package dae.mn.springbootgraphqlengframework.comment;

import daemn.commentsService.grpc.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CommentResource {

    @GrpcClient("comments")
    protected CommentServiceGrpc.CommentServiceBlockingStub commentServiceBlockingStub;

    public Comment createComment(String body, String postID, String userID){
        CreateCommentResponse response = commentServiceBlockingStub.createComment(CreateCommentRequest.newBuilder()
                .setBody(body)
                .setCommentID(UUID.randomUUID().toString())
                .setPostID(postID)
                .setUserID(userID)
                .build());
        return Comment.newBuilder()
                .setUserID(response.getUserID())
                .setPostID(response.getPostID())
                .setCommentID(response.getCommentID())
                .setBody(response.getBody())
                .build();
    }
    public Comment updateComment(String body, String commentID){
        UpdateCommentResponse response = commentServiceBlockingStub.updateComment(UpdateCommentRequest.newBuilder()
                .setCommentID(commentID)
                .setBody(body)
                .build());
        return Comment.newBuilder()
                .setPostID(response.getPostID())
                .setUserID(response.getUserID())
                .setBody(response.getBody())
                .setCommentID(response.getCommentID())
                .build();
    }
    public Comment getComment(String commentID){
        GetCommentResponse response = commentServiceBlockingStub.getComment(GetCommentRequest.newBuilder()
                .setCommentID(commentID)
                .build());
        return Comment.newBuilder()
                .setPostID(response.getPostID())
                .setUserID(response.getUserID())
                .setBody(response.getBody())
                .setCommentID(response.getCommentID())
                .build();
    }
    public String deleteComment(String commentID){
        DeleteCommentResponse response = commentServiceBlockingStub.deleteComment(DeleteCommentRequest.newBuilder()
                .setCommentID(commentID)
                .build());
        return "Comment has been deleted.";
    }

    public List<Comment> getPostComments(String postID){
        GetPostCommentsResponse response = commentServiceBlockingStub
                .getPostComments(GetPostCommentsRequest.newBuilder()
                        .setPostID(postID)
                        .build());
        return response.getGetCommentResponseList()
                .stream()
                .map(CommentResource::toComment)
                .collect(Collectors.toList());
    }

    private static Comment toComment(GetCommentResponse response){
        return Comment.newBuilder()
                .setBody(response.getBody())
                .setCommentID(response.getCommentID())
                .setPostID(response.getPostID())
                .setUserID(response.getUserID())
                .build();
    }
}

