package com.sdh.java8;

public class ComplexApple {
    
    private String color;
    private long weight;
    private String name;
    
    public ComplexApple(String color, long weight, String name) {
        super();
        this.color = color;
        this.weight = weight;
        this.name = name;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public long getWeight() {
        return weight;
    }
    public void setWeight(long weight) {
        this.weight = weight;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComplexApple [color=" + color + ", weight=" + weight + ", name=" + name + "]";
    }
}
