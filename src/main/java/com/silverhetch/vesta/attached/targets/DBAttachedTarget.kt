package com.silverhetch.vesta.attached.targets

import com.silverhetch.vesta.target.Target
import java.sql.Connection

/**
 * The taget that attached to the given tag.
 */
class DBAttachedTarget(
    private val conn: Connection,
    private val target: Target,
    private val tagId: Long
) : AttachedTarget {
    override fun target(): Target {
        return target
    }

    override fun remove() {
        conn.prepareStatement("""delete from attachments where target_id=? and tag_id=?""").use {
            it.setLong(1, target.id())
            it.setLong(2, tagId)
            it.execute()
        }
    }
}