package com.silverhetch.vesta.downloads

import java.net.URI

/**
 * @todo #3 Implementation of Downloads
 * @todo #3 multi threading
 * @todo #3 continue download if interrupted last time which have multiple file not complete
 * @todo #3 (Servery)Progress and uri recording
 */
interface Downloads {
    fun new(uri: URI)
    fun start()
    fun stop()
}