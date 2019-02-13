package com.silverhetch.vesta.attachement

import com.silverhetch.vesta.tag.DBTag
import com.silverhetch.vesta.tag.Tag
import com.silverhetch.vesta.target.Target
import java.sql.Connection

class DBAttachedTags(private val connection: Connection, private val target: Target) : Attachments {
    override fun init() {
        connection.createStatement().execute("""
            create table if not exists attachments
            (
              target_id integer,
              tag_id    integer,
              unique (target_id, tag_id)
            );
        """)
    }

    override fun all(): Map<String, Attachment> {
        connection.prepareStatement("""select * from tags left join attachments where target_id=?""").use { statement ->
            statement.setLong(1, target.id())
            val result = HashMap<String, Attachment>()
            statement.executeQuery().use { resultSet ->
                while (resultSet.next()) {
                    val name = resultSet.getString("name")
                    result[name] = DBAttachment(
                        connection,
                        target,
                        DBTag(
                            connection,
                            resultSet.getLong("id"),
                            name
                        )
                    )
                }
            }
            return result
        }
    }

    override fun add(tag: Tag) {
        connection.prepareStatement("""insert into attachments(target_id, tag_id) values ( ? ,? );""").use {
            it.setLong(1, target.id())
            it.setLong(2, tag.id())
            it.execute()
        }
    }
}