package org.newtonproject.android.component;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        AppCompatImageView imageView = (AppCompatImageView) findViewById(R.id.planet1);
        AppCompatImageView imageView2 = (AppCompatImageView) findViewById(R.id.planet2);
        AppCompatImageView imageView3 = (AppCompatImageView) findViewById(R.id.planet3);
        AppCompatImageView imageView4 = (AppCompatImageView) findViewById(R.id.planet4);

        Bitmap bitmap = initPage(Color.parseColor("#DFB349"), Color.parseColor("#527EDD"));
        Bitmap bitmap2 = initPage(Color.parseColor("#5EE321"), Color.parseColor("#AA9F82"));
        Bitmap bitmap3 = initPage(Color.parseColor("#4852E0"), Color.parseColor("#DC4F51"));
        Bitmap bitmap4 = initPage(Color.parseColor("#CAA051"), Color.parseColor("#633F77"));

        imageView.setImageBitmap(bitmap);
        imageView2.setImageBitmap(bitmap2);
        imageView3.setImageBitmap(bitmap3);
        imageView4.setImageBitmap(bitmap4);
    }

    private Bitmap initPage(int startColor, int endColor) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_bg_newplanet_overlay);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{startColor, endColor});
        Bitmap second = drawableToBitmap(bitmap, gradientDrawable);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap1);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(second, 0, 0, paint);
        return bitmap1;
    }

    public static Bitmap drawableToBitmap(Bitmap sourceBitmap, Drawable drawable) {
        int w = sourceBitmap.getWidth();
        int h = sourceBitmap.getHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

}
