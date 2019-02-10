package com.silverhetch.vesta.target

import java.net.URI

interface Target {
    fun uri(): URI
    fun delete()
}