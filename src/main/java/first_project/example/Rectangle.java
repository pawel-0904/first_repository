package first_project.example;

public class Rectangle implements Figure {
    private double l;
    private double w;

    public Rectangle(double l, double w) {
        if (l < 0 || w < 0) {
            throw new IllegalArgumentException("Параметры фигуры не могут отрицатльными!");
        }
        this.l = l;
        this.w = w;
    }

    @Override
    public double area() {
        return l * w;
    }

    @Override
    public double perimeter() {
        return 2 * (l + w);
    }
}
