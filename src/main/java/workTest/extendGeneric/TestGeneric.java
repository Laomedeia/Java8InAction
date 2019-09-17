package workTest.extendGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型集合操作测试 (集合的协变限制)
 * @author neptune
 * @create 2019 09 06 12:29 下午
 */
public class TestGeneric {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<GaffiedCat> gaffiedCats = new ArrayList<>();
        gaffiedCats.add(new GaffiedCat("pipi", 1));

        List<? extends Cat> extendCatFromAnimalList = new ArrayList<>();
        List<? super Cat> superCatFromAnimalList = new ArrayList<>();
        GaffiedCat kido = new GaffiedCat("kido", 2);

        // ---------------------------------- 测试直接赋值操作 ----------------------------------
        // superCatFromAnimalList = gaffiedCats;   // 直接赋值编译报错，只能赋值 Cat 或 Cat 父类的集合
        superCatFromAnimalList = cats;
        //extendCatFromAnimalList = animals;  // 直接赋值编译报错，只能赋值 Cat 或 Cat 子类的集合
        extendCatFromAnimalList = gaffiedCats;

        // ---------------------------------- 测试 Add 操作 ----------------------------------
        // extendCatFromAnimalList.add(kido);  // add 编译报错。对于 < ? extends xx >, get first。适用于消费集合元素为主的场景
        superCatFromAnimalList.add(kido);  // add 编译正确。对于 < ? super xx >, put first。适用于生产集合元素为主的场景
        superCatFromAnimalList.add(new Cat());
        // superCatFromAnimalList.add(new Animal());  // add 编译报错。只能添加 Cat 或 Cat 的子类

        // ---------------------------------- 测试 get 操作 ----------------------------------
        GaffiedCat superGaffiedCat = (GaffiedCat) superCatFromAnimalList.get(0);
        superGaffiedCat.description();  // 所有的 super 操作能够返回元素，但是泛型丢失，只能返回 Object 元素，需要强制转换。
        superGaffiedCat.addTagList("No.1");
        superGaffiedCat.addTagList("Fat Cat");
        // superGaffiedCat.printList(1111); // 编译报错. 定义的泛型方法参数只适用于 String
        superGaffiedCat.tagList.forEach(t -> System.out.println(t.toString()));

        Cat extendCat = extendCatFromAnimalList.get(0);
        Animal extendAnimal = extendCatFromAnimalList.get(0);
        // GaffiedCat extendGaffiedCat = extendCatFromAnimalList.get(0);  // 编译报错, 虽然extendCatFromAnimalList是从gaffiedCats赋值而来，但类型擦除后是不知道的
        extendAnimal.description();
        extendCat.description();
    }
}
