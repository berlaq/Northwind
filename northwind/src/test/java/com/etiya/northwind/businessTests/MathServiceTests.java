package com.etiya.northwind.businessTests;

import com.etiya.northwind.Business.Abstracts.MathService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MathServiceTests {

    MathService mathService = new MathService();

    @BeforeEach
    public void setup(){
        mathService = new MathService();
    }

    @Test
    public void five_plus_six_is_eleven(){
        //arrange //given
        int num1=5;
        int num2=6;
        int expected = 11;

        //act //when
        int actual = mathService.add(num1,num2);

        //assert //then
        Assertions.assertEquals(expected,actual);

    }

    @Test
    public void minus_ten_plus_six_is_four(){
        //arrange //given
        int num1=-10;
        int num2=6;
        int expected = -4;

        //act //when
        int actual = mathService.add(num1,num2);

        //assert //then
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void minus_ten_multiplyBy_six_is_minus_sixty(){
        //arrange //given
        int num1=-10;
        int num2=6;
        int expected = -60;

        //act //when
        int actual = mathService.multiply(num1,num2);

        //assert //then
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void five_multiplyBy_six_is_thirty(){
        //arrange //given
        int num1=5;
        int num2=6;
        int expected = 30;

        //act //when
        int actual = mathService.multiply(num1,num2);

        //assert //then
        Assertions.assertEquals(expected,actual);

    }
}
