package com.silverhetch.vesta.arch.database;

import com.silverhetch.clotho.Source;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class H2DbConn implements Source<Connection> {
    private final String rootPath;

    public H2DbConn(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public Connection fetch() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection("jdbc:h2:" + rootPath + File.separator + "vesta.db", "", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
