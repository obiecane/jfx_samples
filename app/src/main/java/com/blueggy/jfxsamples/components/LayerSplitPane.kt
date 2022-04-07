package com.blueggy.jfxsamples.components

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.input.MouseEvent

class LayerSplitPane : Control() {
    val items: ObservableList<Node> = FXCollections.observableArrayList<Node>()
    val orientationProperty: ObjectProperty<Orientation> by lazy { SimpleObjectProperty<Orientation>(Orientation.HORIZONTAL) }

    init {

    }

    private fun aaa() {
        this.addEventHandler(MouseEvent.MOUSE_MOVED) {
            println("$it")
        }
    }

    private fun makeChildResizeable(node: Node) {
        println(node)
        node.addEventHandler(MouseEvent.MOUSE_MOVED) {
            println("$it")
        }
    }

    override fun createDefaultSkin(): Skin<*> {
        return LayerSplitPaneSkin(this)
    }

    override fun getInitialFocusTraversable(): Boolean {
        return false
    }


    fun getOrientation(): Orientation {
        return orientationProperty.value
    }

    fun setOrientation(orientation: Orientation) {
        orientationProperty.set(orientation)
    }
}