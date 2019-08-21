package org.kotlin.action

/**
 * 接口和类
 * 1. final kotlin中默认类和方法是final。
 * 2.如果你允许创建一个类的子类，需要使用open 修饰符来标示这个类，另外需要给每一个可以被重写的属性或者方法添加open 修饰符
 * 3.abstract Kotlin中可以将一个类声明为abstract 这种类不能被实例化。抽象类中抽象成员始终是open的，所以不需要显示的使用open修饰符，非抽象函数并不是默认open,但是可以标注为open的
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