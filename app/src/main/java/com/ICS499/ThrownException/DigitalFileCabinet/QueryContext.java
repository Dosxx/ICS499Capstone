/**
 * Author:Thrown Exceptions
 *
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public abstract class QueryContext {
    //attributes
    private QueryBuilder sqlContext;

    private QueryContext(QueryBuilder sqlBuilder){
        this.sqlContext = sqlBuilder;
    };
    public void makeQuery(){
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        sqlContext = sqlBuilder;
    }
}
