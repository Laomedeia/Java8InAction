package org.kotlin.action

import java.io.Serializable

/**
 * 嵌套类和内部类
 */
interface State : Serializable
interface IView {
   fun getCurrentState() : State
   fun restoreState(state : State)
}


class MyButton : IView {
    override fun restoreState(state: State) {
        println("restore State")
    }

    override fun getCurrentState(): State {
       return ButtonState()
    }

    class ButtonState : State {
        fun printState() = println("hello button state")
    }

}

fun main(args: Array<String>) {
    MyButton.ButtonState().printState()
}