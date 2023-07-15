package com.eviro.assessment.grad001.MorganneMatoka.Assessment.controller;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository.AccountProfileRepository;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {


    private final AccountService accountService;

    @Autowired
    public ImageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource getHttpImageLink(@PathVariable String name,
                                               @PathVariable String surname,
                                               @PathVariable("\\w\\.\\w") String fileName) {
        return accountService.getAccountHttpLink(fileName);
    }

}
