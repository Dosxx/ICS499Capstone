/**
 * Author: Thrown Exceptions
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Document(String documentName){
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        this.documentName = documentName;
        createdDate = new String(dft.format(LocalDateTime.now()));
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
        lastEditDate = new String(dft.format(LocalDateTime.now()));
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    public void makeQuery(){
    }
}
