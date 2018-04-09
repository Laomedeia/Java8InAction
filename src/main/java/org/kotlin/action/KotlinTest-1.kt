package org.kotlin.action

/**
 * 认识函数和变量
 * 变量val对应java final变量(自身不可变，但指向的对象可能是可变的), var对应普通java变量
 */
val str = "this is a common string variable"
val num = 140
val doubleNum = 7.5e6
var myVar = 1986

fun main(args: Array<String>) {
    println("hello world")
    println(max(1, 8))
    println(max2(10, 8))
    println("hello, $str, $num, $doubleNum, $myVar")
    println("hello, ${ if (num < 100) "kill bill" else "player one" }")
}

//代码块体写法
fun max(a: Int, b: Int): Int {
    //在kotlin中，if是有结果值的表达式
    return if (a > b) a else b
}

//表达式写法
fun max2(a: Int, b: Int) = if (a > b) a else b