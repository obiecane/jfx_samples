package com.blueggy.jfxsamples.components

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.geometry.HPos
import javafx.geometry.Orientation
import javafx.geometry.Point2D
import javafx.geometry.VPos
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.SkinBase
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.scene.shape.Rectangle

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


class LayerSplitPaneSkin(control: LayerSplitPane) : SkinBase<LayerSplitPane>(control) {
    private var horizontal = false
    private val contentRegions: ObservableList<Content> = FXCollections.observableArrayList<Content>()
    private val contentDividers: ObservableList<ContentDivider> = FXCollections.observableArrayList<ContentDivider>()

    init {
        horizontal = skinnable.getOrientation() == Orientation.HORIZONTAL

        for ((i, item) in skinnable.items.withIndex()) {
            addContent(i, item)
        }

//        initializeContentListener()
        registerChangeListener(control.widthProperty()) { skinnable.requestLayout() }
        registerChangeListener(control.heightProperty()) { skinnable.requestLayout() }

        saa()
    }

    private fun saa() {
        skinnable.addEventHandler(MouseEvent.MOUSE_MOVED) {
            if (horizontal) {
                for (divider in contentDividers) {
                    if (it.x < divider.p1.x) {
                        skinnable.cursor = Cursor.W_RESIZE
                        break
                    }
                }
            }


        }
    }


    override fun layoutChildren(contentX: Double, contentY: Double, contentWidth: Double, contentHeight: Double) {
        val s = skinnable
        val sw = s.width
        val sh = s.height
        if ((if (horizontal) sw == 0.0 else sh == 0.0) || contentRegions.isEmpty()) {
            return
        }


        // [横向]给每个子节点分配宽度
        if (horizontal) {
            val avgW = contentWidth / contentRegions.size
            for ((idx, c) in contentRegions.withIndex()) {
                layoutInArea(c, c.x + idx * avgW, c.y, avgW, contentHeight, 0.0, HPos.CENTER, VPos.CENTER)
                if (idx > 0) {
                    val divider = contentDividers[idx - 1]
                    divider.p1 = Point2D(c.x, 0.0)
                    divider.p2 = Point2D(c.x, contentHeight)
                }
            }
        }
        // [纵向]给每个子节点分配高度
        else {
            val avgH = contentWidth / contentRegions.size
            for ((idx, c) in contentRegions.withIndex()) {
                layoutInArea(c, c.x, c.y + idx * avgH, contentWidth, avgH, 0.0, HPos.CENTER, VPos.CENTER)
            }
        }
    }


    private fun initializeContentListener() {
        skinnable.items.addListener { c: ListChangeListener.Change<out Node> ->
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
        c.minHeight = 100.0
        c.minWidth = 100.0
        contentRegions.add(index, c)
        children.add(index, c)
        if (children.size > 1) {
            contentDividers.add(ContentDivider())
        }
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

    internal class Content(n: Node) : StackPane(n) {
        val content: Node
        private val clipRect: Rectangle
        var x: Double
        var y: Double

        // This is the area of the panel.  This will be used as the
        // width/height during layout.
        var area = 0.0

        //
//        // This is used to save the current area during resizing when
//        // isResizeableWithParent equals false.
//        var resizableWithParentArea = 0.0
//            set(resizableWithParentArea) {
//                field = if (!isResizableWithParent) {
//                    resizableWithParentArea
//                } else {
//                    0.0
//                }
//            }
//
//        // This is the minimum available area for other panels to use
//        // if they need more space.
//        var available = 0.0
//
//        val isResizableWithParent: Boolean
//            get() = SplitPane.isResizableWithParent(content)
//
//        protected fun setClipSize(w: Double, h: Double) {
//            clipRect.width = w
//            clipRect.height = h
//        }
//
        fun dispose() {
            children.remove(content)
        }

        //
////        override fun computeMaxWidth(height: Double): Double {
////            return snapSizeX(content.maxWidth(height))
////        }
////
////        override fun computeMaxHeight(width: Double): Double {
////            return snapSizeY(content.maxHeight(width))
////        }
//
        init {
            clipRect = Rectangle()
//            clip = clipRect
            content = n
//            children.add(n)
            x = 0.0
            y = 0.0
            style = "-fx-background-color: #457349"
        }
    }

    /**
     * 分割线
     */
    internal class ContentDivider {
        /* 分割线的两个端点坐标 */
        var p1: Point2D = Point2D.ZERO
        var p2: Point2D = Point2D.ZERO
    }
}