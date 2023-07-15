package com.eviro.assessment.grad001.MorganneMatoka.Assessment.service;


import java.io.File;
import java.net.URI;

public interface FileParser {
    void parseCSV(File csvFile);
    File convertedCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);
}
