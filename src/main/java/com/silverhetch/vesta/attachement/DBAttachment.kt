package com.silverhetch.vesta.attachement

import com.silverhetch.vesta.tag.Tag
import com.silverhetch.vesta.target.Target
import java.sql.Connection

/**
 * Database implementation of attachment
 */
class DBAttachment(private val connection: Connection, private val target: Target, private val tag: Tag) : Attachment {
    override fun tag(): Tag {
        return tag
    }

    override fun remove() {
        connection.prepareStatement("""delete from attachments where target_id=? and tag_id=?""").use {
            it.setLong(1, target.id())
            it.setLong(2, tag.id())
            it.execute()
        }
    }
}