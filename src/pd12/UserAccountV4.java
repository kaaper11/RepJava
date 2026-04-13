package pd12;

import lombok.Setter;

import java.util.Objects;

@Setter
public class UserAccountV4 extends UserAccount {
    protected String status;
    public UserAccountV4(Long id, String email, String displayName, String status) {
        super(id, email, displayName);
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountV4 that = (UserAccountV4) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(displayName, that.displayName) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, displayName, status);
    }
}
