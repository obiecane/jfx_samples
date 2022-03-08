package com.blueggy.jfxsamples

import com.jfoenix.controls.JFXButton
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.FlowPane
import javafx.scene.layout.StackPane
import javafx.stage.Stage


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}

class App : Application() {
    override fun start(ps: Stage) {

        val main = FlowPane()
        main.vgap = 20.0
        main.hgap = 20.0

        main.children.add(Button("Java Button"))
        main.children.add(JFXButton("JFoenix Button"))

        main.children.add(JFXButton("RAISED BUTTON").apply {
            styleClass.add("button-raised")
        })

        main.children.add(JFXButton("DISABLED").apply {
            isDisable = true
        })

        val pane = StackPane().apply {
            children.add(main)
            style = "-fx-background-color:WHITE"
        }
        StackPane.setMargin(main, Insets(100.0))

        ps.apply {
            this.title = "JFX Button Demo"
            this.scene = Scene(pane, 800.0, 200.0).apply {
                stylesheets.add(App::class.java.getResource("/css/components.css")!!.toExternalForm())
            }
            this.show()
        }
    }
}
