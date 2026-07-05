package com.sy37sdk.order.nat.trade;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayWayAdapter extends BaseAdapter {
    private Context context;
    private boolean isShowDivider = true;
    private List<NativePayWay> nativePayWays;
    private SelectListener selectListener;

    public interface SelectListener {
        void select(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PayWayAdapter(Context context) {
        this.context = context;
    }

    public PayWayAdapter(Context context, List<NativePayWay> list) {
        this.context = context;
        this.nativePayWays = list;
    }

    public void setDatas(List<NativePayWay> list) {
        this.nativePayWays = list;
        notifyDataSetChanged();
    }

    public List<NativePayWay> getDatas() {
        return this.nativePayWays;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<NativePayWay> list = this.nativePayWays;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.nativePayWays.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(SqResUtils.getIdByName("sysq_item_pay_way", "layout", this.context), (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.tvPayWay = (TextView) view.findViewById(SqResUtils.getIdByName("tv_pay_way", SqTrackCommonKey.id, this.context));
            viewHolder.tvPayPromote = (TextView) view.findViewById(SqResUtils.getIdByName("tv_pay_promote", SqTrackCommonKey.id, this.context));
            viewHolder.ivPayWay = (ImageView) view.findViewById(SqResUtils.getIdByName("iv_pay_way", SqTrackCommonKey.id, this.context));
            viewHolder.ivSelectStatus = (ImageView) view.findViewById(SqResUtils.getIdByName("iv_select_status", SqTrackCommonKey.id, this.context));
            viewHolder.divider = view.findViewById(SqResUtils.getIdByName("divider", SqTrackCommonKey.id, this.context));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NativePayWay nativePayWay = this.nativePayWays.get(i);
        viewHolder.tvPayWay.setText(nativePayWay.getWayChinese());
        if (TextUtils.isEmpty(nativePayWay.getPromote())) {
            viewHolder.tvPayPromote.setText(nativePayWay.getExtra());
            viewHolder.tvPayPromote.setTextColor(Color.parseColor("#999999"));
        } else {
            viewHolder.tvPayPromote.setText(nativePayWay.getPromote());
            viewHolder.tvPayPromote.setTextColor(Color.parseColor("#F54B4B"));
        }
        viewHolder.ivPayWay.setImageResource(SqResUtils.getDrawableId(this.context, nativePayWay.getResId()));
        if (nativePayWay.isSelected()) {
            viewHolder.ivSelectStatus.setImageResource(SqResUtils.getDrawableId(this.context, "sysq_ic_pay_selected"));
        } else {
            viewHolder.ivSelectStatus.setImageResource(SqResUtils.getDrawableId(this.context, "sysq_ic_pay_select"));
        }
        if (!this.isShowDivider || i >= this.nativePayWays.size() - 1) {
            viewHolder.divider.setVisibility(8);
        } else {
            viewHolder.divider.setVisibility(0);
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.PayWayAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (PayWayAdapter.this.selectListener != null) {
                    PayWayAdapter.this.selectListener.select(i);
                }
            }
        });
        return view;
    }

    public void setIsShowDivider(boolean z) {
        this.isShowDivider = z;
    }

    private static class ViewHolder {
        private View divider;
        private ImageView ivPayWay;
        private ImageView ivSelectStatus;
        private TextView tvPayPromote;
        private TextView tvPayWay;

        private ViewHolder() {
        }
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
