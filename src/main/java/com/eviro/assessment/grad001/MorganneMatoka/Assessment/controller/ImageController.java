package com.eviro.assessment.grad001.MorganneMatoka.Assessment.controller;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {


    private final AccountService accountService;

    @Autowired
    public ImageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{name}/{surname}/{\\w\\.\\w}")
    public ResponseEntity<FileSystemResource> getHttpImageLink(@PathVariable String name,
                                                               @PathVariable String surname,
                                                               @PathVariable("\\w\\.\\w") String fileName) {
         return accountService.getAccountHttpLink(fileName);

    }
    }


