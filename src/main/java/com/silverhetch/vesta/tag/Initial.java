package com.silverhetch.vesta.tag;

import com.silverhetch.vesta.arch.database.Sql;

/**
 * Sql for initial the tag table.
 */
public class Initial implements Sql {
    @Override
    public String generate() {
        return "CREATE TABLE TAG(name CHAR UNIQUE )";
    }
}
