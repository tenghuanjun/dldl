package com.sy37sdk.account.floatview.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.account.floatview.MenuConfig;
import com.sy37sdk.account.floatview.data.RedDot;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FloatItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private ItemClickListener itemClickListener;
    private List<MenuConfig> dataList = new ArrayList();
    private List<RedDot> redDots = new ArrayList();
    private int titleTextColor = 0;

    public interface ItemClickListener {
        void ItemOnClick(MenuConfig menuConfig);
    }

    public FloatItemAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<MenuConfig> list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void setRedDots(List<RedDot> list) {
        this.redDots = list;
        notifyDataSetChanged();
    }

    public void setTitleColor(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.titleTextColor = Color.parseColor(str);
        } catch (Exception e) {
            e.printStackTrace();
            BuglessAction.reportCatchException(e, "解析颜色错误 setTitleColor : " + str, 999);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(SqResUtils.getLayoutId(this.context, "sy37_float_menu_item"), viewGroup, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        String str;
        final MenuConfig menuConfig = this.dataList.get(i);
        new AsyncImageLoader(this.context).loadDrawable(menuConfig.iconUrl, viewHolder.ivMenu, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.floatview.ui.FloatItemAdapter.1
            @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
            public void imageLoaded(Bitmap bitmap, ImageView imageView, String str2) {
                viewHolder.ivMenu.setImageBitmap(bitmap);
            }
        });
        viewHolder.tvMenu.setText(menuConfig.title);
        int i2 = 0;
        while (true) {
            if (i2 >= this.redDots.size()) {
                i2 = -1;
                break;
            } else if (this.redDots.get(i2).getTitle().equals(menuConfig.title)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            RedDot redDot = this.redDots.get(i2);
            int num = redDot.getNum();
            if (num > 0) {
                if (redDot.needShowNum()) {
                    TextView textView = viewHolder.tvNum;
                    if (num < 10) {
                        str = redDot.getNum() + "";
                    } else {
                        str = "9+";
                    }
                    textView.setText(str);
                    viewHolder.tvNum.setVisibility(0);
                    viewHolder.ivRedDot.setVisibility(8);
                } else {
                    viewHolder.ivRedDot.setVisibility(0);
                    viewHolder.tvNum.setVisibility(8);
                }
            } else {
                viewHolder.ivRedDot.setVisibility(8);
                viewHolder.tvNum.setVisibility(8);
            }
        } else {
            viewHolder.ivRedDot.setVisibility(8);
            viewHolder.tvNum.setText("");
            viewHolder.tvNum.setVisibility(8);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.ui.FloatItemAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FloatItemAdapter.this.itemClickListener != null) {
                    FloatItemAdapter.this.itemClickListener.ItemOnClick(menuConfig);
                }
            }
        });
        if (this.titleTextColor != 0) {
            viewHolder.tvMenu.setTextColor(this.titleTextColor);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MenuConfig> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMenu;
        ImageView ivRedDot;
        TextView tvMenu;
        TextView tvNum;

        ViewHolder(View view) {
            super(view);
            this.ivRedDot = (ImageView) view.findViewById(SqResUtils.getId(FloatItemAdapter.this.context, "iv_red_dot"));
            this.tvNum = (TextView) view.findViewById(SqResUtils.getId(FloatItemAdapter.this.context, "tv_red_dot"));
            this.ivMenu = (ImageView) view.findViewById(SqResUtils.getId(FloatItemAdapter.this.context, "menu_item_icon"));
            this.tvMenu = (TextView) view.findViewById(SqResUtils.getId(FloatItemAdapter.this.context, "menu_item_text"));
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
