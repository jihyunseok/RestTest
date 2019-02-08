package src.example.vo;

public class LoginVO {

    private final String userId;
    private final String password;


    public LoginVO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

}
