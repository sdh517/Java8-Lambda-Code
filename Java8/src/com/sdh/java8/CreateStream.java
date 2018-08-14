package com.sdh.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        
        //////////////////////////////////////////////////////////////////////
        
        createStreamFromValues().forEach(System.out::println);
        
        //////////////////////////////////////////////////////////////////////
        
        createStreamFromArrays().forEach(System.out::println);
        
        //////////////////////////////////////////////////////////////////////
        
        createStreamFromFile();
        
        //////////////////////////////////////////////////////////////////////
        
        createStreamFromIterator().forEach(System.out::println);
        
        //////////////////////////////////////////////////////////////////////
        
        createStreamFromGenerate().forEach(System.out::println);
        
        //////////////////////////////////////////////////////////////////////
        
        createObjStreamFromGenerate().forEach(System.out::println);
    }
    
    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("aa", "bb", "ccc");
        return list.stream();
    }
    
    private static Stream<String> createStreamFromValues() {
        return Stream.of("aaa", "bbb", "ccc");
    }
    
    private static Stream<String> createStreamFromArrays() {
        String[] strings = {"aaaa", "bbbb", "cccc"};
        return Arrays.stream(strings);
    }
    
    private static void createStreamFromFile() {
        Path path = Paths.get("D:\\Users\\Administrator\\Documents\\workspace-sts-3.9.2.RELEASE\\Java8\\src\\com\\sdh\\java8\\ComplexApple.java");
        try (Stream<String> lines = Files.lines(path))
        {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static Stream<Integer> createStreamFromIterator() {
        return Stream.iterate(0, n -> n + 2).limit(100);
    }
    
    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random).limit(1000);
    }
    
    private static Stream<Obj> createObjStreamFromGenerate() {
        return Stream.generate(new ObjSupplier()).limit(10);
    }
    
    static class ObjSupplier implements Supplier<Obj> {
        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "Name->" + index);
        }
    }
    
    static class Obj {
        private int id;
        private String name;
        
        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj [id=" + id + ", name=" + name + "]";
        }
    }
}
