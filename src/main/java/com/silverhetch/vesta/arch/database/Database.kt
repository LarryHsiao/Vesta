package com.silverhetch.vesta.arch.database

import java.sql.Connection

interface Database {
    fun connection(): Connection
}