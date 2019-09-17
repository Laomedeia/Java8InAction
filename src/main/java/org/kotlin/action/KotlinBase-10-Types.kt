package org.kotlin.action

import java.lang.IllegalArgumentException

// 1. 简化非空检查
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)
}

class Address(val streetAddress: String, val zipCde: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Employee(val name: String, val company: Company?)

// 2. 链式安全调用处理多个可空属性
fun Employee.countryName() = company?.address?.country?: "unknown"  // Elvis 运算符 ?: 简化 if null 判断
//fun Employee.countryName(): String {
//    val country = this.company?.address?.country
//    return if (country != null) country else "unknown"
//}
fun printShippingLabel(employee: Employee) {
    val address = employee.company?.address?: throw IllegalArgumentException("no address")
    // 使用 with 函数可简化代码，避免连续写 4 次 address.
    with(address) {
        println(streetAddress)
        println("$zipCde $city,$country")
    }
}
fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

// 5. 泛型类型参数 默认都是可空的，尽管 T 后面并没有问号
fun <T> printHashcode(t: T) {
    println(t?.hashCode())
}

fun main(args: Array<String>) {
    printAllCaps("s.milinkovic")
    printAllCaps(null)
    
    val employee = Employee("Correa", null)
    println(employee.countryName())

    val employee2 = Employee("Immobile", Company("club", Address("st.no1", 61110, "roma", "italy")))
    printShippingLabel(employee2)

    // 3. let 函数测试
    var email: String? = "yahoo@yahoo.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) }  // 什么都不会发生

    // 4. 可空类型扩展函数
    if ("".isNullOrEmpty() ) println("hello i am empty")
    if (null.isNullOrBlank()) println("hello i am null, but will not throw error")

    printHashcode(null)
}