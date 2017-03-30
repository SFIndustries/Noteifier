package sfi.noteifier;

/**
 * Created by Jasmin on 27.3.2017..
 */

public class Point2D
{
    double x;
    double y;

    Point2D(double _x, double _y)
    {
        x = _x;
        y = _y;
    }

    Point2D(Point2D p)
    {
        x = p.x;
        y = p.y;
    }

    Point2D add(double _x, double _y)
    {
        return new Point2D(x + _x, y + _y);
    }

    Point2D add(Point2D p)
    {
        return new Point2D(x + p.x, y + p.y);
    }

    Point2D sub(double _x, double _y)
    {
        return new Point2D(x - _x, y - _y);
    }

    Point2D sub(Point2D p)
    {
        return new Point2D(x - p.x, y - p.y);
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}
