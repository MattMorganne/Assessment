package com.eviro.assessment.grad001.MorganneMatoka.Assessment.service;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morganne
 */
@Service
public class AccountProfileReader implements FileParser {

    private List<String[]> dataList = new ArrayList<>();

    public AccountProfileReader() {
    }


    @Override
    public void parseCSV(File file) {
        BufferedReader bf=null;

        try {
            String line = "";
            String arr = "";

            bf = new BufferedReader(new FileReader(file));
            while ((line = bf.readLine()) != null) {
                dataList.add(line.split(","));

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccountProfileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccountProfileReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bf.close();
            } catch (IOException ex) {
                Logger.getLogger(AccountProfileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    @Override
    public File convertedCSVDataToImage(String base64Image) {
        FileOutputStream fos = null;
        File outputFile = null;

        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            ClassLoader classLoader = getClass().getClassLoader();
            String outputPath = classLoader.getResource(".").getFile() +"/" + Instant.now().getEpochSecond() + ".jpg";
            fos = new FileOutputStream(outputPath);
            fos.write(imageBytes);
            outputFile = new File(outputPath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccountProfileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccountProfileReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(AccountProfileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return outputFile;
    }

    @Override
    public URI createImageLink(File fileImage) {
        URI imageURI = fileImage.toURI();
         return imageURI;
    }

    public List<String[]> getDataList() {
        return dataList;
    }

    public void setDataList(List<String[]> dataList) {
        this.dataList = dataList;
    }



}
