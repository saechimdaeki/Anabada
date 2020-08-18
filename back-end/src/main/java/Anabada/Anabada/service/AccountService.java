package Anabada.Anabada.service;

import Anabada.Anabada.domain.Account;
import Anabada.Anabada.repository.AccountRepository;
import Anabada.Anabada.secret.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public String registAccount(Account newAccount){
        String email= newAccount.getEmail();
        String username= newAccount.getUsername();
        String password= Hashing.hashingPassword(newAccount.getPassword());
        if(username.equals("")||password.equals("")||email.equals(""))
            return "실패";

        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        if(accountRepository.findByUsername(username)!=null)
            return "실패";
        accountRepository.save(account);
        return "성공";
    }

    @Transactional
    public String login(Account loginAccount){
        String email=loginAccount.getEmail();
        String username=loginAccount.getUsername();
        String password=Hashing.hashingPassword(loginAccount.getPassword());

        Account account=accountRepository.findByUsername(username);
        if(account.getUsername().equals(username) && account.getEmail().equals(email) && account.getPassword().equals(password))
            return "성공";
        else
            return "실패";
    }



}
