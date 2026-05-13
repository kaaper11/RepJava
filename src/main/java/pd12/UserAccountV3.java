package pd12;

import java.util.Objects;

public class UserAccountV3 extends UserAccount {
    public UserAccountV3(Long id, String email, String displayName) {
        super(id, email, displayName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, displayName);
    }
}
