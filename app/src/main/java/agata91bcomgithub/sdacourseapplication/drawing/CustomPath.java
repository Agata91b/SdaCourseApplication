package agata91bcomgithub.sdacourseapplication.drawing;

import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.ColorInt;

/**
 * Created by RENT on 2017-02-18.
 */

public class CustomPath {

    @ColorInt
    private int color;
    private Path path;
    private Point point;

    public CustomPath(int color, Point point) {
        this.color = color;
        this.path = new Path();
        this.path.moveTo(point.x, point.y);
        this.point = point;
    }



    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
