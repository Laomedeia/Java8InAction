package workTest.extendGeneric;

import lombok.Data;

import java.util.List;

/**
 * @author neptune
 * @create 2019 09 06 12:10 下午
 */
public class Animal {
    String name;
    int age;
    void description(){};
    <T extends String> void addTagList(T t){};
}
