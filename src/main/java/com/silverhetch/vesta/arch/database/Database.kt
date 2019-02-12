package com.silverhetch.vesta.arch.database

import java.sql.Connection

/**
 * A Database which connected with JDBC implementation.
 */
interface Database {
    fun connection(): Connection
}