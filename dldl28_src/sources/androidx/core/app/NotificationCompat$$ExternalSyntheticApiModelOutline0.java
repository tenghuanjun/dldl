package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.graphics.fonts.FontVariationAxis;
import android.graphics.text.LineBreakConfig;
import android.os.LocaleList;
import android.telephony.SubscriptionManager;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.view.ViewStructure;
import android.view.inputmethod.EditorBoundsInfo;
import java.io.File;
import java.io.FileDescriptor;
import java.util.Locale;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class NotificationCompat$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ Notification.MessagingStyle m(Object obj) {
        return (Notification.MessagingStyle) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannel m6142m(Object obj) {
        return (NotificationChannel) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NotificationChannelGroup m6143m(Object obj) {
        return (NotificationChannelGroup) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ android.app.Person m6144m(Object obj) {
        return (android.app.Person) obj;
    }

    public static /* synthetic */ JobWorkItem m(Intent intent) {
        return new JobWorkItem(intent);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ ShortcutInfo.Builder m6145m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }

    public static /* synthetic */ Typeface.Builder m(AssetManager assetManager, String str) {
        return new Typeface.Builder(assetManager, str);
    }

    public static /* synthetic */ Typeface.Builder m(File file) {
        return new Typeface.Builder(file);
    }

    public static /* synthetic */ Typeface.Builder m(FileDescriptor fileDescriptor) {
        return new Typeface.Builder(fileDescriptor);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Icon m6146m(Object obj) {
        return (Icon) obj;
    }

    public static /* synthetic */ FontVariationAxis m(String str, float f) {
        return new FontVariationAxis(str, f);
    }

    public static /* synthetic */ LineBreakConfig.Builder m() {
        return new LineBreakConfig.Builder();
    }

    public static /* synthetic */ LocaleList m(Locale[] localeArr) {
        return new LocaleList(localeArr);
    }

    public static /* synthetic */ BoringLayout m(CharSequence charSequence, TextPaint textPaint, int i, Layout.Alignment alignment, float f, float f2, BoringLayout.Metrics metrics, boolean z, TextUtils.TruncateAt truncateAt, int i2, boolean z2) {
        return new BoringLayout(charSequence, textPaint, i, alignment, f, f2, metrics, z, truncateAt, i2, z2);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ LocaleSpan m6148m(LocaleList localeList) {
        return new LocaleSpan(localeList);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ViewStructure m6149m(Object obj) {
        return (ViewStructure) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ EditorBoundsInfo.Builder m6150m() {
        return new EditorBoundsInfo.Builder();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Class m6151m() {
        return Notification.MessagingStyle.class;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m6152m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m6154m(Object obj) {
        return obj instanceof Icon;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return Notification.DecoratedCustomViewStyle.class;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m6155m$1() {
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return SubscriptionManager.class;
    }

    /* JADX INFO: renamed from: m$2, reason: collision with other method in class */
    public static /* synthetic */ void m6156m$2() {
    }
}
