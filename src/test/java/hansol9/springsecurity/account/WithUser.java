package hansol9.springsecurity.account;

import org.springframework.security.test.context.support.WithMockUser;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "hansol9", roles = "USER")
public @interface WithUser {
}
