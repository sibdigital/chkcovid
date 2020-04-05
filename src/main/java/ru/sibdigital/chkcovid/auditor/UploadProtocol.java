package ru.sibdigital.chkcovid.auditor;

import java.util.List;
import java.util.Map;


public class UploadProtocol {
    private String fileName;

    private List<Map> parsedData;

    private List<List> statuses;


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

    public List<List> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<List> statuses) {
        this.statuses = statuses;
    }


}
