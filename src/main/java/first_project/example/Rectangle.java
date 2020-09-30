package first_project.example;

public class Rectangle implements Figure {
    private double l;
    private double w;

    public Rectangle(double l, double w) {
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
