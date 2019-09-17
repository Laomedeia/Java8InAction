package workTest.extendGeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neptune
 * @create 2019 09 06 12:23 下午
 */
public class GaffiedCat extends Cat {

    List tagList = new ArrayList<Object>();

    GaffiedCat(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    void description() {
        super.description();
        System.out.println("Hi, I am a cute Gaffied, my name is:" + name +", and age is:" + age);
    }

    @Override
    <T extends String> void addTagList(T t) {
        tagList.add(t);
    }
}
