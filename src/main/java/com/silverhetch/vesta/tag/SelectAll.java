package com.silverhetch.vesta.tag;

import com.silverhetch.vesta.arch.database.sql.Sql;

public class SelectAll implements Sql {
    @Override
    public String generate() {
        return "SELECT * FROM TAG;";
    }
}
