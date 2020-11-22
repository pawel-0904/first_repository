package HomeTask.dto;

import HomeTask.annotations.After;
import HomeTask.annotations.Before;
import HomeTask.annotations.Test;

public class Example {
    //Класс-тест, в котором есть методы, отмеченные аннотациями
    private String name = "Default";
    @Before
    public void makeWorkBefore(){
        System.out.println("MethodBefore does '" + name + "'");
    }

    @After
    public void makeWorkAfter(){
        System.out.println("MethodAfter does '" + name + "'");
    }

    @Test
    public void makeWork1(){System.out.println("Method 1 does '" + name + "'"); }

    public void makeWork2(){
        System.out.println("Method 2 does '" + name + "'");
    }
    @Test
    public void makeWork3(){
        System.out.println("Method 3 does '" + name + "'");
    }

    public void setName(String name) {
        this.name = name;
    }
}
