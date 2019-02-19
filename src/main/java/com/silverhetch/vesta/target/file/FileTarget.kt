package com.silverhetch.vesta.target.file

import com.silverhetch.vesta.target.Target
import java.io.File
import java.net.URI

/**
 * Target implementation on file system.
 */
class FileTarget(private val file: File) : Target {
    override fun id(): Long {
        return file.hashCode().toLong()
    }

    override fun name(): String {
        return file.name
    }

    override fun delete() {
        file.delete()
    }
}
