package reqresPojos;

public class RegisterUserRes {
    public RegisterUserRes() {
    }

    public RegisterUserRes(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    private Integer id;
    private String token;


    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
