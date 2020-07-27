/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */

package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Document implements Serializable {
    /*Class variables declaration */
    private long documentID;  //set the document is written to the database
    private String documentName;
    private String createdDate;
    private String lastEditDate;
    private String fileExtension = "JPEG";
    private String filePath;
    private File file;
    private QueryContext sqlContext;
    private QueryBuilder selectQuery;
    private QueryBuilder addQuery;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Document(String documentName, String filePath, File file){
        this.documentName = documentName;
        this.filePath = filePath;
        this.file = file;
        createdDate = new SimpleDateFormat("yyyy_MM_ddd_HH_mm_ss", Locale.getDefault())
                .format(LocalDateTime.now());
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
        lastEditDate = new SimpleDateFormat("yyyy_MM_ddd_HH_mm_ss", Locale.getDefault())
                .format(LocalDateTime.now());
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


    public String toString() {
        return String.format("Name: %s\rCreate date: %s\rLast edited: %s\rFilePath: %s\r",
                this.documentName,
                this.createdDate,
                this.lastEditDate,
                this.filePath);
    }

    public void setDocumentID(long documentID) {this.documentID = documentID;}

    public long getDocumentID() {
        return documentID;
    }
}
