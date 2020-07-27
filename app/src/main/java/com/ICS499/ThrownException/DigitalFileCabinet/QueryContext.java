/*
 * Author: Thrown Exceptions
 * ICS499 Capstone 2020
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

public class QueryContext {
    /* Attributes */
    private QueryBuilder sqlBuilder;
    private User queryResult;

    public Object makeQuery(){
        if(sqlBuilder instanceof AddUserQueryBuilder) {
            return (User) sqlBuilder.addQuery();
        }else if(sqlBuilder instanceof SelectUserQueryBuilder){
            queryResult = (User)sqlBuilder.selectQuery();
            return queryResult;
        }
        return null;
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        this.sqlBuilder = sqlBuilder;
    }
}
