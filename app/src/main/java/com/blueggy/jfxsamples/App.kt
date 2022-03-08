package com.blueggy.jfxsamples

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}

class App : Application() {
    override fun start(ps: Stage) {
        ps.apply {
            title = "JavaFX and Gradle"
            scene = Scene(AnchorPane())
            show()
        }
    }
}
