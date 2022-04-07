package com.blueggy.jfxsamples.pages

import com.blueggy.jfxsamples.components.LayerSplitPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.stage.Stage

class LayerSplitPaneTestMainPage(ps: Stage) : AbstractMainPage(ps) {

    override fun mainPane(): LayerSplitPane {
        return LayerSplitPane().apply {
            id = "ResizeablePane"

            items.add(pane1())
            items.add(pane2())
        }
    }


    private fun pane1(): Pane {
        return Pane().apply {
            background = Background(BackgroundFill(Color.AQUA, null, null))
            prefWidth = 50.0
            prefHeight = 50.0
            minWidth = 50.0
            minHeight = 50.0
        }
    }

    private fun pane2(): Pane {
        return Pane().apply {
            background = Background(BackgroundFill(Color.BEIGE, null, null))
            prefWidth = 50.0
            prefHeight = 50.0
            minWidth = 50.0
            minHeight = 50.0
        }
    }

    private fun pane3(): Pane {
        return Pane().apply {
            background = Background(BackgroundFill(Color.CADETBLUE, null, null))
            prefWidth = 50.0
            prefHeight = 50.0
            minWidth = 50.0
            minHeight = 50.0
        }
    }
}