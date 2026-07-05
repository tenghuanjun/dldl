package com.sy37sdk.order.nat.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.order.nat.bean.Coupon;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CouponAdapter extends BaseAdapter {
    private Context context;
    private List<Coupon> coupons;
    private SelectListener selectListener;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    public interface SelectListener {
        void onSelect(int i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public CouponAdapter(Context context) {
        this.context = context;
    }

    public CouponAdapter(Context context, List<Coupon> list) {
        this.context = context;
        this.coupons = list;
    }

    public void setDatas(List<Coupon> list) {
        this.coupons = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<Coupon> list = this.coupons;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.coupons.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Context context;
        String str;
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(SqResUtils.getIdByName("sysq_coupon_item", "layout", this.context), (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.tvMoney = (TextView) view.findViewById(SqResUtils.getIdByName("tv_money", SqTrackCommonKey.id, this.context));
            viewHolder.tvMinAmount = (TextView) view.findViewById(SqResUtils.getIdByName("tv_min_amount", SqTrackCommonKey.id, this.context));
            viewHolder.tvEndTime = (TextView) view.findViewById(SqResUtils.getIdByName("tv_coupon_etime", SqTrackCommonKey.id, this.context));
            viewHolder.ivSelect = (ImageView) view.findViewById(SqResUtils.getIdByName("iv_select_status", SqTrackCommonKey.id, this.context));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Coupon coupon = this.coupons.get(i);
        viewHolder.tvMoney.setText(String.valueOf((int) coupon.getAmount()));
        viewHolder.tvEndTime.setText(parseDate(coupon.getEtime()) + "到期");
        viewHolder.tvMinAmount.setText("满" + coupon.getMinAmount() + "元可用");
        ImageView imageView = viewHolder.ivSelect;
        if (coupon.isSelect()) {
            context = this.context;
            str = "sysq_ic_pay_selected";
        } else {
            context = this.context;
            str = "sysq_ic_pay_select";
        }
        imageView.setImageResource(SqResUtils.getDrawableId(context, str));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.nat.coupon.CouponAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (CouponAdapter.this.selectListener != null) {
                    CouponAdapter.this.selectListener.onSelect(i);
                }
            }
        });
        return view;
    }

    private static class ViewHolder {
        private ImageView ivSelect;
        private TextView tvEndTime;
        private TextView tvMinAmount;
        private TextView tvMoney;

        private ViewHolder() {
        }
    }

    private String parseDate(long j) {
        return j - System.currentTimeMillis() < 86400000 ? "今日" : this.simpleDateFormat.format(new Date(j));
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
