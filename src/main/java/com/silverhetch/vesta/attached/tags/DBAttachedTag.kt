package com.silverhetch.vesta.attached.tags

import com.silverhetch.vesta.tag.Tag
import java.sql.Connection

/**
 * Database implementation of attachment
 */
class DBAttachedTag(private val connection: Connection, private val id: Long, private val tag: Tag) : AttachedTag {
    override fun tag(): Tag {
        return tag
    }

    override fun remove() {
        connection.prepareStatement("""delete from attachments where target_id=? and tag_id=?""").use {
            it.setLong(1, id)
            it.setLong(2, tag.id())
            it.execute()
        }
    }
}