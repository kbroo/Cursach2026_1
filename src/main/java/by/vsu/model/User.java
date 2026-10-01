package by.vsu.model;

public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private String hashPassword;
    private Role userRole;

    public User(int userId, String userName, String userEmail, String hashPassword, Role userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.hashPassword = hashPassword;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getHashPassword() {
        return hashPassword;
    }
    public Role getUserRole() {
        return userRole;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        User object = (User) obj;
        return userId == object.getUserId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}
