package first_project.example;

import java.util.Scanner;

public class Main {
    //Основной класс с методом без полей
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        Figure figure;

        System.out.println("Выберите фигуру: 1 - квадрат, 2 - прямоугольник, 3 - треуголник: ");
        String input = scanner.nextLine();
        int type = Integer.parseInt(input);
        switch (type) {
            case 1:
                System.out.println("Введите сторону квадрата:");
                double a = scanner.nextDouble();
                figure = new Square(a);
                break;
            case 2:
                System.out.println("Введите длину прямоугольника:");
                double l = scanner.nextDouble();
                System.out.println("Введите ширину прямоугольника:");
                double w = scanner.nextDouble();
                figure = new Rectangle(l, w);
                break;
            case 3:
                System.out.println("Введите первую сторону треугольника:");
                double c = scanner.nextDouble();
                System.out.println("Введите вторую сторону треугольника:");
                double d = scanner.nextDouble();
                System.out.println("Введите угол в градусах между первой и второй стороной треугольника:");
                double angle = scanner.nextDouble();
                figure = new Triangle(c, d, angle);
                break;
            default:
                throw new RuntimeException("Вы выбрали что-то не то!");
        }
        System.out.println("Площадь фигуры равна:" + figure.area());
        System.out.println("Периметр фигуры равен:" + figure.perimeter());
    }
}
