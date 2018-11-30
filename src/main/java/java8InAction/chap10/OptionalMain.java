package java8InAction.chap10;

import java.util.*;

/**
 * Optional测试.（可optinal看成只含一个元素的stream）
 */
public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

//    public Set<String> getCarInsuranceNames(List<Person> persons) {
//        return persons.stream()
//                      .map(Person::getCar)
//                      .map(optCar -> optCar.flatMap(Car::getInsurance))
//                      .map(optInsurance -> optInsurance.map(Insurance::getName))
//                      .flatMap(Optional::stream)
//                      .collect(toSet());
//    }

    // filter过滤
    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Insurance findCheapestInsurance(Person p, Car c) {
        return new Insurance();
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance2(
            Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

}
