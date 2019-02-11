package com.silverhetch.vesta

import com.silverhetch.vesta.tag.Tags
import com.silverhetch.vesta.target.Targets
import java.sql.Connection

interface Vesta {
    fun target(): Targets
    fun tag(): Tags
}