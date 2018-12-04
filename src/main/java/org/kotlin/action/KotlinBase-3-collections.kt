@file:JvmName("RenameKtFileClass")   //重命名生成的类名
package org.kotlin.action

import workTest.shortIDAutoGen.ShareCodeUtil2
import java.security.SecureRandom


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
// 中缀调用
infix fun Any.to(other:Any) = Pair(this, other)
val numberName = 1 to "hello"

// 扩展函数
fun String.lastChar(): Char = get(length - 1)  //this.get(this.length - 1)
// 扩展函数2 - string splitter
fun <T> Collection<T>.joinToString(separator:String = "", prefix:String = "", suffix:String = "") : String {
    val result = StringBuilder(prefix);
    for ((index, item) in withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(item)
    }
    result.append(suffix)
    return result.toString()
}
// loop query shortid
fun testLoopShortids() {
    val random = SecureRandom()
    val alphabet = charArrayOf('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')
    val size = 6
    var set = hashSetOf<String>()
    for (i in 1..10000000) {
//        var randomNanoId = NanoIdUtils.randomNanoId(random, alphabet, size)
//        var randomNanoId = ShareCodeUtil.idToCode(i.toLong(), 33554432L)
//        var randomNanoId = MD5Encoder.encode("SHA1", time).substring(0,6)
        var randomNanoId = ShareCodeUtil2.toSerialCode(i.toLong())
//        println(randomNanoId)
        set.add(randomNanoId)
        //println("auto generate short id:"+NanoIdUtils.randomNanoId(random,alphabet,size))
    }
    println("set size:"+set.size)
}



fun main(args: Array<String>) {
    println(newSet.last())    //多余的a不会输出
    println(newHashMap[2])
    println(newArrayList.max())
    println(UNIX_LINE_SEPARATOR)
    println("Kotlin".lastChar())
    println(listOf(1,2,3).joinToString("@"))
    println(numberName.first)
//    println("auto generate short id:"+ShortId.generate())
    testLoopShortids()

}
