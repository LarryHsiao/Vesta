package com.silverhetch.vesta.target;

import com.silverhetch.vesta.arch.database.sql.Sql;

public class SelectAll implements Sql {
    @Override
    public String generate() {
        return "SELECT * FROM TARGET";
    }
}
