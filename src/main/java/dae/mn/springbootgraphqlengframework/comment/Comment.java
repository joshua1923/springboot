package dae.mn.springbootgraphqlengframework.comment;

public class Comment {
    private String commentID;
    private String userID;
    private String postID;
    private String body;

    public static class Builder{

        private String commentID;
        private String userID;
        private String postID;
        private String body;

        public Builder setCommentID(String commentID) {
            this.commentID = commentID;
            return this;
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public Builder setPostID(String postID) {
            this.postID = postID;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Comment build() {
            return new Comment(commentID, userID, postID, body);
        }
    }
    public static Builder newBuilder(){
        return new Builder();
    }
    private Comment(String commentID, String userID, String postID, String body) {
        this.commentID = commentID;
        this.userID = userID;
        this.postID = postID;
        this.body = body;
    }

    public String getCommentID() {

        return commentID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPostID() {
        return postID;
    }

    public String getBody() {
        return body;
    }

}
