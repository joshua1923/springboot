package dae.mn.springbootgraphqlengframework.user;

import java.util.ArrayList;

public class User {
    public static class Builder {
        private String email;
        private String username;
        private String password;
        private String userid;
        private ArrayList<String> follows;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUserid(String userid) {
            this.userid = userid;
            return this;
        }

        public Builder setFollows(ArrayList<String> follows) {
            this.follows = follows;
            return this;
        }

        public User build() {
            return new User(email, username, password, userid, follows);
        }
    }
    public static Builder newBuilder() {
        return new Builder();
    }

    private final String userid;
    private final String email;
    private final String username;
    private final String password;
    private final ArrayList<String> follows;

    public User(String email, String username, String password, String userid, ArrayList<String> follows) {
        this.userid = userid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.follows = follows;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getFollows() { return follows; }

    public void addFollow(String userid) {
        follows.add(userid);
    }
    public void removeFollow(String userid) {
        follows.remove(userid);
    }
}
