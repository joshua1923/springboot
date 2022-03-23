package dae.mn.springbootgraphqlengframework.post;

import java.util.UUID;

public class Post {
    public static class Builder{

        private UUID postID;
        private String body;
        private Long createdAt;
        private String userID;

        public Builder setPostID(UUID postID) {
            this.postID = postID;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public Post build() {
            return new Post(postID, body, createdAt, userID);
        }
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    private final UUID postID;
    private final String body;
    private final Long createdAt;
    private final String userID;

    private Post(UUID postID, String body, Long createdAt, String userID) {
        this.postID = postID;
        this.body = body;
        this.createdAt = createdAt;
        this.userID = userID;
    }

    public UUID getPostID() {
        return postID;
    }

    public String getBody() {
        return body;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public String getUserID() {
        return userID;
    }
}