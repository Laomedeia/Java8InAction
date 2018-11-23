package org.kotlin.action

import java.io.Serializable

/**
 * 数据类和类委托
 */
class CountingSet<T> (val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        val sum = innerSet.addAll(elements)
        return sum
    }
}

fun main(args: Array<String>) {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1,1,2))
    println("${cset.objectAdded} objects, ${cset.size} remain" )
    cset.forEach{ i -> println(i)}
}