package com.silverhetch.vesta.attached.tags

import com.silverhetch.vesta.attached.AttachedTableConn
import com.silverhetch.vesta.tag.DBTag
import com.silverhetch.vesta.tag.Tag
import java.sql.Connection

/**
 * Database implementation of [AttachedTags].
 */
class DBAttachedTags(private val connection: Connection, private val targetId: Long) : AttachedTags {
    override fun init() {
        AttachedTableConn(connection).init()
    }

    override fun all(): Map<String, AttachedTag> {
        connection.prepareStatement("""select * from attachments left join tags where target_id=? and tag_id=tags.id""").use { statement ->
            statement.setLong(1, targetId)
            val result = HashMap<String, AttachedTag>()
            statement.executeQuery().use { resultSet ->
                while (resultSet.next()) {
                    val name = resultSet.getString("name")
                    result[name] = DBAttachedTag(
                        connection,
                        targetId,
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
            it.setLong(1, targetId)
            it.setLong(2, tag.id())
            it.execute()
        }
    }
}