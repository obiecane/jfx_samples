package com.blueggy.jfxsamples.pages

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDecorator
import com.jfoenix.controls.JFXTabPane
import javafx.geometry.Insets
import javafx.geometry.Side
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.FlowPane
import javafx.scene.layout.StackPane
import javafx.stage.Stage

fun mainDecorator(stage: Stage): JFXDecorator {
    return JFXDecorator(stage, mainPane(), false, true, true).apply {
    }
}

fun mainMenu(): MenuBar {

    fun fileMenu(): Menu {
        return Menu("File").apply {
            this.items.addAll(
                MenuItem("File")
            )
        }
    }

    return MenuBar().apply {
        this.menus.addAll(fileMenu())
    }
}

fun mainPane(): BorderPane {
    return BorderPane().apply {
        top = mainMenu()
        center = tabPane()
    }
}

fun tabPane(): JFXTabPane {
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

    fun testTab(): Tab {
        return Tab("hello", pane)
    }

    fun testTab2(): Tab {
        return Tab("hello2", pane)
    }

    fun testTab3(): Tab {
        return Tab("hello3", pane)
    }


    return JFXTabPane().apply {
        this.side = Side.RIGHT
        tabs.addAll(testTab(), testTab2(), testTab3())
    }
}