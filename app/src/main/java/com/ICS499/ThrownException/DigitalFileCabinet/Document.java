/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */

package com.ICS499.ThrownException.DigitalFileCabinet;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Document implements Serializable {
    /*Class variables declaration */

    private String documentName;
    private String createdDate;
    private String lastEditDate;
    private String fileExtension = "JPEG";
    private QueryContext sqlContext;
    private QueryBuilder selectQuery;
    private QueryBuilder addQuery;
    private String filePath;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Document(String documentName){
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.documentName = documentName;
        createdDate = dft.format(LocalDateTime.now());
        lastEditDate = createdDate;
    }

    public Document getDocument(){
        return this;
    }

    public String getDocumentName() {
        return documentName;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        lastEditDate = dft.format(LocalDateTime.now());
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(String lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void makeQuery(){

    }
}
