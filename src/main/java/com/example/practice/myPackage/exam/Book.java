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


    public void testBranch() {
        System.out.println("브랜치 수정작업 테스트");
    }

    public void test2() {
        System.out.println("test2");
    }
}
