package com.blueggy.jfxsamples.pages

import com.jfoenix.controls.JFXDecorator
import javafx.scene.Parent
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

interface IMainPage {
    fun mainDecorator(): JFXDecorator
}

abstract class AbstractMainPage(private val ps: Stage) : IMainPage {
    override fun mainDecorator(): JFXDecorator {
        return JFXDecorator(ps, mainPane(), false, true, true).apply {
        }
    }


    open fun mainPane(): Parent {
        return AnchorPane()
    }
}