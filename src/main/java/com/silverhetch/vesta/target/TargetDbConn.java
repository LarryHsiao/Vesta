package com.silverhetch.vesta.target;

import com.silverhetch.clotho.Source;

import java.sql.Connection;

/**
 * Decorator for database connection.
 */
public class TargetDbConn implements Source<Connection> {
    private final Source<Connection> dbConn;

    public TargetDbConn(Source<Connection> dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public Connection fetch() {
        try {
            final Connection connection = dbConn.fetch();
            connection.createStatement().execute(new Initial().generate());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
