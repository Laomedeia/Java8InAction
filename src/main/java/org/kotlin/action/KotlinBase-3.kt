@file:JvmName("RenameKtFileClass")   //重命名生成的类名
package org.kotlin.action

/**
 * 函数与集合
 */
// create a hash set
val newSet = hashSetOf<String>("a","b","c","a")
// create a array list
val newArrayList = arrayListOf(1,2,3)
// create a hash map
val newHashMap = hashMapOf(1 to "ONE", 2 to "TWO", "s1" to "THREE")
// 静态final常量
const val UNIX_LINE_SEPARATOR = "\n"

fun main(args: Array<String>) {
    println(newSet.last())    //多余的a不会输出
    println(newHashMap[2])
    println(newArrayList.max())
    println(UNIX_LINE_SEPARATOR)
}