/**
 * Author:Thrown Exceptions
 *
 */
package com.ICS499.ThrownException.DigitalFileCabinet;

import android.os.Bundle;
import android.database.sqlite.*;

public class QueryContext {
    //attributes
    private QueryBuilder sqlContext;

    private QueryContext(QueryBuilder sqlBuilder){
        this.sqlContext = sqlBuilder;
    };
    public QueryBuilder makeQuery(){
        sqlContext.buildQuery();
        return sqlContext;
    }
    public void setQueryBuilder(QueryBuilder sqlBuilder){
        sqlContext = sqlBuilder;
    }
}
