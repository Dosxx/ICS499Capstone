/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class QueryContext {
    /* Attributes */
    QueryBuilder sqlContext;

    public void makeQuery(){
        if(sqlContext instanceof AddUserQueryBuilder) {
            sqlContext.addQuery();
        }else if(sqlContext instanceof SelectUserQueryBuilder){
            sqlContext.selectQuery();
        }
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        this.sqlContext = sqlBuilder;
    }
}
