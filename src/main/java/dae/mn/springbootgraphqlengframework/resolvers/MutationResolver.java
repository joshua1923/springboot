package dae.mn.springbootgraphqlengframework.resolvers;

import dae.mn.springbootgraphqlengframework.comment.Comment;
import dae.mn.springbootgraphqlengframework.comment.CommentResource;
import dae.mn.springbootgraphqlengframework.exceptions.ServiceException;
import dae.mn.springbootgraphqlengframework.post.Post;
import dae.mn.springbootgraphqlengframework.post.PostResource;
import dae.mn.springbootgraphqlengframework.user.User;
import dae.mn.springbootgraphqlengframework.user.UserResource;
import graphql.kickstart.tools.GraphQLMutationResolver;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final UserResource userResource;
    private final PostResource postResource;
    private final CommentResource commentResource;


    @Autowired
    public MutationResolver(UserResource userResource, PostResource postResource, CommentResource commentResource) {
        this.userResource = userResource;
        this.postResource = postResource;
        this.commentResource = commentResource;
    }

    public User createUser(String email, String userName, String password) throws ServiceException {
        try {
            return userResource.createUser(email, userName, password);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Post createPost(String body, String userID) {
        try {
            return postResource.createPost(body, userID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public String deletePost(UUID postID) {
        try {
            return postResource.deletePost(postID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Post updatePost(UUID postID, String body) {
        try {
            return postResource.updatePost(postID, body);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Comment createComment(String body, String postID, String userID) {
        try {
            return commentResource.createComment(body, postID, userID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public String deleteComment(String commentID) {
        try {
            return commentResource.deleteComment(commentID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Comment updateComment(String body, String commentID) {
        try {
            return commentResource.updateComment(body, commentID);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public String followUser(String userIDOwner, String userIDFollow) {
        try {
            return userResource.followUser(userIDOwner, userIDFollow);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public String unfollowUser(String userIDOwner, String userIDFollow) {
        try {
            return userResource.unfollowUser(userIDOwner, userIDFollow);
        } catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }
}
