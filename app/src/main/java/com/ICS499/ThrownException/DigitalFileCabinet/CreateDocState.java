/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class CreateDocState implements DFCState {
    private Document doc;

    public CreateDocState(Document myDoc) {this.doc = myDoc;}

    @Override
    public void setState(DFCContext context) {
        context.setState(this);
    }

    @Override
    public void createAccount() {
        doc.makeQuery();
    }

    @Override
    public void makeQuery() {

    }

    public Document getDocument(){
        return doc;
    }

    public void setDocument(Document myDoc){
        this.doc = myDoc;
    }


}
