package com.huya.berry.gamesdk.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.huya.berry.gamesdk.resolutions.Resolution;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ResolutionListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Resolution> mResolutionList = new ArrayList();
    private int mSelectedIndex;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ResolutionListAdapter(Context context) {
        this.mContext = context;
    }

    public void setResolutionList(List<Resolution> list) {
        if (list == null) {
            return;
        }
        this.mResolutionList.clear();
        this.mResolutionList.addAll(list);
    }

    public void setSelectedIndex(int i) {
        this.mSelectedIndex = i;
        notifyDataSetChanged();
    }

    public void setResolution(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= this.mResolutionList.size()) {
                break;
            }
            if (this.mResolutionList.get(i3).resolution == i) {
                i2 = i3;
                break;
            }
            i3++;
        }
        setSelectedIndex(i2);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mResolutionList.size();
    }

    @Override // android.widget.Adapter
    public Resolution getItem(int i) {
        return this.mResolutionList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_item_resolution_list), viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.vBg = view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.v_bg));
            viewHolder.tvResolution = (TextView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.tv_resolution));
            viewHolder.ivTick = (ImageView) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.iv_tick));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvResolution.setText(this.mResolutionList.get(i).tips);
        if (i == this.mSelectedIndex) {
            viewHolder.setSelected(true);
        } else {
            viewHolder.setSelected(false);
        }
        return view;
    }

    private static class ViewHolder {
        ImageView ivTick;
        TextView tvResolution;
        View vBg;

        private ViewHolder() {
        }

        public void setSelected(boolean z) {
            View view = this.vBg;
            if (view == null || this.tvResolution == null || this.ivTick == null) {
                return;
            }
            view.setSelected(z);
            this.tvResolution.setSelected(z);
            if (z) {
                this.ivTick.setImageResource(ResourceUtil.getDrawableResIDByName("hyberry_tick_icon"));
            } else {
                this.ivTick.setImageBitmap(null);
            }
        }
    }
}
