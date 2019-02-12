package com.silverhetch.vesta.target

import java.net.URI


interface Targets {
    fun init()
    fun add(uri: URI)
    fun all(): Array<Target>
}