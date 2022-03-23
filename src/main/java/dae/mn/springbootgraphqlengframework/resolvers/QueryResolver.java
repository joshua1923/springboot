package dae.mn.springbootgraphqlengframework.resolvers;

import dae.mn.springbootgraphqlengframework.comment.Comment;
import dae.mn.springbootgraphqlengframework.comment.CommentResource;
import dae.mn.springbootgraphqlengframework.exceptions.ServiceException;
import dae.mn.springbootgraphqlengframework.post.Post;
import dae.mn.springbootgraphqlengframework.post.PostResource;
import dae.mn.springbootgraphqlengframework.user.User;
import dae.mn.springbootgraphqlengframework.user.UserResource;
import graphql.kickstart.tools.GraphQLQueryResolver;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final UserResource userResource;
    private final CommentResource commentResource;
    private final PostResource postResource;

    @Autowired
    public QueryResolver(UserResource userResource, CommentResource commentResource, PostResource postResource) {
        this.userResource = userResource;
        this.commentResource = commentResource;
        this.postResource = postResource;
    }

    public User getUserInfo(String userId) throws ServiceException {
        try {
            return userResource.getUserInfo(userId);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Comment getCommentInfo(String commentID) {
        try {
            return commentResource.getComment(commentID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public List<Post> listAllPosts() {
        try {
            return postResource.listAllPosts();
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public List<Post> listPostsByUser(String userID) {
        try {
            return postResource.listPostsByUserID(userID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Post requestPost(UUID postID) {
        try {
            return postResource.requestPost(postID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public List<String> getFollows(String userID) {
        try {
            return userResource.follows(userID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public List<Post> ownerPosts(User user) {
        try {
            return postResource.listPostsByUserID(user.getUserid());
        }
        catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }


}
