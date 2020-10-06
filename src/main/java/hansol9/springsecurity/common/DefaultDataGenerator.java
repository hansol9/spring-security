package hansol9.springsecurity.common;

import hansol9.springsecurity.account.Account;
import hansol9.springsecurity.account.AccountService;
import hansol9.springsecurity.book.Book;
import hansol9.springsecurity.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // hansol9 -> spring
        // hansol10 -> hibernate
        Account hansol9 = createUser("hansol9");
        Account hansol10 = createUser("hansol10");

        createBook("spring", hansol9);
        createBook("hibernate", hansol10);

    }

    private void createBook(String title, Account hansol9) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(hansol9);
        bookRepository.save(book);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("123");
        account.setRole("USER");

        return accountService.createNew(account);
    }
}
