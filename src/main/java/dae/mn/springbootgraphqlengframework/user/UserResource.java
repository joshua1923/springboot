package dae.mn.springbootgraphqlengframework.user;

import dae.mn.springbootgraphqlengframework.security.KetoService;
import daemn.userservice.grpc.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserResource {

    @GrpcClient("users")
    protected UserGrpc.UserBlockingStub userBlockingStub;

    public User getUserInfo(String userId) {
        GetInfoResponse response = userBlockingStub.getInfo(GetInfoRequest.newBuilder()
                .setUserid(userId)
                .build());
        return buildUser(
                response.getEmail(),
                response.getUsername(),
                response.getPassword(),
                response.getUserid(),
                new ArrayList<>(response.getFollowsList()));
    }

    public User createUser(String email , String username, String password) {

        KetoService ketoService = new KetoService();

        ketoService.createRelation("videos", "/cats/1.mp4", "view", "*");

        CreateUserResponse response = userBlockingStub.createUser(CreateUserRequest.newBuilder()
                .setEmail(email)
                .setUsername(username)
                .setPassword(password)
                .build());
        return buildUser(response.getEmail(),response.getUsername(), response.getPassword(), response.getUserid(), new ArrayList<>());
    }

    public String followUser(String userIdOwner, String userIdFollow) {
        FollowUserResponse response = userBlockingStub.followUser(FollowUserRequest.newBuilder()
                .setUseridOwner(userIdOwner)
                .setUseridFollow(userIdFollow)
                .build());
        return response.getUseridFollow();
    }

    public String unfollowUser(String userIdOwner, String userIdUnfollow) {
        UnfollowUserResponse response = userBlockingStub.unfollowUser(UnfollowUserRequest.newBuilder()
                .setUseridOwner(userIdOwner)
                .setUseridUnfollow(userIdUnfollow)
                .build());
        return response.getUseridUnfollow();
    }

    public List<String> follows(String userIdOwner) {
        FollowsResponse response = userBlockingStub.follows(FollowsRequest.newBuilder()
                .setUseridOwner(userIdOwner)
                .build());
        return response.getFollowsList();
    }

    private User buildUser(String email, String userName, String password, String userId, ArrayList<String> follows) {
        return User.newBuilder()
                .setEmail(email)
                .setUsername(userName)
                .setPassword(password)
                .setUserid(userId)
                .setFollows(follows)
                .build();
    }
}
