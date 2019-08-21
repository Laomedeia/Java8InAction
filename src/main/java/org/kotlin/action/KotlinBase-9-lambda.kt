package org.kotlin.action

import java.lang.StringBuilder
import javax.swing.text.html.HTML.Tag.P


data class Cat (
    val name: String,
    val age: Int
)

fun findTheOldest(people: List<Cat>) {
//  1. 未使用 lambda
//    var maxAge = 0
//    var theOldest: Cat? = null
//    for (person in people) {
//        if (person.age > maxAge) {
//            maxAge = person.age
//            theOldest = person
//        }
//    }
//    println(theOldest)

//    2. 使用 lambda
    println(people.maxBy { it.age } )
    println(people.maxBy(Cat::age))


    // 3. join
    println(people.joinToString(".", transform = { cat:Cat -> cat.name}))

    // 4. filter
    println(people.filter { it.age < 3 })

    // 5. map
    println(people.map { it.name })

    // 8. all, any, count, find
    println(people.count { it.age > 0 })
    println(people.all { it.age > 0 })
    println(people.any { it.age > 0 })
    println(people.find { it.age == 5 })

    // 9. groupby
    println(people.groupBy { it.age })

    // 11. 先 map 后 filter 会产生一个临时集合。如果用序列就不会
    println(people.map(Cat::name).filter { it.startsWith("c") }.toList())

    // 12. 惰性求值。用 sequence 操作创建避免产生临时集合，提高效率（针对于包含元素特别多的列表转换场景）
    println(people.asSequence().map(Cat::name).filter { it.startsWith("c") }.toList())
}

// 14. with 函数的使用,可以看到 return 关键字都不用写
fun stringBuild() = with(StringBuilder()) {
    for (letter in 'A' .. 'Z') {
        append(letter)
    }
    append("\nNow I know the「with」ABC")
    toString()
}

// 15. apply 函数的使用
fun stringBuild2() = StringBuilder().apply {
    for (letter in 'A' .. 'Z') {
        append(letter)
    }
    append("\nNow I know the「apply」ABC")
}.toString()

// 16. 使用 buildString内置函数更加简化上述 with 和 apply 的例子
fun stringBuild3() = buildString {
    for (letter in 'A' .. 'Z') {
        append(letter)
    }
    append("\nNow I know the ABC")
}

val sum = { x:Int, y: Int -> x + y }
val numbers = mapOf(0 to "zero", 1 to "one", 2 to "two")
val strings = listOf("abc", "dbf")
val pivot = 8
fun main(args: Array<String>) {
    val catList = arrayListOf(Cat("candy", 2), Cat("tom",5), Cat("hero",5))
    findTheOldest(catList)
    // 6. lambda 直接求和
    println(sum(1,3))

    // 7. map 过滤
    println(numbers.mapValues { it.value.toUpperCase() })

    // 10. flatMap, flatten
    println(strings.flatMap { it.toList() })

    // 11. 及早求值和惰性求值的区别
    println(listOf(1,2,3,4).map { it * it }.find { it > 3 }) // 先所有元素平方转换成新列表，然后找出值 > 3 的结果值 4 返回结果值 4 ( 2 * 2 = 4)
    println(listOf(1,2,3,4).asSequence().map { it * it }.find { it > 3 }) // 依次查找所有元素平方，一旦有找到值 > 3 ，返回结果值 4 ( 2 * 2 = 4)

    // 12. 范围过滤
    println(listOf(1,2,3,4,5,6,7,8,9,10).filter { it in 1..(pivot - 1) })

    // 13. 计算 1 到 100 自然数求和.这里用到 sequence 会延期求值
    val natureNm = generateSequence(0) { it + 1 }
    val numTo100 = natureNm.takeWhile { it <= 100 }
    println(numTo100.sum())

    // 14. with 函数的使用
    println(stringBuild())

    // 15. apply 函数的使用
    println(stringBuild2())

    // 16. buildString 内置函数使用
    println(stringBuild3())
}