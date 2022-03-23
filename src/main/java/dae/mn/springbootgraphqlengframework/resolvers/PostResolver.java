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

import java.util.List;

@Component
public class PostResolver implements GraphQLResolver<Post> {

    private final UserResource userResource;
    private final CommentResource commentResource;
    private final PostResource postResource;

    @Autowired
    public PostResolver(UserResource userResource, CommentResource commentResource, PostResource postResource) {
        this.userResource = userResource;
        this.commentResource = commentResource;
        this.postResource = postResource;
    }

    public List<Comment> feedComments(Post post) {
        try {
            return commentResource.getPostComments(post.getPostID().toString());
        }
        catch(StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public User getUserInfoFromPost(Post post) {
        try {
            return userResource.getUserInfo(post.getUserID().toString());
        }
        catch(StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Comment createComment(Post post, String body) throws ServiceException {
        try{
            return commentResource.createComment(body, post.getPostID().toString(), post.getUserID());
        }
        catch(StatusRuntimeException exception){
            throw new ServiceException(exception.getMessage());
        }
    }
}
