/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */

package com.ICS499.ThrownException.DigitalFileCabinet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    // use this constructor to retrieve document from db
    private Document(){}

    public Document getDocument(){
        return this;
    }

    public String getDocumentName() {
        return documentName;
    }

    public byte[] getFileToByte() {
        byte[] fileByteArray = new byte[(int) file.length()];
        try{
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(fileByteArray);
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return fileByteArray;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
        lastEditDate = new SimpleDateFormat(
                "yyyy_MM_ddd_HH_mm_ss", Locale.getDefault()
        ).format(new Date());
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

    public File getFile() {
        return file;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String toString() {
        return String.format("Name: %s\nCreate date: %s\nLast edited: %s",
                this.documentName,
                this.createdDate,
                this.lastEditDate);
    }

    public void setDocumentID(long documentID) {this.documentID = documentID;}

    public long getDocumentID() {
        return documentID;
    }

    public static class Builder {
        // implemented a builder pattern for the document class
        private long documentID;  //set the document is written to the database
        private String documentName;
        private String createdDate;
        private String lastEditDate;
        private String filePath;
        private File file;
        public Builder(String documentName) {
            this.documentName = documentName;
        }

        public Builder setDocumentId(long documentID){
            this.documentID = documentID;
            return this;
        }
        public Builder setCreatedDate(String createdDate){
            this.createdDate = createdDate;
            return this;
        }
        public Builder setLastEditedDate(String lastEditDate){
            this.lastEditDate = lastEditDate;
            return this;
        }
        public Builder setFile(File file){
            this.file = file;
            return this;
        }
        public Builder setFilePath(String filePath){
            this.filePath = filePath;
            return this;
        }
        public Document build(){
            Document document = new Document();
            document.documentName = this.documentName;
            document.documentID = this.documentID;
            document.createdDate = this.createdDate;
            document.lastEditDate = this.lastEditDate;
            document.file = this.file;
            document.filePath = this.filePath;
            return document;
        }
    }
}
