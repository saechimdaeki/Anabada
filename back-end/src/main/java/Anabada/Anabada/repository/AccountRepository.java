package Anabada.Anabada.repository;

import Anabada.Anabada.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

     Account findByUsername(String username);

     Account findByUsernameAndPassword(String username,String password);

}
