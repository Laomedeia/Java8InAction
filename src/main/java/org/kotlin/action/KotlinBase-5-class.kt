package org.kotlin.action

/**
 * 接口和类
 */
interface Clickable {
    fun click()
    fun showOff() = println("ooops, click off.")
}
interface Focusable {
    fun showOff() = println("ooops, focus off.")
}
open class Button : Clickable, Focusable {
    override fun showOff() {
        super<Focusable>.showOff()  //尖括号里面表示你要调用哪一个父类方法
    }

    override fun click() {
        println("i was clicked")
    }
    final fun disableOverride(){println("i can not be override")}
}

class ChildButton : Button() {
    override fun click() {
        println("child button was clicked")
    }
}

fun main(args: Array<String>) {
    Button().click()
    Button().showOff()
    ChildButton().click()
    ChildButton().disableOverride()
}