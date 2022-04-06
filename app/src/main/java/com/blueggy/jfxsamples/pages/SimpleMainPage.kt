package com.blueggy.jfxsamples.pages

import com.jfoenix.controls.*
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.Stage


class SimpleMainPage(ps: Stage) : AbstractMainPage(ps) {

    override fun mainPane(): Parent {
        return AnchorPane()
    }
}



