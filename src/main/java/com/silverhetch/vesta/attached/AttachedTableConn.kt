package com.silverhetch.vesta.attached

import java.sql.Connection

/**
 * Connection to database which have table attached_tags
 */
class AttachedTableConn(private val conn: Connection) {

    /**
     * Initial the table if not exist
     */
    fun init() {
        conn.createStatement().execute("""
            create table if not exists attachments
            (
              target_id integer,
              tag_id    integer,
              unique (target_id, tag_id)
            );
        """)
    }
}