package com.silverhetch.vesta.tag

import java.sql.Connection

class DBTag(private val connection: Connection, private val name: String) : Tag {
}