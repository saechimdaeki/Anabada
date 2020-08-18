package Anabada.Anabada.controller;

import Anabada.Anabada.domain.Account;
import Anabada.Anabada.repository.AccountRepository;
import Anabada.Anabada.secret.Hashing;
import Anabada.Anabada.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistController {

    private final AccountService accountService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody Account newAccount){
        return accountService.registAccount(newAccount);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Account login){
        return accountService.login(login);
    }

}
