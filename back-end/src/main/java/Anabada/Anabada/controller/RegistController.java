package Anabada.Anabada.controller;

import Anabada.Anabada.domain.Account;
import Anabada.Anabada.repository.AccountRepository;
import Anabada.Anabada.secret.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class RegistController {

    private final AccountRepository accountRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/regist")
    @ResponseBody
    public String register(@RequestBody Account newAccount){
        String email= newAccount.getEmail();
        String username= newAccount.getUsername();
        String password= Hashing.hashingPassword(newAccount.getPassword());
        if(username.equals("")||password.equals("")||email.equals(""))
            return "실패!";

        Account account=new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);

        if(accountRepository.findByUsername(username)!=null)
            return "실패.....";
        accountRepository.save(account);
        return "성공!";
    }
}
