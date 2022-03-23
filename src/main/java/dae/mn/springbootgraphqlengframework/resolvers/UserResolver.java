package dae.mn.springbootgraphqlengframework.resolvers;

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
import java.util.stream.Collectors;

@Component
public class UserResolver implements GraphQLResolver<User> {

    private final UserResource userResource;
    private final CommentResource commentResource;
    private final PostResource postResource;

    @Autowired
    public UserResolver(UserResource userResource, CommentResource commentResource, PostResource postResource) {
        this.userResource = userResource;
        this.commentResource = commentResource;
        this.postResource = postResource;
    }

    public List<Post> ownerPosts(User user) {
        try {
            return postResource.listPostsByUserID(user.getUserid());
        }
        catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public List<Post> followsPosts(User user) {
        try {
            List<List<Post>> followsPosts = user.getFollows()
                    .stream()
                    .map(u -> postResource.listPostsByUserID(u))
                    .collect(Collectors.toList());
            return followsPosts.stream().flatMap(List::stream).collect(Collectors.toList());
        }
        catch (StatusRuntimeException exception) {
            throw new ServiceException(exception.getMessage());
        }
    }

    public Post createPost(User user, String body) throws ServiceException {
        try{
            return postResource.createPost(body, user.getUserid());
        }
        catch(StatusRuntimeException exception){
            throw new ServiceException(exception.getMessage());
        }
    }

}
