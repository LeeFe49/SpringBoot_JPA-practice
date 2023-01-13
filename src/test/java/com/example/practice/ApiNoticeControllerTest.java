package com.example.practice;

import com.example.practice.myPackage.Dto.MyDto;
import jdk.jfr.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApiNoticeControllerTest {
    @Test
    void notice() {
        //given
        String hello = "test";
        int amount = 1000;

        //when
        MyDto dto = new MyDto("test", 1000);

        //then
        assertEquals(dto.getName(), hello);
        assertEquals(dto.getAmount(), amount);
    }
}