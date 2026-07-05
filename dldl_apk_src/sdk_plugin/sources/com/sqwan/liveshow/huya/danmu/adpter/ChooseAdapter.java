package com.sqwan.liveshow.huya.danmu.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.liveshow.huya.SqR;
import com.sqwan.liveshow.huya.skin.view.SkinTextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ChooseAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<String> dataList = new ArrayList();
    int highlightIndex = 0;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void ItemOnClick(int i);
    }

    public ChooseAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<String> list, String str) {
        this.dataList.addAll(list);
        int i = 0;
        while (true) {
            if (i >= this.dataList.size()) {
                break;
            }
            if (str.equals(this.dataList.get(i))) {
                this.highlightIndex = i;
                break;
            }
            i++;
        }
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(SqResUtils.getLayoutId(this.context, SqR.layout.sy37_item_choose_view), viewGroup, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.mTvItemText.setText(this.dataList.get(i));
        if (i == this.highlightIndex) {
            viewHolder.mTvItemText.setTextColor(this.context, SqR.color.sy37_item_choose_view_tv_item_text_color_highlight);
        } else {
            viewHolder.mTvItemText.setTextColor(this.context, SqR.color.sy37_item_choose_view_tv_item_text_color);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.liveshow.huya.danmu.adpter.ChooseAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseAdapter.this.highlightIndex = i;
                ChooseAdapter.this.notifyDataSetChanged();
                if (ChooseAdapter.this.itemClickListener != null) {
                    ChooseAdapter.this.itemClickListener.ItemOnClick(i);
                }
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SkinTextView mTvItemText;

        ViewHolder(View view) {
            super(view);
            this.mTvItemText = (SkinTextView) view.findViewById(SqResUtils.getId(ChooseAdapter.this.context, SqR.id.tv_item_text));
        }
    }
}
