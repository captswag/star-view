package com.anjith.starview;

/**
 * Created by anjithsasindran
 * on 27/10/17.
 */

class Coordinate {

    private float x;
    private float y;

    Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
