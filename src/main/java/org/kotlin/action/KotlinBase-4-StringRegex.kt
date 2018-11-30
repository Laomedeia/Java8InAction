package org.kotlin.action

/**
 * 字符串/正则式处理
 */

// 字符串分隔
fun makeStrSplit(): List<String>  {
    return "123.34-A.3".split(".")   // ".".toRegex() 转变成正则式匹配
}

// 解析字符串
fun parseStr()  {
    val url = "/User/Neptune/CCC/xxx.doc"
    // 方法1
    val path = url.substringBeforeLast("/")
    val fullName = url.substringAfter("/")
    val fileName = fullName.substringBeforeLast(".")
    val fileExt = fullName.substringAfterLast(".")
    println("path: $path, file name: $fileName, extension: $fileExt")

    // 方法2
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    var matchEntire = regex.matchEntire(url)
    if (matchEntire != null) {
        val (dir, filename, extension) = matchEntire.destructured
        println("dir: $dir, file name: $filename, extension: $extension")
    }

}


fun main(args: Array<String>) {
    println(makeStrSplit())
    parseStr()
}
