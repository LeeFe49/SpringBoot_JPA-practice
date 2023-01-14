package com.example.practice.myPackage.exam;

public class Book {

    private String name;
    private long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Book(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public void test() {
        System.out.println("브랜치 생성 테스트!");
    }
}
