package com.silverhetch.vesta.target

import java.io.File


interface Targets {
    fun init()
    fun add(newFile: File)
    fun all(): Map<String, Target>
    fun byName(name: String): Target
    fun byKeyword(keyword: String): Map<String, Target>
}