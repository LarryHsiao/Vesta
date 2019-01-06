package com.silverhetch.vesta.arch.database;

import java.sql.SQLException;

public interface SqlExecution<T> {
    T execute() throws SQLException;
}
