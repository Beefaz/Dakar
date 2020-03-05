package sample.model;

public class Vartotojas {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private boolean adminRights;


    public Vartotojas(int userId, String userName, String userPassword, String userEmail, boolean adminRights) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.adminRights = adminRights;
    }

    //registracijai
    public Vartotojas(String userName, String userPassword, String userEmail, boolean adminRights) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.adminRights = adminRights;
    }

    //
    public Vartotojas(String userName) {
        this.userName = userName;
    }

    public Vartotojas() {
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(boolean adminRights) {
        this.adminRights = adminRights;
    }

    @Override
    public String toString() {
        return "Vartotojas{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", adminRights=" + adminRights +
                '}';
    }
}