package nl.sean.dea.dto;

public class TokenDTO {
    private String token;
    private String user;

    public TokenDTO() {
    }

    public TokenDTO(String user, String token) {
        this.token = token;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
