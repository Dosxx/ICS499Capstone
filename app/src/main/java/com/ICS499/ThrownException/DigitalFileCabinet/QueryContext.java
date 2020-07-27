/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class QueryContext {
    /* Attributes */
    private QueryBuilder sqlBuilder;
    private boolean hasResult = false;

    public void makeQuery(){
        if(sqlBuilder instanceof AddUserQueryBuilder) {
            sqlBuilder.addQuery();
        }else if(sqlBuilder instanceof SelectUserQueryBuilder){
            hasResult = sqlBuilder.selectQuery();
        }
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        this.sqlBuilder = sqlBuilder;
    }
}
