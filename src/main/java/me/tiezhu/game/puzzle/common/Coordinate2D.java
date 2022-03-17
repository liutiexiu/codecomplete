package me.tiezhu.game.puzzle.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author liushuai
 * Created on 2022/2/10
 */
public class Coordinate2D implements ICoordinate {

    private int x;
    private int y;

    public Coordinate2D() {
        this(0, 0);
    }

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
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
        this.y = y;
    }

    public Coordinate2D clone() {
        return new Coordinate2D(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Coordinate2D)) return false;

        Coordinate2D that = (Coordinate2D) o;

        return new EqualsBuilder().append(x, that.x).append(y, that.y).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(x).append(y).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("x", x)
                .append("y", y)
                .toString();
    }
}
