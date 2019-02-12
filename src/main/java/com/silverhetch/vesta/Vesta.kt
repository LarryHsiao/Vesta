package com.silverhetch.vesta

import com.silverhetch.vesta.tag.Tags
import com.silverhetch.vesta.target.Targets

/**
 * Object that builds the main implementations of objects that used in this app.
 */
interface Vesta {
    fun target(): Targets
    fun tag(): Tags
}