package hansol9.springsecurity.form;

import hansol9.springsecurity.account.Account;
import hansol9.springsecurity.account.AccountContext;
import hansol9.springsecurity.account.AccountRepository;
import hansol9.springsecurity.account.UserAccount;
import hansol9.springsecurity.common.CurrentUser;
import hansol9.springsecurity.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account) {
//    public String index(Model model, @AuthenticationPrincipal UserAccount userAccount) {
//    public String index(Model model, Principal principal) {
        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        }
        else {
            model.addAttribute("message", "Hello " + account.getUsername());
        }

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();
        sampleService.displayThreadLocal();
        sampleService.displaySecurityContextHolder();
        return "dashboard";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello User, " + principal.getName());
        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {
        SecurityLogger.log("MVC");

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                SecurityLogger.log("Callable");
                return "Async Handler";
            }
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }
}
