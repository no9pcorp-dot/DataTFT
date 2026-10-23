package defpackage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.chromium.webapk.shell_apk.h2o.SplashActivity;

/* JADX INFO: compiled from: chromium-WebApk.apk-default-1 */
/* JADX INFO: loaded from: classes.dex */
public final class t extends AsyncTask {
    public final /* synthetic */ Bitmap a;
    public final /* synthetic */ SplashActivity b;

    public t(SplashActivity splashActivity, Bitmap bitmap) {
        this.a = bitmap;
        this.b = splashActivity;
    }

    @Override // android.os.AsyncTask
    public final Object doInBackground(Object[] objArr) {
        Bitmap bitmap = this.a;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                Bitmap.CompressFormat compressFormat = bitmap.getWidth() * bitmap.getHeight() <= 1048576 ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;
                bitmap.compress(compressFormat, 100, byteArrayOutputStream);
                Pair pairCreate = Pair.create(byteArrayOutputStream.toByteArray(), compressFormat);
                byteArrayOutputStream.close();
                return pairCreate;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        Bitmap.CompressFormat compressFormat;
        Pair pair = (Pair) obj;
        SplashActivity splashActivity = this.b;
        byte[] bArr = null;
        splashActivity.a = null;
        if (pair == null) {
            compressFormat = Bitmap.CompressFormat.PNG;
        } else {
            bArr = (byte[]) pair.first;
            compressFormat = (Bitmap.CompressFormat) pair.second;
        }
        splashActivity.a(bArr, compressFormat);
    }
}
