package zl4;

public class Password {
    private String password;

    public Password(String password) {
        this.password = password;
    }

    public boolean isCorrectPassword(String userPassword) {
        return password.equals(userPassword);
    }
}
