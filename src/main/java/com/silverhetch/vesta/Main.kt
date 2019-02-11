package com.silverhetch.vesta

import com.silverhetch.vesta.jfx.VestaApplication
import javafx.application.Application

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Application.launch(VestaApplication::class.java, *args)
        }
    }
}