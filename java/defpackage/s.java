package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewTreeObserver;
import org.chromium.webapk.shell_apk.h2o.SplashActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class s implements ViewTreeObserver.OnGlobalLayoutListener {
    public /* synthetic */ SplashActivity a;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        Bitmap bitmapCreateBitmap;
        SplashActivity splashActivity = this.a;
        if (splashActivity.b.getWidth() == 0 || splashActivity.b.getHeight() == 0) {
            return;
        }
        splashActivity.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        View view = splashActivity.b;
        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0 || height == 0) {
            bitmapCreateBitmap = null;
        } else {
            float fMin = Math.min(1.0f, 1.2582912E7f / ((width << 2) * height));
            int iRound = Math.round(width * fMin);
            int iRound2 = Math.round(height * fMin);
            Matrix matrix = new Matrix();
            matrix.postScale(fMin, fMin);
            bitmapCreateBitmap = Bitmap.createBitmap(iRound, iRound2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.concat(matrix);
            view.draw(canvas);
        }
        splashActivity.c = bitmapCreateBitmap;
        p pVar = splashActivity.f;
        pVar.b = true;
        pVar.a();
    }
}
