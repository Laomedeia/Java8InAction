package lambdasinaction.chap8;


import java.util.*;

import static java.util.Comparator.comparing;

public class Debugging{
    public static void main(String[] args) {
        // compare point
        Point point1 = new Point(10, 12);
        Point point2 = new Point(10, 18);
        int result = Point.compareXAndY.compare(point1, point2);
        System.out.println(result);

        // debug test
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(p -> p.getX()).forEach(System.out::println);
    }


    private static class Point{
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.x = y;
        }

        public final static Comparator<Point> compareXAndY = comparing(Point::getX).thenComparing(Point::getY);

    }
}
