package com.ani.accgame.graphics;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.PointF;
import android.util.SizeF;

public class Animation {

    private static final int DEF_DURATION = 1000;

    private final int resourceId;

    private Movie movie;

    private long startTime;

    public Animation(int resourceId) {
        this.resourceId = resourceId;
    }

    public void init(Resources resources) {

        movie = Movie.decodeStream(resources.openRawResource(resourceId));
        startTime = android.os.SystemClock.uptimeMillis();
    }

    public void draw(Canvas canvas, PointF position) {
        int duration = movie.duration();

        if (duration == 0) {
            duration = DEF_DURATION;
        }

        // GIF animation part
        movie.setTime((int) (android.os.SystemClock.uptimeMillis() - startTime) % duration);

        movie.draw(canvas, position.x - (movie.width() / 2f), position.y - (movie.height() / 2f));
    }

    public SizeF getSize() {
        return new SizeF(movie.width(), movie.height());
    }
}
