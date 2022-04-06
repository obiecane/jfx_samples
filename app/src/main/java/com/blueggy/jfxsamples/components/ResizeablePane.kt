package com.blueggy.jfxsamples.components

import javafx.collections.ListChangeListener
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane

class ResizeablePane : StackPane() {

    init {
        aaa()
        children.addListener(ListChangeListener<Node> {
            while (it.next()) {
                if (it.wasAdded()) {
                    for (node in it.addedSubList) {
                        println(node)
                    }
                }
            }
        })
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

}