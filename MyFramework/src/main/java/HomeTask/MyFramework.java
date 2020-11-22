package HomeTask;

import HomeTask.annotations.After;
import HomeTask.annotations.Before;
import HomeTask.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Constructor;

public class MyFramework {
    //Тестовый Framework
    public static void RunMyFramework(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("HomeTask.dto." + className);
        Constructor<?> constructor = clazz.getConstructor();

        int countTest = 0; //кол-во тестов
        int successTest = 0; //кол-во успешных тестов
        int errorTest = 0; //кол-во неуспешных тестов

        //Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
        Field fieldName = clazz.getDeclaredField("name"); //понятно, что если в передваемом классе не будет поля name, то упадем с ошибкой, но для примера сойдет)

        //соберем методы отмеченные аннотациями
        List<Method> beforeMethods = getMethodAnnotation(clazz, Before.class);
        List<Method> testMethods = getMethodAnnotation(clazz, Test.class);
        List<Method> afterMethods = getMethodAnnotation(clazz, After.class);

        for (Method tempMethod : testMethods) {
            try {
                countTest++;
                System.out.println("====================================>");
                System.out.println("Название теста: " + tempMethod.getName());

                Object object = constructor.newInstance();
                //setNameMethod.invoke(object, tempMethod.getName());
                fieldName.setAccessible(true);
                fieldName.set(object, tempMethod.getName());

                //Выполняем все Before
                for (Method method : beforeMethods){
                    System.out.println("Выполнение метода Before:" + method.getName());
                    method.invoke(object);
                }

                //Выполняем сам Test
                System.out.println("выполнения метода Test: " + tempMethod.getName());
                tempMethod.invoke(object);

                //Выполняем все After
                for (Method method : afterMethods){
                    System.out.println("Выполнение метода After:" + method.getName());
                    method.invoke(object);
                }
                System.out.println("<====================================");
                successTest++;
            } catch (Exception e) {
                errorTest++;
                System.out.println("Ошибка метода:" + tempMethod.getName());
            }
        }
        System.out.println("Всего тестов:" + countTest);
        System.out.println("Успешных тестов:" + successTest);
        System.out.println("Тесты упали с ошибкой:" + errorTest);
    }

    private static List<Method> getMethodAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {

        List<Method> methods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }
        return methods;
    }
}
