package first_project.example;

import sun.jvm.hotspot.types.JDoubleField;

import static java.lang.Math.*;

public class Triangle implements Figure {
    private double a;
    private double b;
    private double radians;
    private double c;

    public Triangle(double a, double b, double angle) {
        if (angle > 180) {
            throw new IllegalArgumentException("Невозможно создать треугольник с углом больше 180 градусов!");
        }
        if (a < 0 || b < 0 || angle < 0) {
            throw new IllegalArgumentException("Параметры треугольника не могут отрицатльными!");
        }
        this.a = a;
        this.b = b;
        this.radians = toRadians(angle); //переведем градусы в радианы, как по-другому - не знаю
        this.c = sqrt(a * a + b * b - 2 * a * b * cos(radians));
    }

    @Override
    public double area() {
        return 0.5 * a * b * sin(radians);
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }
}
