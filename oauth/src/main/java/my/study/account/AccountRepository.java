package my.study.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
