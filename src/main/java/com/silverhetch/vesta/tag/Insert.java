package com.silverhetch.vesta.tag;

import com.silverhetch.vesta.arch.database.sql.Sql;

/**
 * Insertion one Tag
 */
public class Insert implements Sql {
    private final String name;

    public Insert(String name) {
        this.name = name;
    }

    @Override
    public String generate() {
        return "INSERT INTO TAG(NAME) values ( '" + name + "' )";
    }
}
