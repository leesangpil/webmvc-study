package my.study.account;

import lombok.RequiredArgsConstructor;
import my.study.account.enums.RoleEnum;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account createAccount(String username, String password, RoleEnum roleEnum) throws RuntimeException{
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);

        AccountRole accountRole = new AccountRole();
        accountRole.setUsername(username);
        accountRole.setRoleEnum(roleEnum);
        accountRoleRepository.save(accountRole);

        return accountRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("createAccount Error!!!!"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(account.getUsername(), account.getPassword(), account.getRoles());
    }
}
