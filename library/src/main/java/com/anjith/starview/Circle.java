package com.anjith.starview;

/**
 * Created by anjithsasindran
 * on 27/10/17.
 */

class Circle {

    private Coordinate coordinates[];
    private float radius;
    private float centerX;
    private float centerY;
    private int angles[];

    Circle(int angles[]) {
        this.coordinates = new Coordinate[Constants.NUMBER_OF_COORDINATES];
        this.angles = angles;
    }

    float getRadius() {
        return radius;
    }

    void setRadius(float radius) {
        this.radius = radius;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    Coordinate getCoordinates(int position) {
        return coordinates[position];
    }

    void initCoordinates() {
        for (int i = 0; i < Constants.NUMBER_OF_COORDINATES; i++) {
            this.coordinates[i] = CoordinateHelper.findCoordinatesOnCircumference(angles[i],
                    this.centerX, this.centerY, this.radius);
        }
    }
}
