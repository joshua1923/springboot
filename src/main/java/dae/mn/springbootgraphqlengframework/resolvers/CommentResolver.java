package dae.mn.springbootgraphqlengframework.resolvers;

import dae.mn.springbootgraphqlengframework.comment.Comment;
import dae.mn.springbootgraphqlengframework.comment.CommentResource;
import dae.mn.springbootgraphqlengframework.exceptions.ServiceException;
import dae.mn.springbootgraphqlengframework.post.Post;
import dae.mn.springbootgraphqlengframework.post.PostResource;
import dae.mn.springbootgraphqlengframework.user.User;
import dae.mn.springbootgraphqlengframework.user.UserResource;
import graphql.kickstart.tools.GraphQLResolver;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentResolver implements GraphQLResolver<Comment> {

    private final UserResource userResource;
    private final CommentResource commentResource;
    private final PostResource postResource;

    @Autowired
    public CommentResolver(UserResource userResource, CommentResource commentResource, PostResource postResource) {
        this.userResource = userResource;
        this.commentResource = commentResource;
        this.postResource = postResource;
    }

    public Post getPostInfo(Comment comment) {
        try {
            return postResource.requestPost(UUID.fromString(comment.getPostID()));
        }
        catch(StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public User getUserInfoFromComment(Comment comment) {
        try {
            return userResource.getUserInfo(comment.getUserID());
        }
        catch(StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }
}
