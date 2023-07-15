package com.eviro.assessment.grad001.MorganneMatoka.Assessment.service;

import com.eviro.assessment.grad001.MorganneMatoka.Assessment.model.AccountProfile;
import com.eviro.assessment.grad001.MorganneMatoka.Assessment.repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.StringJoiner;

@Service
public class FileFromfileDataParser {

    private final AccountService accountService;

    @Autowired
    public FileFromfileDataParser(AccountService accountService) {
        this.accountService = accountService;
    }

    public String triggerAddAccountProfiles(String csvFilePath) {
        // Create an instance of AccountProfileReader
        AccountProfileReader accountProfileReader = new AccountProfileReader();

        // Parse the CSV file
        File csvFile = new File(csvFilePath);
        accountProfileReader.parseCSV(csvFile);

        // Get the data list from the CSV
        List<String[]> dataList = accountProfileReader.getDataList();

        // Iterate over the data list and create account profiles
        StringJoiner filesInfo = new StringJoiner("\n", "Please use the data below when accessing the account files:\n", "");

        for (int i = 1; i < dataList.size(); i++) {
            String name = dataList.get(i)[0];
            String surname = dataList.get(i)[1];
            String base64Image = dataList.get(i)[3];

            // Convert base64 image data to a file
            File imageFile = accountProfileReader.convertedCSVDataToImage(base64Image);

            // Create an HTTP link for the image
            URI imageLink = accountProfileReader.createImageLink(imageFile);

            // Create an AccountProfile object
            AccountProfile accountProfile = new AccountProfile(name, surname, imageLink.toString().replace("file:/",""));

            // Add the account profile using the AccountService
            accountService.addAccount(accountProfile);
            ClassLoader classLoader = getClass().getClassLoader();
            String replace =    classLoader.getResource(".").getFile();
            filesInfo.add("\n"+ i + ". " + accountProfile.getName() +", " +  accountProfile.getSurname() +", " +  accountProfile.getHttpImageLink().replace(replace,""));
        }

        return filesInfo.toString();
    }
}
