package org.kotlin.action

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File
import java.io.Serializable

/**
 * object 关键字
 */
// 直接创建
object CaseInsensitiveFileComparator: Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, true)
    }
}

// 嵌套类创建
data class MyPerson(val name: String) {
    object NameComparator: Comparator<MyPerson> {
        override fun compare(o1: MyPerson, o2: MyPerson): Int = o1.name.compareTo(o2.name)

    }
}

// 伴生对象-可创建工厂方法和静态成员
class User private constructor(val nickname : String) {
    // 声明伴生对象
    companion object {
        fun newSubscribingUser(email: String): User {
            return User(email.substringBeforeLast('@'))
        }
        fun newFacebookUser(accountId: Int) {
            //User(getFacebookName(accountId))
        }
    }
}

// 对象表达式
val listener = object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
    }

    override fun mouseEntered(e: MouseEvent?) {
        super.mouseEntered(e)
    }
}

fun main(args: Array<String>) {
    // 直接调用对象compare方法
    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))
    // 利用sortedWith返回特定比较器排序过的列表
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))
    // 伴生对象调用
    val user1 = User.newSubscribingUser("bob@gmail.com")
    println(user1.nickname)
}