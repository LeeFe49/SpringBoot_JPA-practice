package com.example.practice.myPackage.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyDto {
    private final String name;
    private final int amount;
}
