package com.cy.yyjia.zhe28.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.tencent.open.SocialConstants;
import java.util.List;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: CalendarUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ*\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fJ\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\bH\u0002¨\u0006\u0016"}, d2 = {"Lcom/cy/yyjia/zhe28/util/CalendarUtil;", "", "()V", "addCalendarEvent", "", "context", "Landroid/content/Context;", "timestamp", "", "title", "", SocialConstants.PARAM_COMMENT, "checkCalendarPermission", "", "success", "Lkotlin/Function0;", "failure", "createCalendarAccount", "getCalendarAccount", "openCalendar", "setEventAlarm", "eventId", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CalendarUtil {
    public static final int $stable = 0;
    public static final CalendarUtil INSTANCE = new CalendarUtil();

    private CalendarUtil() {
    }

    public final void checkCalendarPermission(Context context, final Function0<Unit> success, final Function0<Unit> failure) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failure, "failure");
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{Permission.READ_CALENDAR, Permission.WRITE_CALENDAR});
        if (XXPermissions.isGranted(context, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(context).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.util.CalendarUtil.checkCalendarPermission.1
                @Override // com.hjq.permissions.OnPermissionCallback
                public void onGranted(List<String> permissions, boolean allGranted) {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    if (allGranted) {
                        success.invoke();
                    } else {
                        failure.invoke();
                    }
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public void onDenied(List<String> permissions, boolean doNotAskAgain) {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    failure.invoke();
                }
            });
        }
    }

    public final boolean addCalendarEvent(Context context, long timestamp, String title, String description) {
        String lastPathSegment;
        Long longOrNull;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
            long calendarAccount = getCalendarAccount(context);
            if (calendarAccount == -1) {
                return false;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("calendar_id", Long.valueOf(calendarAccount));
            contentValues.put("title", title);
            contentValues.put(SocialConstants.PARAM_COMMENT, description);
            contentValues.put("dtstart", Long.valueOf(timestamp));
            contentValues.put("dtend", Long.valueOf(((long) 3600000) + timestamp));
            contentValues.put("allDay", (Integer) 0);
            contentValues.put("hasAlarm", (Integer) 1);
            contentValues.put("eventTimezone", TimeZone.getDefault().getID());
            Uri uriInsert = contentResolver.insert(CalendarContract.Events.CONTENT_URI, contentValues);
            if (uriInsert == null || (lastPathSegment = uriInsert.getLastPathSegment()) == null || (longOrNull = StringsKt.toLongOrNull(lastPathSegment)) == null) {
                return false;
            }
            setEventAlarm(context, longOrNull.longValue());
            return true;
        } catch (Exception e) {
            Log.e("addCalendarEvent: ", e.getLocalizedMessage());
            e.printStackTrace();
            return false;
        }
    }

    private final long getCalendarAccount(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
        Cursor cursorQuery = contentResolver.query(CalendarContract.Calendars.CONTENT_URI, new String[]{DBHelper.COL_ID, "account_name", "calendar_displayName"}, null, null, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToFirst()) {
                    long j = cursor2.getLong(cursor2.getColumnIndexOrThrow(DBHelper.COL_ID));
                    CloseableKt.closeFinally(cursor, null);
                    return j;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } finally {
            }
        }
        return createCalendarAccount(context);
    }

    private final long createCalendarAccount(Context context) {
        String lastPathSegment;
        Long longOrNull;
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "28zhe");
        contentValues.put("account_name", "28zhe@example.com");
        contentValues.put("account_type", "LOCAL");
        contentValues.put("calendar_displayName", "28zhe日历");
        contentValues.put("visible", (Integer) 1);
        contentValues.put("sync_events", (Integer) 1);
        contentValues.put("calendar_access_level", (Integer) 700);
        Uri uriInsert = contentResolver.insert(CalendarContract.Calendars.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", BooleanUtils.TRUE).appendQueryParameter("account_name", "28zhe@example.com").appendQueryParameter("account_type", "LOCAL").build(), contentValues);
        if (uriInsert == null || (lastPathSegment = uriInsert.getLastPathSegment()) == null || (longOrNull = StringsKt.toLongOrNull(lastPathSegment)) == null) {
            return -1L;
        }
        return longOrNull.longValue();
    }

    private final void setEventAlarm(Context context, long eventId) {
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_id", Long.valueOf(eventId));
        contentValues.put("method", (Integer) 1);
        contentValues.put("minutes", (Integer) 10);
        contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, contentValues);
    }

    public final void openCalendar(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("content://com.android.calendar/time"));
        context.startActivity(intent);
    }
}
