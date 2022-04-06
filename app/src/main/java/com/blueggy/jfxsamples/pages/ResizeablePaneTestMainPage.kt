package com.blueggy.jfxsamples.pages

import com.blueggy.jfxsamples.components.ResizeablePane
import javafx.scene.Parent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.stage.Stage

class ResizeablePaneTestMainPage(ps: Stage) : AbstractMainPage(ps) {

    override fun mainPane(): ResizeablePane {
        return ResizeablePane().apply {
            id = "ResizeablePane"
            children.add(pane1())
            children.add(pane2())
        }
    }


    private fun pane1(): Pane {
        return Pane().apply {
            background = Background(BackgroundFill(Color.AQUA, null, null))
            prefWidth = 50.0
            prefHeight = 50.0
        }
    }

    private fun pane2(): Pane {
        return Pane().apply {
            background = Background(BackgroundFill(Color.BEIGE, null, null))
            prefWidth = 50.0
            prefHeight = 50.0
        }
    }

    private fun pane3(): Pane {
        return Pane().apply {
            background = Background(BackgroundFill(Color.CADETBLUE, null, null))
            prefWidth = 50.0
            prefHeight = 50.0
        }
    }
}