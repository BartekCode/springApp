package portfolio.portfolio.model;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN(Code.ADMIN),
    USER(Code.USER);



    private final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }


    public class Code {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }


}