package com.silverhetch.vesta.target.file

import com.silverhetch.vesta.target.Target
import com.silverhetch.vesta.target.Targets
import java.io.File

/**
 * [Targets] implementation on file system.
 */
class FileTargets(private val root: File) : Targets {
    override fun init() {
        // leave it empty
    }

    override fun add(newFile: File) {
        if (!newFile.absoluteFile.startsWith(root.absoluteFile)) {
            newFile.renameTo(File(root, newFile.name))
        }
    }

    override fun byName(name: String): Target {
        return FileTarget(File(root, name))
    }

    override fun byKeyword(keyword: String): Map<String, Target> {
        val result = HashMap<String, Target>()
        root.listFiles().forEach {
            if (it.name.toLowerCase().contains(keyword.toLowerCase())){
                result[it.name] = FileTarget(it)
            }
        }
        return result
    }

    override fun all(): Map<String, Target> {
        val result = HashMap<String, Target>()
        root.listFiles().forEach {
            result[it.name] = FileTarget(it)
        }
        return result
    }
}