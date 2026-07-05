package com.sq.tools.event;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.tools.Logger;
import com.sq.tools.proxy.DefaultLifecycleCallbacks;
import com.sq.tools.proxy.Pair;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventsTracker {
    protected static String TAG = "sq_events";
    private final WeakReference<Context> context;
    private volatile boolean isEnable = true;
    private final EventQueue queue = new EventQueue();

    public EventsTracker(Context context, EventRequest eventRequest, String str) {
        this.context = new WeakReference<>(context);
        setEventCommonRequest(eventRequest);
        setEventUrl(str);
        this.queue.schedulePost(this.context.get());
        if (context instanceof Application) {
            registerActivityLife((Application) context);
        } else if (context instanceof Activity) {
            registerActivityLife(((Activity) context).getApplication());
        }
    }

    public void setPostAvailable(boolean z) {
        this.queue.isPostAvailable = z;
    }

    public void setEventUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.queue.url = str;
    }

    public void setEventCommonRequest(EventRequest eventRequest) {
        if (eventRequest != null) {
            this.queue.common = eventRequest;
        } else {
            this.queue.common = new EventRequest(this.context.get()) { // from class: com.sq.tools.event.EventsTracker.1
                @Override // com.sq.tools.event.EventRequest
                protected void fresh(Context context) {
                }
            };
        }
    }

    public void setEventPoster(EventPost eventPost) {
        if (eventPost != null) {
            this.queue.poster = eventPost;
        }
    }

    public void setStorageFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.queue.storage.setEventStorageFileName(str);
    }

    public void setFlushNum(int i) {
        if (i > 0) {
            this.queue.maxFlushNum = i;
        }
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void log(String str, Pair... pairArr) {
        doLog(str, pairArr);
    }

    private void doLog(String str, Pair... pairArr) {
        if (!this.isEnable || TextUtils.isEmpty(str)) {
            return;
        }
        WeakReference<Context> weakReference = this.context;
        if (weakReference == null || weakReference.get() == null) {
            Logger.error(Logger.tag(TAG), "EventsApi can not track event, since context is null, did you init EventsApi?", new Object[0]);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (pairArr != null) {
            for (Pair pair : pairArr) {
                try {
                    jSONObject.put(pair.name, pair.value);
                } catch (JSONException e) {
                    Logger.error(Logger.tag(TAG), "EventLog do log put Json Exception", e);
                }
            }
        }
        this.queue.add(this.context.get(), new EventFiled(this.context.get(), str, jSONObject));
    }

    public void save(Context context) {
        this.queue.save(context);
    }

    public void post(Context context) {
        this.queue.post(context);
    }

    private void registerActivityLife(final Application application) {
        application.registerActivityLifecycleCallbacks(new DefaultLifecycleCallbacks() { // from class: com.sq.tools.event.EventsTracker.2
            @Override // com.sq.tools.proxy.DefaultLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                super.onActivitySaveInstanceState(activity, bundle);
                EventsTracker.this.save(application);
            }
        });
    }
}
