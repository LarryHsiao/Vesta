package com.silverhetch.vesta.target;

import com.silverhetch.vesta.arch.database.sql.Sql;

public class Insert implements Sql {
    private final String url;

    public Insert(String url) {
        this.url = url;
    }

    @Override
    public String generate() {
        return "insert into target(uri) values ('" + url + "');";
    }
}
