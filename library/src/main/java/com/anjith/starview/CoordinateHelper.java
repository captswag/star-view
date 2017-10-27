package com.anjith.starview;

/**
 * Created by anjithsasindran
 * on 27/10/17.
 */

class CoordinateHelper {

    /**
     * Finds radius of the inner circle
     * https://diplograph.net/posts/drawing_a_regular_star_in_illustrator
     *
     * @param outerCircleRadius Radius of the outer circle
     * @return Radius of the inner circle
     */
    static float findInnerCircleRadius(float outerCircleRadius) {
        final int angle1 = 18;
        double angle1InRadians = Math.toRadians(angle1);

        final int angle2 = 126;
        double angle2InRadians = Math.toRadians(angle2);

        return (float) (outerCircleRadius * (Math.sin(angle1InRadians) / Math.sin(angle2InRadians)));
    }

    /**
     * Finds Coordinates of a point on the circle's boundary.
     * https://en.wikipedia.org/wiki/Circle#Equations
     * https://stackoverflow.com/questions/839899/how-do-i-calculate-a-point-on-a-circle-s-circumference
     *
     * @param angle   Angle of the line from center to the boundary point
     * @param centerX CenterX of the circle
     * @param centerY CenterY of the circle
     * @param radius  Radius of the circle
     * @return Coordinate object containing x and y coordinates
     */
    static Coordinate findCoordinatesOnCircumference(int angle, float centerX, float centerY, float radius) {
        double angleInRadians = Math.toRadians(angle);

        float x = (float) (centerX + (radius * Math.cos(angleInRadians)));
        float y = (float) (centerY + (radius * Math.sin(angleInRadians)));

        return new Coordinate(x, y);
    }
}
