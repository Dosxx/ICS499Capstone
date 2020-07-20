/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class QueryContext {
    /* Attributes */
    QueryBuilder sqlContext;

    public void makeQuery(){
        sqlContext.buildQuery();
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        this.sqlContext = sqlBuilder;
    }
}
