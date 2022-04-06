package com.blueggy.jfxsamples.pages

import com.jfoenix.controls.*
import com.jfoenix.validation.RequiredFieldValidator
import javafx.beans.value.ObservableValue
import javafx.geometry.Insets
import javafx.geometry.Side
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.stage.Stage

fun mainDecorator(stage: Stage): JFXDecorator {
    return JFXDecorator(stage, mainPane(), false, true, true).apply {
    }
}

fun mainMenu(): MenuBar {

    fun fileMenu(): Menu {
        return Menu("File").apply {
            this.items.addAll(
                MenuItem("File")
            )
        }
    }

    return MenuBar().apply {
        this.menus.addAll(fileMenu())
    }
}

private fun mainPane(): BorderPane {
    return BorderPane().apply {
        top = mainMenu()
        center = tabPane()
    }
}

private fun testTab(): Tab {
    val pane = VBox()
    pane.spacing = 30.0
    pane.style = "-fx-background-color:WHITE;-fx-padding:40;"

    pane.children.add(TextField())

    val field = JFXTextField()
    field.isLabelFloat = true
    field.promptText = "Type Something"
    pane.children.add(field)


    val disabledField = JFXTextField()
    disabledField.style = "-fx-label-float:true;"
    disabledField.promptText = "I'm disabled.."
    disabledField.isDisable = true
    pane.children.add(disabledField)

    val validationField = JFXTextField()

    validationField.promptText = "With Validation.."
    var validator = RequiredFieldValidator()
    validator.message = "Input Required"
    validationField.validators.add(validator)
    validationField.focusedProperty()
        .addListener { _: ObservableValue<out Boolean?>?, _: Boolean?, newVal: Boolean? ->
            if (!newVal!!) {
                validationField.validate()
            }
        }
    pane.children.add(validationField)


    val passwordField = JFXPasswordField()
    passwordField.style = "-fx-label-float:true;"
    passwordField.promptText = "Password"
    validator = RequiredFieldValidator()
    validator.message = "Password Can't be empty"
    passwordField.validators.add(validator)
    passwordField.focusedProperty()
        .addListener { _: ObservableValue<out Boolean?>?, _: Boolean?, newVal: Boolean? ->
            if (!newVal!!) {
                passwordField.validate()
            }
        }
    pane.children.add(passwordField)

    return Tab("test", pane)
}

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