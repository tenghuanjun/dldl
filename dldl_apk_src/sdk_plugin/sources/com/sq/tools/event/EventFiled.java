package com.sq.tools.event;

import android.content.Context;
import com.sq.tools.event.FiledTools;
import com.sq.tools.utils.TimeUtils;
import com.sqwan.bugless.util.DateUtil;
import com.sqwan.common.constants.SqConstants;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventFiled extends FiledTools implements Serializable {

    @FiledTools.EventField("event")
    public String event;

    @FiledTools.EventField("msg_id")
    public String id;

    @FiledTools.EventField(SqConstants.CDATE)
    public String timeDate;

    public EventFiled(Context context, String str) {
        this(context, str, new JSONObject());
    }

    public EventFiled(Context context, String str, JSONObject jSONObject) {
        this.event = str;
        this.id = UUID.randomUUID().toString();
        this.timeDate = new SimpleDateFormat(DateUtil.DEFAULT_DATE_TIME_FORMAT, Locale.CHINA).format(new Date(TimeUtils.getLongServerTimeInMills()));
        this.data = jSONObject.toString();
    }
}
