package com.blueggy.jfxsamples.pages

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTabPane
import javafx.geometry.Insets
import javafx.geometry.Side
import javafx.scene.control.Button
import javafx.scene.control.Tab
import javafx.scene.layout.FlowPane
import javafx.scene.layout.StackPane

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