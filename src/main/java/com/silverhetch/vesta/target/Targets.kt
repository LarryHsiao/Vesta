package com.silverhetch.vesta.target

import java.io.File


/**
 * Targets
 */
interface Targets {
    /**
     * Initial the resources
     */
    fun init()

    /**
     * Add target using jdk [File].
     */
    fun add(newFile: File)

    /**
     * All the [Target] key by name.
     */
    fun all(): Map<String, Target>

    /**
     * Build a [Target] with exist target name.
     */
    fun byName(name: String): Target

    /**
     * Search [Target] by keyword.
     */
    fun byKeyword(keyword: String): Map<String, Target>
}