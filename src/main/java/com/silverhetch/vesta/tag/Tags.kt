package com.silverhetch.vesta.tag

interface Tags {
    fun init()
    fun all(): Array<Tag>
    fun add(name: String)
}