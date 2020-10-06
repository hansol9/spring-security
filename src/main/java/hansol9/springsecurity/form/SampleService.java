package hansol9.springsecurity.form;

import hansol9.springsecurity.account.Account;
import hansol9.springsecurity.account.AccountContext;
import hansol9.springsecurity.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@Service
public class SampleService {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
//    @Secured("ROLE_USER")
//    @RolesAllowed("ROLE_USER")
//    @PreAuthorize("hasRole('USER')")
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();
    }

    public void displayThreadLocal() {
        Account account = AccountContext.getAccount();
        System.out.println("Thread Local Info");
        System.out.println("=======================");
        System.out.println(account.getUsername());
        System.out.println("=======================");
    }

    public void displaySecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("SecurityContextHolder");
        System.out.println("=======================");
        System.out.println(userDetails.getUsername());
        System.out.println("=======================");
    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called");
    }
}
