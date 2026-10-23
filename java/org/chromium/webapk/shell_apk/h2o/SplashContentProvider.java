package org.chromium.webapk.shell_apk.h2o;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.view.View;
import defpackage.b0;
import defpackage.u;
import defpackage.v;
import defpackage.w;
import defpackage.x;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public class SplashContentProvider extends ContentProvider implements ContentProvider.PipeDataWriter {
    public static Bitmap.CompressFormat b;
    public static final AtomicReference c = new AtomicReference();
    public String a;

    public static byte[] a(byte[] bArr) {
        v vVar;
        if (bArr != null) {
            u uVar = new u();
            vVar = new v();
            vVar.a = bArr;
            Handler handler = new Handler();
            vVar.b = handler;
            handler.postDelayed(uVar, 10000L);
        } else {
            vVar = null;
        }
        v vVar2 = (v) c.getAndSet(vVar);
        if (vVar2 == null) {
            return null;
        }
        vVar2.b.removeCallbacksAndMessages(null);
        return vVar2.a;
    }

    public final Bitmap b() {
        Context applicationContext = getContext().getApplicationContext();
        SharedPreferences sharedPreferencesA = b0.a(applicationContext);
        int i = sharedPreferencesA.getInt("org.chromium.webapk.shell_apk.splash_width", -1);
        int i2 = sharedPreferencesA.getInt("org.chromium.webapk.shell_apk.splash_height", -1);
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        View viewA = Build.VERSION.SDK_INT >= 31 ? x.a(applicationContext) : w.a(applicationContext);
        viewA.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(i2, 1073741824));
        viewA.layout(0, 0, i, i2);
        int width = viewA.getWidth();
        int height = viewA.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        float fMin = Math.min(1.0f, 1.2582912E7f / ((width << 2) * height));
        int iRound = Math.round(width * fMin);
        int iRound2 = Math.round(height * fMin);
        Matrix matrix = new Matrix();
        matrix.postScale(fMin, fMin);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRound, iRound2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.concat(matrix);
        viewA.draw(canvas);
        return bitmapCreateBitmap;
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        if (uri != null && uri.toString().equals(this.a)) {
            Bitmap.CompressFormat compressFormat = b;
            if (compressFormat == null) {
                SharedPreferences sharedPreferencesA = b0.a(getContext().getApplicationContext());
                compressFormat = sharedPreferencesA.getInt("org.chromium.webapk.shell_apk.splash_height", -1) * sharedPreferencesA.getInt("org.chromium.webapk.shell_apk.splash_width", -1) <= 1048576 ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
                b = compressFormat;
            }
            if (compressFormat == Bitmap.CompressFormat.PNG) {
                return "image/png";
            }
            if (b == Bitmap.CompressFormat.JPEG) {
                return "image/jpeg";
            }
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        this.a = "content://" + (getContext().getPackageName() + ".SplashContentProvider") + "/cached_splash_image";
        return true;
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        if (uri == null || !uri.toString().equals(this.a)) {
            return null;
        }
        return openPipeHelper(uri, null, null, null, this);
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider.PipeDataWriter
    public final void writeDataToPipe(ParcelFileDescriptor parcelFileDescriptor, Uri uri, String str, Bundle bundle, Object obj) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
            try {
                byte[] bArrA = a(null);
                if (bArrA != null) {
                    fileOutputStream.write(bArrA);
                } else {
                    Bitmap bitmapB = b();
                    if (bitmapB != null) {
                        Bitmap.CompressFormat compressFormat = bitmapB.getWidth() * bitmapB.getHeight() <= 1048576 ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
                        b = compressFormat;
                        bitmapB.compress(compressFormat, 100, fileOutputStream);
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Exception unused) {
        }
    }
}
