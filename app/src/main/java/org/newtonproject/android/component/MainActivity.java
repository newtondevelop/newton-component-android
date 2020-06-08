package org.newtonproject.android.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        AppCompatImageView imageView = (AppCompatImageView) findViewById(R.id.planet1);
        AppCompatImageView imageView2 = (AppCompatImageView) findViewById(R.id.planet2);
        AppCompatImageView logo1 = findViewById(R.id.logo1);

        Bitmap bitmap = initPage(Color.parseColor("#DFB349"), Color.parseColor("#527EDD"));
        Bitmap bitmap2 = initPage(Color.parseColor("#5EE321"), Color.parseColor("#AA9F82"));
        Bitmap bitmap3 = initIcon(Color.parseColor("#4852E0"), Color.parseColor("#DC4F51"));

        imageView.setImageBitmap(bitmap);
        imageView2.setImageBitmap(bitmap2);

        logo1.setImageBitmap(bitmap3);
    }



    private Bitmap initIcon(int startColor, int endColor) {
        Bitmap icon = getBitmapFromVectorDrawable(this, R.drawable.tab_icon_home);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{startColor, endColor});
        Bitmap gradientBg = drawableToBitmap(icon, gradientDrawable);

        Bitmap bitmap1 = Bitmap.createBitmap(icon.getWidth(), icon.getHeight(), icon.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap1);
        int layerId = canvas.saveLayer(0f, 0f, gradientBg.getWidth(), gradientBg.getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(gradientBg, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(icon, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);
        return bitmap1;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private Bitmap initPage(int startColor, int endColor) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_bg_newplanet_overlay);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{startColor, endColor});
        Bitmap second = drawableToBitmap(bitmap, gradientDrawable);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap1);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
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
