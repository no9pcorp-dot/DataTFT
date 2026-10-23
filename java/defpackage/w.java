package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.webapk.a78285a08c75c412c_v2.R;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class w {
    public static FrameLayout a(Context context) {
        Bitmap bitmap;
        boolean z;
        Resources resources = context.getResources();
        try {
            Drawable drawable = resources.getDrawable(R.drawable.splash_icon, null);
            bitmap = drawable != null ? ((BitmapDrawable) drawable).getBitmap() : null;
        } catch (Resources.NotFoundException unused) {
        }
        int color = d0.c(context) ? resources.getColor(R.color.dark_background_color_non_empty, null) : resources.getColor(R.color.background_color_non_empty, null);
        FrameLayout frameLayout = new FrameLayout(context);
        try {
            z = context.getResources().getBoolean(R.bool.is_splash_icon_maskable);
        } catch (Resources.NotFoundException unused2) {
            z = false;
        }
        String string = resources.getString(R.string.name);
        boolean zF = d0.f(color);
        Resources resources2 = context.getResources();
        int i = R.layout.webapp_splash_screen_no_icon;
        if (bitmap != null) {
            DisplayMetrics displayMetrics = resources2.getDisplayMetrics();
            if (Math.min(bitmap.getScaledWidth(displayMetrics), bitmap.getScaledHeight(displayMetrics)) >= resources2.getDimensionPixelSize(R.dimen.webapp_splash_image_size_minimum)) {
                i = R.layout.webapp_splash_screen_large;
            }
        }
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(i, (ViewGroup) frameLayout, true);
        TextView textView = (TextView) viewGroup.findViewById(R.id.webapp_splash_screen_name);
        textView.setText(string);
        if (zF) {
            textView.setTextColor(context.getResources().getColor(R.color.webapp_splash_title_light, null));
        }
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.webapp_splash_screen_icon);
        if (imageView != null) {
            if (!z || bitmap == null) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageIcon(Icon.createWithAdaptiveBitmap(bitmap));
            }
        }
        frameLayout.setBackgroundColor(color);
        return frameLayout;
    }
}
