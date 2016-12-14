package com.app.gitevent.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

/**
 * Created by niranjan on 12/14/16.
 */

public class RoundedCornersTransform {
    public static Transformation getTransformation(final ImageView imageView) {
        return new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());

                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                float actualHeight = source.getHeight();
                float actualWidth = source.getWidth();
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();

                float maxWidth = imageView.getWidth();
                float maxHeight = (int) (maxWidth * aspectRatio);

                float maxRatio = maxWidth / maxHeight;

                if (source.getHeight() > maxHeight || source.getWidth() > maxWidth) {
                    if (aspectRatio < maxRatio) {
                        aspectRatio = maxHeight / source.getHeight();
                        actualWidth = (float) (aspectRatio * source.getWidth());
                        actualHeight = (float) maxHeight;
                    } else if (aspectRatio > maxRatio) {
                        aspectRatio = maxWidth / source.getWidth();
                        actualHeight = (float) (aspectRatio * source.getHeight());
                        actualWidth = (float) maxWidth;
                    } else {
                        actualHeight = (int) maxHeight;
                        actualWidth = (int) maxWidth;

                    }
                }
                if (actualHeight == 0)
                    actualHeight = 80;
                if (actualWidth == 0)
                    actualWidth = 80;
                Bitmap squaredBitmap = Bitmap.createScaledBitmap(source, (int) actualWidth, (int) actualHeight, false);

                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap((int) actualWidth, (int) actualHeight, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size / 20f;
                canvas.drawRoundRect(new RectF(0, 0, (int) actualWidth, (int) actualHeight), r, r, paint);
                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "rounded_corners";
            }
        };
    }
}
