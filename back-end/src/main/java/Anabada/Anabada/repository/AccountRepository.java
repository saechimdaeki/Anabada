package Anabada.Anabada.repository;

import Anabada.Anabada.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByUsername(String username);

    public Account findByUsernameAndPassword(String username,String password);

}
