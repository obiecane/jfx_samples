package com.blueggy.jfxsamples

import com.blueggy.jfxsamples.pages.IMainPage
import com.blueggy.jfxsamples.pages.SimpleMainPage
import com.jfoenix.assets.JFoenixResources
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage


fun main(args: Array<String>) {
    Application.launch(App::class.java, *args)
}

class App : Application() {
    override fun start(ps: Stage) {
        val mainPage: IMainPage = SimpleMainPage(ps)
        ps.apply {
            this.title = "JFX Button Demo"
            this.scene = Scene(mainPage.mainDecorator(), 1000.0, 800.0).apply {
                stylesheets.addAll(
                    JFoenixResources.load("css/jfoenix-fonts.css").toExternalForm(),
                    JFoenixResources.load("css/jfoenix-design.css").toExternalForm(),
                    App::class.java.getResource("/css/components.css")!!.toExternalForm()
                )
            }
            this.show()
        }
    }
}
