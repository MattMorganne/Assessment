package com.eviro.assessment.grad001.MorganneMatoka.Assessment.service;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.util.List;

@Component
public class FileFromfileDataParser {

       AccountService accountService;

    @Autowired
    public FileFromfileDataParser(AccountService accountService) {
        this.accountService = accountService;
    }

    public  String triggerAddAccountProfiles(String csvfilepath) {
        // Path to the CSV file

        System.out.println(csvfilepath);

        File csvFile = new File(csvfilepath);

        // Create an instance of AccountProfileReader
        AccountProfileReader accountProfileReader = new AccountProfileReader();

        // Parse the CSV file
        accountProfileReader.parseCSV(csvFile);

        // Get the data list from the CSV
        List<String[]> dataList = accountProfileReader.getDataList();

        // Iterate over the data list and create account profiles
        String filesInfo ="Please use the data belows when you go back to access foles account <br>";
        for (int i = 1; i < dataList.size(); i++) {
            String name = dataList.get(i)[0];
            String surname = dataList.get(i)[1];
            String base64Image = dataList.get(i)[3];

            // Convert base64 image data to a file
            File imageFile = accountProfileReader.convertedCSVDataToImage(base64Image);

            // Create an HTTP link for the image
            URI imageLink = accountProfileReader.createImageLink(imageFile);

            // Create an AccountProfile object
            AccountProfile accountProfile = new AccountProfile(name, surname, imageLink.toString());

            // Add the account profile using the AccountService
              accountService.addAccount(accountProfile);
            filesInfo = filesInfo + i + accountProfile.toString()+"\n";
        }
        return filesInfo;
    }

}