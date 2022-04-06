package com.blueggy.jfxsamples.pages

import com.jfoenix.controls.*
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.Stage

fun mainDecorator(stage: Stage): JFXDecorator {
    return JFXDecorator(stage, mainPane(), false, true, true).apply {
    }
}


private fun mainPane(): Parent {
    return AnchorPane()
}

