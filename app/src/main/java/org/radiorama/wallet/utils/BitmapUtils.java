package org.radiorama.wallet.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class BitmapUtils {

    public static Bitmap getCircleImage(Bitmap source) {
        int length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

//    public static Bitmap getCircleImage(Bitmap source) {
//        int width = source.getWidth();
//        int height = source.getHeight();
//        float raduis = Math.min(width, height) * 0.5f;
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//        Bitmap bitmap = Bitmap.createBitmap(width, height, source.getConfig());
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawCircle(width * 0.5f, height *0.5f, raduis, paint);
//        return bitmap;
//    }

}
