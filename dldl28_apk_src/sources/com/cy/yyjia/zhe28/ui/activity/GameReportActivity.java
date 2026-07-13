package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import androidx.loader.content.CursorLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityGameReportBinding;
import com.cy.yyjia.zhe28.databinding.ItemReportPicBinding;
import com.cy.yyjia.zhe28.domain.GameReportBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.gson.Gson;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.PostRequest;
import com.mobile.auth.gatewayauth.Constant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: GameReportActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\"\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0016J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006$"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GameReportActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGameReportBinding;", "Landroid/view/View$OnClickListener;", "()V", "REQUEST_CODE", "", "REQ_CHOOSE", "max", "getMax", "()I", "picAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "", "Lcom/cy/yyjia/zhe28/databinding/ItemReportPicBinding;", "getPicAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "picAdapter$delegate", "Lkotlin/Lazy;", "checkPermission", "", "success", "Lkotlin/Function0;", "getVideoPath", "fileUri", "Landroid/net/Uri;", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "submit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GameReportActivity extends BaseActivity<ActivityGameReportBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final int REQUEST_CODE;
    private final int REQ_CHOOSE;
    private final int max;

    /* JADX INFO: renamed from: picAdapter$delegate, reason: from kotlin metadata */
    private final Lazy picAdapter;

    public GameReportActivity() {
        super(R.layout.activity_game_report, 0, 2, null);
        this.picAdapter = LazyKt.lazy(new Function0<BaseAdapter<String, ItemReportPicBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity$picAdapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<String, ItemReportPicBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_report_pic, null, 2, null);
            }
        });
        this.REQUEST_CODE = 11;
        this.REQ_CHOOSE = 113;
        this.max = 3;
    }

    public final BaseAdapter<String, ItemReportPicBinding> getPicAdapter() {
        return (BaseAdapter) this.picAdapter.getValue();
    }

    public final int getMax() {
        return this.max;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setData(new GameReportBean());
        getMBinding().rvPic.setAdapter(getPicAdapter());
        getPicAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameReportActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getPicAdapter().addChildClickViewIds(R.id.iv_delete);
        getPicAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GameReportActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final GameReportActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.checkPermission(new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity$init$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ImageSelector.ImageSelectorBuilder maxSelectCount = ImageSelector.builder().useCamera(false).setSingle(false).setMaxSelectCount(this.this$0.getMax());
                List<String> data = this.this$0.getPicAdapter().getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
                maxSelectCount.setSelected((ArrayList) data).canPreview(false).start(this.this$0.getMContext(), this.this$0.REQUEST_CODE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(GameReportActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        GameReportBean data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        data.getPic().remove(i);
        GameReportBean data2 = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        data2.notifyChange();
    }

    public final void checkPermission(final Function0<Unit> success) {
        Intrinsics.checkNotNullParameter(success, "success");
        List<String> listListOf = CollectionsKt.listOf(Permission.READ_MEDIA_IMAGES);
        GameReportActivity gameReportActivity = this;
        if (XXPermissions.isGranted(gameReportActivity, listListOf)) {
            success.invoke();
        } else {
            XXPermissions.with(gameReportActivity).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity$$ExternalSyntheticLambda0
                @Override // com.hjq.permissions.OnPermissionCallback
                public /* synthetic */ void onDenied(List list, boolean z) {
                    OnPermissionCallback.CC.$default$onDenied(this, list, z);
                }

                @Override // com.hjq.permissions.OnPermissionCallback
                public final void onGranted(List list, boolean z) {
                    GameReportActivity.checkPermission$lambda$2(success, list, z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPermission$lambda$2(Function0 success, List permissions, boolean z) {
        Intrinsics.checkNotNullParameter(success, "$success");
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        if (z) {
            success.invoke();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void submit(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        GameReportBean data = getMBinding().getData();
        Intrinsics.checkNotNull(data);
        if (data.getGame().length() == 0) {
            toast("请输入游戏名");
            return;
        }
        if (data.getStr().length() == 0) {
            toast("请输入了解途径");
            return;
        }
        if (data.getReason().length() == 0) {
            toast("请输入申请理由");
            return;
        }
        if (checkClick()) {
            return;
        }
        String token = com.cy.yyjia.zhe28.util.Constant.INSTANCE.getToken();
        int i = 0;
        PostRequest postRequest = (PostRequest) ((PostRequest) ((PostRequest) ((PostRequest) OkGo.post(NetUtil.BASE_URL1 + "game/apply").headers("Authorization", "bearer " + token)).params("name", data.getGame(), new boolean[0])).params("where_know", data.getStr(), new boolean[0])).params("argument", data.getReason(), new boolean[0]);
        ArrayList arrayList = new ArrayList();
        for (String str : getPicAdapter().getData()) {
            if (str.length() > 0) {
                arrayList.add(new File(str));
            }
        }
        int size = arrayList.size();
        while (i < size) {
            int i2 = i + 1;
            postRequest.params("image" + i2, (File) arrayList.get(i));
            i = i2;
        }
        String video = data.getVideo();
        if (video != null) {
            postRequest.params("video", new File(video));
        }
        String string = postRequest.getParams().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        log(string);
        postRequest.execute(new AbsCallback<Result>() { // from class: com.cy.yyjia.zhe28.ui.activity.GameReportActivity$submit$1$2
            @Override // com.lzy.okgo.convert.Converter
            public Result convertResponse(Response p0) throws IOException {
                String strString;
                if ((p0 != null ? p0.body() : null) == null) {
                    strString = "";
                } else {
                    ResponseBody responseBodyBody = p0.body();
                    Intrinsics.checkNotNull(responseBodyBody);
                    strString = responseBodyBody.string();
                }
                if (strString.length() > 0) {
                    return (Result) new Gson().fromJson(strString, Result.class);
                }
                return null;
            }

            @Override // com.lzy.okgo.callback.Callback
            public void onSuccess(com.lzy.okgo.model.Response<Result> p0) {
                this.this$0.getMainLooper();
                GameReportActivity gameReportActivity = this.this$0;
                if ((p0 != null ? p0.body() : null) != null) {
                    Result resultBody = p0.body();
                    Intrinsics.checkNotNull(resultBody);
                    gameReportActivity.toast(resultBody.getMsg());
                    if (p0.body().getCode() == 200) {
                        gameReportActivity.finish();
                        return;
                    }
                    return;
                }
                gameReportActivity.toast("返回为空");
            }

            @Override // com.lzy.okgo.callback.AbsCallback, com.lzy.okgo.callback.Callback
            public void onError(com.lzy.okgo.model.Response<Result> response) {
                Throwable exception;
                super.onError(response);
                String localizedMessage = (response == null || (exception = response.getException()) == null) ? null : exception.getLocalizedMessage();
                if (localizedMessage != null) {
                    this.this$0.toast(localizedMessage);
                }
            }
        });
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.REQUEST_CODE && data != null) {
            ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Intrinsics.checkNotNull(stringArrayListExtra, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.String>");
            List<String> listAsMutableList = TypeIntrinsics.asMutableList(stringArrayListExtra);
            if (listAsMutableList.size() < this.max) {
                listAsMutableList.add("");
            }
            GameReportBean data2 = getMBinding().getData();
            Intrinsics.checkNotNull(data2);
            data2.setPic(listAsMutableList);
            GameReportBean data3 = getMBinding().getData();
            Intrinsics.checkNotNull(data3);
            data3.notifyChange();
            return;
        }
        if (requestCode != this.REQ_CHOOSE || data == null) {
            return;
        }
        GameReportBean data4 = getMBinding().getData();
        Intrinsics.checkNotNull(data4);
        data4.setVideo(getVideoPath(data.getData()));
        GameReportBean data5 = getMBinding().getData();
        Intrinsics.checkNotNull(data5);
        data5.notifyChange();
        GameReportBean data6 = getMBinding().getData();
        Intrinsics.checkNotNull(data6);
        String video = data6.getVideo();
        Intrinsics.checkNotNull(video);
        log(video);
    }

    public final String getVideoPath(Uri fileUri) {
        if (fileUri == null) {
            return null;
        }
        Cursor cursorLoadInBackground = new CursorLoader(this, fileUri, new String[]{"_data"}, null, null, null).loadInBackground();
        if (cursorLoadInBackground == null) {
            return null;
        }
        int columnIndexOrThrow = cursorLoadInBackground.getColumnIndexOrThrow("_data");
        cursorLoadInBackground.moveToFirst();
        String string = cursorLoadInBackground.getString(columnIndexOrThrow);
        cursorLoadInBackground.close();
        return string;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.iv_delete) {
            GameReportBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            data.setVideo(null);
        } else {
            if (id != R.id.iv_video) {
                return;
            }
            Intent intent = new Intent();
            intent.setType("video/*");
            intent.setAction("android.intent.action.PICK");
            intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, this.REQ_CHOOSE);
        }
    }
}
