package com.winterbe.java8.samples.stream;

import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Optional1 {

    public static void main(String[] args) {
        String name = "bam";
//        String name = null;
        Optional<String> optional = Optional.ofNullable(name);
        optional.isPresent();           // true
//        optional.get();                 // "bam"
        String newName = optional.orElse("hhehehe");    // "bam"
        String newName1 = optional.orElseGet(() -> new String("zzz"));    // "bam"
        System.out.println(newName);
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

}