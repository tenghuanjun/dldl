package com.sqwan.liveshow.huya.danmu.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.danmu.adpter.ChooseAdapter;
import com.sqwan.liveshow.huya.skin.view.SkinRelativeLayout;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ChooseView extends SkinRelativeLayout {
    private ChooseAdapter chooseAdapter;

    public ChooseView(Context context) {
        this(context, null);
    }

    public ChooseView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChooseView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ChooseView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, SqResUtils.getLayoutId(context, SqR.layout.sy37_choose_view), this);
        RecyclerView recyclerView = (RecyclerView) findViewById(SqResUtils.getId(context, SqR.id.rl_choose_view));
        this.chooseAdapter = new ChooseAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(this.chooseAdapter);
    }

    public void setData(List<String> list, String str) {
        this.chooseAdapter.setDataList(list, str);
    }

    public void setOnclickItemListener(ChooseAdapter.ItemClickListener itemClickListener) {
        this.chooseAdapter.setItemClickListener(itemClickListener);
    }
}
