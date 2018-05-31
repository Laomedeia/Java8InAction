package org.kotlin.action

import java.io.BufferedReader
import java.io.StringReader
import java.util.*


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
    println("hello, ${if (num < 100) "kill bill" else "player one"}")
    println(Person("Felipe", true))
    println(Person("Felipe", true).name)
    println(Rectangle(10, 12).isSquare)
    println(testWhen(Color.BLACK))
    println(eval(Sum(Sum(Num(1), Num(2)), Num(5))))
    testInterval()
    loopMap()
    loopList()
    readNumber(BufferedReader(StringReader("323a")))
}

// 代码块体写法
fun max(a: Int, b: Int): Int {
    //在kotlin中，if是有结果值的表达式
    return if (a > b) a else b
}

// 表达式写法
fun max2(a: Int, b: Int) = if (a > b) a else b

// when关键字
fun testWhen(color: Color) =
        when (color) {
            Color.RED -> "hello"
            Color.ORANGE -> "world"
            else -> "paradise"
//        Color.BLACK,Color.WHITE -> "hero"
        }

// ..运算符
fun testInterval() {
    for (i in 1..5) {
        print(i)
    }
    for (i in 10 downTo 1 step 2) {
        print(i)
    }
}

// 迭代map
fun loopMap() {
    val map = TreeMap<Char,String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        map[c] = binary
    }
    for ((letter, charCode) in map) {
        println("$letter is $charCode")
    }
}

// 迭代list
fun loopList() {
    //如果是set，用setOf
    val list = arrayListOf<String>("aaa","bbb","ccc")
    // 迭代集合是使用下标 withIndex
    for ((index, value) in list.withIndex()) {
        println("$index is $value")
    }
}

// 异常
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}

class Person(val name: String, var isMarried: Boolean)

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
//        get() { return height == width }
}

enum class Color { RED, ORANGE, BLACK, WHITE }