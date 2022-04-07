package com.blueggy.jfxsamples.components

import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.SkinBase
import javafx.scene.control.SplitPane
import javafx.scene.layout.StackPane
import javafx.scene.shape.Rectangle

class LayerSplitPaneSkin(control: LayerSplitPane) : SkinBase<LayerSplitPane>(control) {
    private var horizontal = false
    private val contentRegions: ObservableList<Content> = FXCollections.observableArrayList<Content>()

    init {
        horizontal = skinnable.getOrientation() == Orientation.HORIZONTAL

        for ((i, item) in skinnable.items.withIndex()) {
            addContent(i, item)
        }

        initializeContentListener()
        registerChangeListener(control.widthProperty()) { skinnable.requestLayout() }
        registerChangeListener(control.heightProperty()) { skinnable.requestLayout() }
    }


//    override fun layoutChildren(contentX: Double, contentY: Double, contentWidth: Double, contentHeight: Double) {
//        val sw: Double = skinnable.width
//        val sh: Double = skinnable.height
//
//        if ((if (horizontal) sw == 0.0 else sh == 0.0) || contentRegions.isEmpty()) {
//            return
//        }
//    }

    override fun layoutChildren(contentX: Double, contentY: Double, contentWidth: Double, contentHeight: Double) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight)
    }



    private fun initializeContentListener() {
        skinnable.items.addListener { c: ListChangeListener.Change<out Node?> ->
            while (c.next()) {
                if (c.wasPermutated() || c.wasUpdated()) {
                    /**
                     * the contents were either moved, or updated.
                     * rebuild the contents to re-sync
                     */
                    children.clear()
                    contentRegions.clear()
                    for ((index, n) in c.list.withIndex()) {
                        addContent(index, n!!)
                    }
                } else {
                    for (n in c.removed) {
                        removeContent(n!!)
                    }
                    var index = c.from
                    for (n in c.addedSubList) {
                        addContent(index++, n!!)
                    }
                }
            }


        }
    }

    private fun addContent(index: Int, n: Node) {
        val c = Content(n)
        contentRegions.add(index, c)
        children.add(index, c)
    }

    private fun removeContent(n: Node) {
        for (c in contentRegions) {
            if (c.content == n) {
                c.dispose()
                children.remove(c)
                contentRegions.remove(c)
                break
            }
        }
    }

    internal class Content(n: Node?) : StackPane() {
        val content: Node?
        private val clipRect: Rectangle
        var x: Double
        var y: Double

        // This is the area of the panel.  This will be used as the
        // width/height during layout.
        var area = 0.0

        // This is used to save the current area during resizing when
        // isResizeableWithParent equals false.
        var resizableWithParentArea = 0.0
            set(resizableWithParentArea) {
                field = if (!isResizableWithParent) {
                    resizableWithParentArea
                } else {
                    0.0
                }
            }

        // This is the minimum available area for other panels to use
        // if they need more space.
        var available = 0.0

        val isResizableWithParent: Boolean
            get() = SplitPane.isResizableWithParent(content)

        protected fun setClipSize(w: Double, h: Double) {
            clipRect.width = w
            clipRect.height = h
        }

        fun dispose() {
            children.remove(content)
        }

        override fun computeMaxWidth(height: Double): Double {
            return snapSizeX(content!!.maxWidth(height))
        }

        override fun computeMaxHeight(width: Double): Double {
            return snapSizeY(content!!.maxHeight(width))
        }

        init {
            clipRect = Rectangle()
            clip = clipRect
            content = n
            if (n != null) {
                children.add(n)
            }
            x = 0.0
            y = 0.0
        }
    }
}