package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.chromium.webapk.a78285a08c75c412c_v2.R;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static View a(Context context) {
        boolean z;
        Bitmap bitmap = null;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.splash_screen_view, (ViewGroup) null);
        try {
            z = context.getResources().getBoolean(R.bool.is_splash_icon_maskable);
        } catch (Resources.NotFoundException unused) {
            z = false;
        }
        if (z) {
            try {
                Drawable drawable = context.getResources().getDrawable(R.drawable.splash_icon, null);
                if (drawable != null) {
                    bitmap = ((BitmapDrawable) drawable).getBitmap();
                }
            } catch (Resources.NotFoundException unused2) {
            }
            ((ImageView) viewInflate.findViewById(R.id.splashscreen_icon_view)).setImageIcon(Icon.createWithAdaptiveBitmap(bitmap));
        }
        return viewInflate;
    }
}
