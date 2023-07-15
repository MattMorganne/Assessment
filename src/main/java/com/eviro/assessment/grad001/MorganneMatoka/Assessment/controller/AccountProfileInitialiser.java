package com.eviro.assessment.grad001.MorganneMatoka.Assessment.controller;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository.AccountProfileRepository;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.service.AccountService;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.service.FileFromfileDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AccountProfileInitialiser {

    private final AccountService accountService;

    @Autowired
    public AccountProfileInitialiser(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/triggerAddAccountProfiles/{csvfile}")
    public String saveFileparsedData(@RequestParam("csvfile") String csvfile, Model model) {

        FileFromfileDataParser fileFromfileDataParser = new FileFromfileDataParser(accountService);
        model.addAttribute("info",fileFromfileDataParser.triggerAddAccountProfiles(csvfile));
        return "display" ;
    }


}
