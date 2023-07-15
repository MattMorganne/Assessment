package com.eviro.assessment.grad001.MorganneMatoka.Assessment.service;



import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;

@Service
public class AccountService {


    private final AccountProfileRepository accountProfileRepository;


    public AccountService(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    public void addAccount(AccountProfile accountProfile) {
        accountProfileRepository.save(accountProfile);
    }

    public AccountProfile getAccountFromDetails(String httpImageLink) {

                AccountProfile accountProfile;
        return accountProfileRepository.findByHttpImageLink(httpImageLink);
    }

    public FileSystemResource getAccountHttpLink(String filePath) {
        AccountProfile profile =  accountProfileRepository.findByHttpImageLink(filePath);

        File imageFile = new File(profile.getHttpImageLink());
        FileSystemResource resource = new FileSystemResource(imageFile);


        return resource;
    }


}