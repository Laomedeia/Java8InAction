package org.kotlin.action

/**
 * 作用域函数
 */

class TestBean {
    var name: String = "xuyisheng"
    var age: Int = 18
}
fun main(args: Array<String>) {
    val test = TestBean()
    // 1. run()
    val resultRun = test.run {
        name = "xys"
        age = 3
        println("Run内部 $this")
        age
    }
    println("run返回值 $resultRun")

    // 2. let() let和run的返回值相同，它们的区别主要在于作用域内使用it和this的区别。一般来说，如果调用者的属性和类中的属性同名，则一般会使用let，避免出现同名的赋值引起混乱。
    //  国际惯例，run通常使用在链式调用中，进行数据处理、类型转换，例如?.run{}的使用。
    val resultLet = test.let {
        it.name = "xys"
        it.age = 3
        println("let内部 $it")
        it.age
    }
    println("let返回值 $resultLet")

    // 3. apply()
    val resultApply = test.apply {
        name = "xys"
        age = 3
        println("apply内部 $this")
        age
    }
    println("apply返回值 $resultApply")

    // 4. also()
    val resultAlso = test.also {
        it.name = "xys"
        it.age = 3
        println("also内部 $it")
        it.age
    }
    println("also返回值 $resultAlso")

    // 5. with()
    val resultWith = with(test) {
        name = "xys"
        age = 3
        println("with内部 $this")
        age
    }
    println("with返回值 $resultWith")

    // 6.takeIf() 条件为真返回对象本身否则返回null。
    test.age = 33
    val resultTakeIf = test.takeIf {
        it.age > 3
    }
    println("takeIf $resultTakeIf")

    // 7. takeUnless() 条件为真返回null否则返回对象本身。
    val resultTakeUnless = test.takeUnless {
        it.age > 3
    }
    println("takeUnless $resultTakeUnless")

    // 8. also & apply
    //虽然also和apply都是返回this，但国际惯例，它们在使用的时候，还是有一些细微的差别的，also强调的是【与调用者无关的操作】，而apply强调的是【调用者的相关操作】，例如下面的这个例子。
    test?.also {
        println("some log")
    }?.apply {
        name = "xys"
    }

}
