package com.eviro.assessment.grad001.MorganneMatoka.Assessment.service;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AccountService {

    private final AccountProfileRepository accountProfileRepository;

    @Autowired
    public AccountService(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    public void addAccount(AccountProfile accountProfile) {
        accountProfileRepository.save(accountProfile);
    }

    public AccountProfile getAccountFromDetails(String httpImageLink) {
        return accountProfileRepository.findByHttpImageLink(httpImageLink);
    }

    public ResponseEntity<FileSystemResource> getAccountHttpLink(String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        filePath =   classLoader.getResource(".").getFile()  + filePath;
        filePath = filePath.substring(1,filePath.length());
        System.out.println(filePath);
        AccountProfile profile = accountProfileRepository.findByHttpImageLink(filePath);

        File imageFile = new File((profile.getHttpImageLink()));


        FileSystemResource imageResource=  new FileSystemResource(imageFile);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok()
                .headers(headers)
                .body(imageResource);
    }
}
