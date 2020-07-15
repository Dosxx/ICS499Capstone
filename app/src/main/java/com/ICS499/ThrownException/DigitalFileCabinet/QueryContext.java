/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public abstract class QueryContext {
    //attributes
    private QueryBuilder sqlContext;

    private QueryContext(QueryBuilder sqlBuilder){
        this.sqlContext = sqlBuilder;
    }
    public void makeQuery(){
        sqlContext.buildQuery();
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        sqlContext = sqlBuilder;
    }
}
