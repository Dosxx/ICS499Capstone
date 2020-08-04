/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import java.util.ArrayList;
import java.util.List;

public class QueryContext {
    /* Attributes */
    private QueryBuilder sqlBuilder;
    private User queryResult;
    private List queryResultList;

    public Object makeQuery(){
        if(sqlBuilder instanceof AddUserQueryBuilder) {
            return (User) sqlBuilder.addQuery();
        }else if(sqlBuilder instanceof SelectUserQueryBuilder){
            queryResult = (User)sqlBuilder.selectQuery();
            return queryResult;
        }else if(sqlBuilder instanceof AddDocumentQueryBuilder){
            return (Document) sqlBuilder.addQuery();
        }else if(sqlBuilder instanceof SelectDocumentQueryBuilder) {
            queryResultList = (ArrayList) sqlBuilder.selectQuery();
            return queryResultList;
        }else if(sqlBuilder instanceof DeleteUserQueryBuilder){
            return (Integer) sqlBuilder.deleteQuery();
        }else if(sqlBuilder instanceof DeleteDocumentQueryBuilder){
            return (Integer) sqlBuilder.deleteQuery();
        }else return null;
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        this.sqlBuilder = sqlBuilder;
    }
}
