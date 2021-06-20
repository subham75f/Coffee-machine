package com.java.coffeeMachine;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CoffeeMachineTest {
    private CoffeeMachine coffeeMachine;

    @Before
    public void setUp() {
    }

    //Three solutions are possible ( 2 combinations each of missing elements)
    @Test
    public void getMaximumBeveragesServedMultipleSolutions() {
        Map<String, Integer>  totalQuantities = new HashMap<>();
        totalQuantities.put("hot_water", 500);
        totalQuantities.put("hot_milk", 500);
        totalQuantities.put("ginger_syrup", 100);
        totalQuantities.put("sugar_syrup", 100);
        totalQuantities.put("tea_leaves_syrup", 100);

        Map<StringBuilder, Integer>  temp = new HashMap<>();
        temp.put(new StringBuilder("hot_water"), 200);
        temp.put(new StringBuilder("hot_milk"), 100);
        temp.put(new StringBuilder("ginger_syrup"), 10);
        temp.put(new StringBuilder("sugar_syrup"), 10);
        temp.put(new StringBuilder("tea_leaves_syrup"), 30);

        Map<StringBuilder, Integer>  temp1 = new HashMap<>();
        temp1.put(new StringBuilder("hot_water"), 100);
        temp1.put(new StringBuilder("hot_milk"), 400);
        temp1.put(new StringBuilder("ginger_syrup"), 30);
        temp1.put(new StringBuilder("sugar_syrup"), 50);
        temp1.put(new StringBuilder("tea_leaves_syrup"), 30);

        Map<StringBuilder, Integer>  temp2 = new HashMap<>();
        temp2.put(new StringBuilder("hot_water"), 300);
        temp2.put(new StringBuilder("ginger_syrup"), 30);
        temp2.put(new StringBuilder("sugar_syrup"), 50);
        temp2.put(new StringBuilder("tea_leaves_syrup"), 30);

        Map<StringBuilder, Integer>  temp3 = new HashMap<>();
        temp3.put(new StringBuilder("hot_water"), 100);
        temp3.put(new StringBuilder("ginger_syrup"), 30);
        temp3.put(new StringBuilder("sugar_syrup"), 50);
        temp3.put(new StringBuilder("green_mixture"), 30);

        Map<String, Map<StringBuilder, Integer>> beverages = new HashMap<>();
        beverages.put("hot_tea", temp);
        beverages.put("hot_coffee", temp1);
        beverages.put("black_tea", temp2);
        beverages.put("green_tea", temp3);


        coffeeMachine = new CoffeeMachine(4, totalQuantities, beverages);

        Map<String, String> outputExpected = new HashMap<>();
        outputExpected.put("hot_tea", "hot_tea is prepared");
        outputExpected.put("hot_coffee", "hot_coffee is prepared");
        outputExpected.put("black_tea", "black_tea cannot be prepared because item sugar_syrup, hot_water not sufficient");
        outputExpected.put("green_tea", "green_tea cannot be prepared because green_mixture not available");

        Map<String, String> outputExpected1 = new HashMap<>();
        outputExpected1.put("hot_tea", "hot_tea cannot be prepared because item sugar_syrup, hot_water not sufficient");
        outputExpected1.put("hot_coffee", "hot_coffee is prepared");
        outputExpected1.put("black_tea", "black_tea is prepared");
        outputExpected1.put("green_tea", "green_tea cannot be prepared because green_mixture not available");

        Map<String, String> outputExpected2 = new HashMap<>();
        outputExpected2.put("hot_tea", "hot_tea is prepared");
        outputExpected2.put("hot_coffee", "hot_coffee cannot be prepared because item sugar_syrup, hot_water not sufficient");
        outputExpected2.put("black_tea", "black_tea is prepared");
        outputExpected2.put("green_tea", "green_tea cannot be prepared because green_mixture not available");

        Map<String, String> outputExpected3 = new HashMap<>();
        outputExpected3.put("hot_tea", "hot_tea is prepared");
        outputExpected3.put("hot_coffee", "hot_coffee is prepared");
        outputExpected3.put("black_tea", "black_tea cannot be prepared because item hot_water, sugar_syrup not sufficient");
        outputExpected3.put("green_tea", "green_tea cannot be prepared because green_mixture not available");

        Map<String, String> outputExpected4 = new HashMap<>();
        outputExpected4.put("hot_tea", "hot_tea cannot be prepared because item hot_water, sugar_syrup not sufficient");
        outputExpected4.put("hot_coffee", "hot_coffee is prepared");
        outputExpected4.put("black_tea", "black_tea is prepared");
        outputExpected4.put("green_tea", "green_tea cannot be prepared because green_mixture not available");

        Map<String, String> outputExpected5 = new HashMap<>();
        outputExpected5.put("hot_tea", "hot_tea is prepared");
        outputExpected5.put("hot_coffee", "hot_coffee cannot be prepared because item hot_water, sugar_syrup not sufficient");
        outputExpected5.put("black_tea", "black_tea is prepared");
        outputExpected5.put("green_tea", "green_tea cannot be prepared because green_mixture not available");


        Map<String, String> actualOutput = coffeeMachine.getMaximumBeveragesServed();
        assertTrue(Arrays.asList(outputExpected, outputExpected1, outputExpected2, outputExpected3, outputExpected4, outputExpected5).contains(actualOutput));
    }

    ////One solution is possible ( 6 combinations of that)
    @Test
    public void getMaximumBeveragesServedOneSolution() {
        Map<String, Integer>  totalQuantities = new HashMap<>();
        totalQuantities.put("hot_water", 500);
        totalQuantities.put("hot_milk", 500);
        totalQuantities.put("ginger_syrup", 100);
        totalQuantities.put("sugar_syrup", 100);
        totalQuantities.put("tea_leaves_syrup", 100);

        Map<StringBuilder, Integer>  temp = new HashMap<>();
        temp.put(new StringBuilder("hot_water"), 200);
        temp.put(new StringBuilder("hot_milk"), 100);
        temp.put(new StringBuilder("ginger_syrup"), 10);
        temp.put(new StringBuilder("sugar_syrup"), 10);
        temp.put(new StringBuilder("tea_leaves_syrup"), 50);

        Map<StringBuilder, Integer>  temp1 = new HashMap<>();
        temp1.put(new StringBuilder("hot_water"), 100);
        temp1.put(new StringBuilder("hot_milk"), 400);
        temp1.put(new StringBuilder("ginger_syrup"), 30);
        temp1.put(new StringBuilder("sugar_syrup"), 50);
        temp1.put(new StringBuilder("tea_leaves_syrup"), 30);

        Map<StringBuilder, Integer>  temp2 = new HashMap<>();
        temp2.put(new StringBuilder("hot_water"), 200);
        temp2.put(new StringBuilder("ginger_syrup"), 30);
        temp2.put(new StringBuilder("sugar_syrup"), 30);
        temp2.put(new StringBuilder("tea_leaves_syrup"), 30);

        Map<StringBuilder, Integer>  temp3 = new HashMap<>();
        temp3.put(new StringBuilder("hot_water"), 100);
        temp3.put(new StringBuilder("ginger_syrup"), 30);
        temp3.put(new StringBuilder("sugar_syrup"), 50);
        temp3.put(new StringBuilder("tea_leaves_syrup"), 20);

        Map<String, Map<StringBuilder, Integer>> beverages = new HashMap<>();
        beverages.put("hot_tea", temp);
        beverages.put("hot_coffee", temp1);
        beverages.put("black_tea", temp2);
        beverages.put("green_tea", temp3);


        coffeeMachine = new CoffeeMachine(4, totalQuantities, beverages);

        Map<String, String> outputExpected = new HashMap<>();
        outputExpected.put("hot_tea", "hot_tea is prepared");
        outputExpected.put("hot_coffee", "hot_coffee cannot be prepared because item tea_leaves_syrup, sugar_syrup, hot_water not sufficient");
        outputExpected.put("black_tea", "black_tea is prepared");
        outputExpected.put("green_tea", "green_tea is prepared");

        Map<String, String> outputExpected1 = new HashMap<>();
        outputExpected1.put("hot_tea", "hot_tea is prepared");
        outputExpected1.put("hot_coffee", "hot_coffee cannot be prepared because item tea_leaves_syrup, hot_water, sugar_syrup not sufficient");
        outputExpected1.put("black_tea", "black_tea is prepared");
        outputExpected1.put("green_tea", "green_tea is prepared");

        Map<String, String> outputExpected2 = new HashMap<>();
        outputExpected2.put("hot_tea", "hot_tea is prepared");
        outputExpected2.put("hot_coffee", "hot_coffee cannot be prepared because item hot_water, tea_leaves_syrup, sugar_syrup not sufficient");
        outputExpected2.put("black_tea", "black_tea is prepared");
        outputExpected2.put("green_tea", "green_tea is prepared");

        Map<String, String> outputExpected3 = new HashMap<>();
        outputExpected3.put("hot_tea", "hot_tea is prepared");
        outputExpected3.put("hot_coffee", "hot_coffee cannot be prepared because item hot_water, sugar_syrup, tea_leaves_syrup not sufficient");
        outputExpected3.put("black_tea", "black_tea is prepared");
        outputExpected3.put("green_tea", "green_tea is prepared");

        Map<String, String> outputExpected4 = new HashMap<>();
        outputExpected4.put("hot_tea", "hot_tea is prepared");
        outputExpected4.put("hot_coffee", "hot_coffee cannot be prepared because item sugar_syrup, tea_leaves_syrup, hot_water not sufficient");
        outputExpected4.put("black_tea", "black_tea is prepared");
        outputExpected4.put("green_tea", "green_tea is prepared");

        Map<String, String> outputExpected5 = new HashMap<>();
        outputExpected5.put("hot_tea", "hot_tea is prepared");
        outputExpected5.put("hot_coffee", "hot_coffee cannot be prepared because item sugar_syrup, hot_water, tea_leaves_syrup not sufficient");
        outputExpected5.put("black_tea", "black_tea is prepared");
        outputExpected5.put("green_tea", "green_tea is prepared");

        Map<String, String> actualOutput = coffeeMachine.getMaximumBeveragesServed();
        assertTrue(Arrays.asList(outputExpected, outputExpected1, outputExpected2, outputExpected3, outputExpected4, outputExpected5).contains(actualOutput));
    }
}