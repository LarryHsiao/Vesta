package com.silverhetch.vesta.target;

import com.silverhetch.vesta.arch.database.sql.Sql;

public class Initial implements Sql {
    @Override
    public String generate() {
        return "CREATE TABLE if not exists TARGET(uri CHAR UNIQUE);";
    }
}
