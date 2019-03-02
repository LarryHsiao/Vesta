package com.silverhetch.vesta

import com.silverhetch.vesta.jfx.VestaApplication
import javafx.application.Application

/**
 * Entry point of Vesta
 */
class Main {
    companion object {
        /**
         * Main function
         */
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(VestaApplication::class.java, *args)
        }
    }
}