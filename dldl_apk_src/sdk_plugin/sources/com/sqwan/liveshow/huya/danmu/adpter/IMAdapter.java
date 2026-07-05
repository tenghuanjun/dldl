package com.sqwan.liveshow.huya.danmu.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sqwan.common.util.SpanUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class IMAdapter extends RecyclerView.Adapter<IMViewHolder> {
    public Context context;
    private volatile ArrayList<UserIMBean> mUserImBeanList = new ArrayList<>();

    public IMAdapter(Context context) {
        this.context = context;
    }

    public void updateDataDirect(CharSequence charSequence, CharSequence charSequence2) {
        UserIMBean userIMBean = new UserIMBean();
        userIMBean.userName = charSequence;
        userIMBean.message = charSequence2;
        this.mUserImBeanList.add(userIMBean);
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public IMViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new IMViewHolder(LayoutInflater.from(this.context).inflate(SqResUtils.getLayoutId(this.context, SqR.layout.sy37_item_im_chat), viewGroup, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(IMViewHolder iMViewHolder, int i) {
        iMViewHolder.mTvMsg.setText(SpanUtil.concat(this.mUserImBeanList.get(i).userName, this.mUserImBeanList.get(i).message));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mUserImBeanList == null) {
            return 0;
        }
        return this.mUserImBeanList.size();
    }

    class UserIMBean {
        CharSequence message;
        CharSequence userName;

        UserIMBean() {
        }
    }

    class IMViewHolder extends RecyclerView.ViewHolder {
        TextView mTvMsg;

        IMViewHolder(View view) {
            super(view);
            this.mTvMsg = (TextView) view.findViewById(SqResUtils.getId(IMAdapter.this.context, "tv_msg"));
        }
    }
}
