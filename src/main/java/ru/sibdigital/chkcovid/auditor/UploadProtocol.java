package ru.sibdigital.chkcovid.auditor;

import java.util.List;
import java.util.Map;


public class UploadProtocol {
    private String fileName;

    private List<Map> parsedData;


    private String globalMessage = "";

    private int uploaded = 0;
    private int errors = 0;
    private int alreadyExists = 0;

    public UploadProtocol(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Map> getParsedData() {
        return parsedData;
    }

    public void setParsedData(List<Map> parsedData) {
        this.parsedData = parsedData;
    }


    public String getGlobalMessage() {
        return globalMessage;
    }

    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }

    public int getUploaded() {
        return uploaded;
    }

    public void setUploaded(int uploaded) {
        this.uploaded = uploaded;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public int getAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(int alreadyExists) {
        this.alreadyExists = alreadyExists;
    }
}
