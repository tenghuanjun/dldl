package com.sy37sdk.account.floatview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.track.SqTrackPage;
import com.sqwan.common.util.DisplayUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sy37sdk.account.floatview.request.bean.FloatUserInfo;
import com.sy37sdk.account.floatview.ui.AvatarAdapter;
import com.sy37sdk.account.floatview.ui.SpacesItemDecoration;
import com.sy37sdk.account.trackaction.PageExposureTrackManager;
import com.sy37sdk.account.view.base.view.ClearEditText;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PersonModifyDialog extends BaseDialog {
    private AvatarAdapter avatarAdapter;
    String currentAvatar;
    private ClearEditText etNick;
    private Context mContext;
    private View modifyLayout;
    public ModifyPersonInfoListener modifyPersonInfoListener;
    private RecyclerView rvAvatar;
    private TextView tvCancel;
    private TextView tvSure;
    InputFilter typeFilter;

    public interface ModifyPersonInfoListener {
        void modify();
    }

    public PersonModifyDialog(Context context) {
        super(context);
        this.currentAvatar = "";
        this.typeFilter = new InputFilter() { // from class: com.sy37sdk.account.floatview.PersonModifyDialog.5
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (Pattern.compile("[0-9a-zA-Z|一-龥]+").matcher(charSequence.toString()).matches()) {
                    return null;
                }
                return "";
            }
        };
        this.mContext = context;
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(SqResUtils.getLayoutId(this.mContext, "sysq_dialog_person_modify"));
        PageExposureTrackManager.track(SqTrackPage.SqTrackViewId.modifyUserInfo, SqTrackPage.SqTrackViewName.modifyUserInfo);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        ClearEditText clearEditText = (ClearEditText) findViewById(getIdByName("et_nick", SqTrackCommonKey.id));
        this.etNick = clearEditText;
        clearEditText.setFilters(new InputFilter[]{this.typeFilter, new InputFilter.LengthFilter(8)});
        this.tvCancel = (TextView) findViewById(getIdByName("tv_cancel", SqTrackCommonKey.id));
        this.tvSure = (TextView) findViewById(getIdByName("tv_sure", SqTrackCommonKey.id));
        this.modifyLayout = findViewById(getIdByName("modify_layout", SqTrackCommonKey.id));
        this.rvAvatar = (RecyclerView) findViewById(getIdByName("rv_avatar", SqTrackCommonKey.id));
        this.rvAvatar.addItemDecoration(new SpacesItemDecoration(DisplayUtil.dip2px(this.mContext, 20)));
        this.avatarAdapter = new AvatarAdapter(this.mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.rvAvatar.setLayoutManager(linearLayoutManager);
        this.rvAvatar.setAdapter(this.avatarAdapter);
    }

    private void initEvent() {
        this.etNick.setClearDrawableRes("sysq_ic_delete");
        this.modifyLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.PersonModifyDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StatusBarUtil.hideSystemKeyBoard(PersonModifyDialog.this.mContext, PersonModifyDialog.this.modifyLayout);
            }
        });
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.PersonModifyDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PersonModifyDialog.this.modifyPersonInfo();
            }
        });
        this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.PersonModifyDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StatusBarUtil.hideSystemKeyBoard(PersonModifyDialog.this.mContext, PersonModifyDialog.this.modifyLayout);
                PersonModifyDialog.this.dismiss();
            }
        });
        this.avatarAdapter.setItemClickListener(new AvatarAdapter.ItemClickListener() { // from class: com.sy37sdk.account.floatview.PersonModifyDialog.4
            @Override // com.sy37sdk.account.floatview.ui.AvatarAdapter.ItemClickListener
            public void ItemOnClick(int i) {
                List<String> datas = PersonModifyDialog.this.avatarAdapter.getDatas();
                if (datas == null || i >= datas.size()) {
                    return;
                }
                PersonModifyDialog.this.currentAvatar = datas.get(i);
            }
        });
    }

    private void initData() {
        ArrayList<String> avatars = FloatViewDataManager.getInstance().getAvatars();
        FloatUserInfo ptUserInfo = FloatViewDataManager.getInstance().getPtUserInfo();
        if (ptUserInfo != null && !TextUtils.isEmpty(ptUserInfo.getAvatar())) {
            this.currentAvatar = ptUserInfo.getAvatar();
        }
        this.avatarAdapter.setDataList(avatars, this.currentAvatar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void modifyPersonInfo() {
        final String string = this.etNick.getText().toString();
        FloatViewDataManager.getInstance().modifyPersonInfo(string, this.currentAvatar, new SqHttpCallback<Void>() { // from class: com.sy37sdk.account.floatview.PersonModifyDialog.6
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                ToastUtil.showToast(PersonModifyDialog.this.mContext, str);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Void r3) {
                FloatViewDataManager.getInstance().modifyPtUserInfo(string, PersonModifyDialog.this.currentAvatar);
                if (PersonModifyDialog.this.modifyPersonInfoListener != null) {
                    PersonModifyDialog.this.modifyPersonInfoListener.modify();
                }
                ToastUtil.showToast(PersonModifyDialog.this.mContext, "修改成功");
                StatusBarUtil.hideSystemKeyBoard(PersonModifyDialog.this.mContext, PersonModifyDialog.this.modifyLayout);
                PersonModifyDialog.this.dismiss();
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                ToastUtil.showToast(PersonModifyDialog.this.mContext, "修改失败");
            }
        });
    }

    public void setModifyPersonInfoListener(ModifyPersonInfoListener modifyPersonInfoListener) {
        this.modifyPersonInfoListener = modifyPersonInfoListener;
    }
}
