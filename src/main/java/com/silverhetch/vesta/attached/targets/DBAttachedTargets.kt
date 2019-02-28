package com.silverhetch.vesta.attached.targets

import com.silverhetch.vesta.attached.AttachedTableConn
import com.silverhetch.vesta.target.Target
import com.silverhetch.vesta.target.db.DBTargetResult
import java.sql.Connection

/**
 * All targets that attached to given tags.
 */
class DBAttachedTargets(private val conn: Connection, private val tagId: Long) : AttachedTargets {
    override fun init() {
        AttachedTableConn(conn).init()
    }

    override fun add(target: Target) {
        conn.prepareStatement("""insert into attachments(target_id, tag_id) values ( ? ,? );""").use {
            it.setLong(1, target.id())
            it.setLong(2, tagId)
            it.execute()
        }
    }

    override fun all(): Map<String, AttachedTarget> {
        conn.prepareStatement("""select * from targets left join attachments where tag_id=?""").use { statement ->
            statement.setLong(1, tagId)
            val result = HashMap<String, AttachedTarget>()
            statement.executeQuery().use { resultSet ->
                while (resultSet.next()) {
                    val name = resultSet.getString("name")
                    result[name] = DBAttachedTarget(
                        conn,
                        DBTargetResult(conn, resultSet).target(),
                        tagId
                    )
                }
            }
            return result
        }
    }
}