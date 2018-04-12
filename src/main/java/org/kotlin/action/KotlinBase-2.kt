package org.kotlin.action

/**
 * 接口及接口方法定义
 */
interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// eval写法1
//fun eval(e: Expr): Int {
//    if (e is Num) {
//        val n = e as Num
//        return n.value
//    }
//    if (e is Sum) {
//        return eval(e.left) + eval(e.right)
//    }
//    throw IllegalArgumentException("unknow expression")
//}

// eval写法2
//fun eval(e: Expr): Int =
//        if (e is Num) {
//            e.value
//        } else if (e is Sum) {
//            eval(e.left) + eval(e.right)
//        } else {
//            throw IllegalArgumentException("unknow expression")
//        }

// eval写法2
fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.left) + eval(e.right)
            else -> throw IllegalArgumentException("unknow expression")
        }