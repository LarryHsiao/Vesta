package com.silverhetch.vesta.database

import com.silverhetch.vesta.arch.database.Database
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class H2DB(private val path: String) : Database {
    override fun connection(): Connection {
        Class.forName("org.h2.Driver")
        return DriverManager.getConnection("jdbc:h2:" + path + File.separator + "vesta.db", "", "")
    }
}