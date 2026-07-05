package com.sqwan.liveshow.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.SqR;
import com.sqwan.liveshow.bean.LiveShowIMBean;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowIMAdapter extends RecyclerView.Adapter<IMViewHolder> {
    private Context mContext;
    private Task task = Task.create();
    private int USER_LIST_SIZE = 500;
    private int SHOW_LIST_SIZE = 300;
    private int REPEAT_TIME = 1000;
    private volatile LinkedList<LiveShowIMBean.UserImBean> mUserImBeanList = new LinkedList<>();
    private volatile LinkedList<LiveShowIMBean.UserImBean> mShowUserImBeanList = new LinkedList<>();
    private LinkedList<LiveShowIMBean.UserImBean> mTempImBeanList = new LinkedList<>();

    public interface RepeatUpdateViewCallback {
        void updateViewCallback();
    }

    public LiveshowIMAdapter(Context context) {
        this.mContext = context;
    }

    public void updateDataDirect(LiveShowIMBean.UserImBean userImBean) {
        if (this.mShowUserImBeanList.size() >= this.SHOW_LIST_SIZE) {
            this.mShowUserImBeanList.removeFirst();
        }
        this.mShowUserImBeanList.add(userImBean);
        notifyDataSetChanged();
    }

    private void addData(LiveShowIMBean.UserImBean userImBean) {
        if (this.mUserImBeanList.size() > this.USER_LIST_SIZE) {
            this.mUserImBeanList.removeFirst();
        }
        this.mUserImBeanList.add(userImBean);
    }

    public void addDataRefresh(LiveShowIMBean.UserImBean userImBean) {
        addData(userImBean);
        checkLimit();
        notifyDataSetChanged();
    }

    public void updateDataRefresh(ArrayList<LiveShowIMBean.UserImBean> arrayList) {
        checkLimit();
        notifyDataSetChanged();
    }

    public void release() {
        this.task.stop();
    }

    public void repeatUpdateView(final RepeatUpdateViewCallback repeatUpdateViewCallback) {
        this.task.repeat(this.REPEAT_TIME, new Task.TaskFunc() { // from class: com.sqwan.liveshow.ui.LiveshowIMAdapter.1
            @Override // com.sqwan.common.util.task.Task.TaskFunc
            public Task.Result exec() {
                if (LiveshowIMAdapter.this.mUserImBeanList.size() <= 0 || LiveshowIMAdapter.this.mShowUserImBeanList == null) {
                    return null;
                }
                LiveshowIMAdapter.this.checkLimit();
                LiveshowIMAdapter.this.notifyDataSetChanged();
                repeatUpdateViewCallback.updateViewCallback();
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkLimit() {
        this.mTempImBeanList.clear();
        if (this.mUserImBeanList.size() + this.mShowUserImBeanList.size() > this.SHOW_LIST_SIZE) {
            this.mTempImBeanList.addAll(this.mShowUserImBeanList);
            this.mTempImBeanList.addAll(this.mUserImBeanList);
            int size = this.mTempImBeanList.size() - this.SHOW_LIST_SIZE;
            for (int i = 0; i < size; i++) {
                this.mTempImBeanList.removeFirst();
            }
            this.mShowUserImBeanList.clear();
            this.mShowUserImBeanList.addAll(this.mTempImBeanList);
            int size2 = this.SHOW_LIST_SIZE - this.mShowUserImBeanList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.mUserImBeanList.removeFirst();
            }
        } else {
            this.mShowUserImBeanList.addAll(this.mUserImBeanList);
            this.mUserImBeanList.clear();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public IMViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new IMViewHolder(LayoutInflater.from(this.mContext).inflate(SqResUtils.getLayoutId(this.mContext, SqR.layout.sy37_base_item_liveshow_im_chat), viewGroup, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(IMViewHolder iMViewHolder, int i) {
        iMViewHolder.mUserName.setText(this.mShowUserImBeanList.get(i).getUserName() + ":  ");
        iMViewHolder.mChatMessage.setText(this.mShowUserImBeanList.get(i).getUserChatContent());
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mShowUserImBeanList == null) {
            return 0;
        }
        return this.mShowUserImBeanList.size();
    }

    class IMViewHolder extends RecyclerView.ViewHolder {
        TextView mChatMessage;
        TextView mUserName;

        IMViewHolder(View view) {
            super(view);
            this.mUserName = (TextView) view.findViewById(SqResUtils.getId(LiveshowIMAdapter.this.mContext, "tv_user_name"));
            this.mChatMessage = (TextView) view.findViewById(SqResUtils.getId(LiveshowIMAdapter.this.mContext, SqR.id.tv_chat_message));
        }
    }
}
