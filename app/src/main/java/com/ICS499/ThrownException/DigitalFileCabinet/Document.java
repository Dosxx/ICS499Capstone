/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */

package com.ICS499.ThrownException.DigitalFileCabinet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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


    public Document(String documentName, String filePath, File file){
        this.documentName = documentName;
        this.filePath = filePath;
        this.file = file;
        System.out.println("FILE SIZE ID "+file.length()+"");
        createdDate = new SimpleDateFormat(
                "yyyy_MM_ddd_HH_mm_ss", Locale.getDefault()
        ).format(new Date());
        lastEditDate = createdDate;
    }

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

    public void setByteToFile(byte[] bytes) {
        try {
            OutputStream outputStreams = new FileOutputStream(file);
            // Starts writing the bytes in it
            outputStreams.write(bytes);
            // Close the file
            outputStreams.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
