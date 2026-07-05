package com.sy37sdk.account.floatview.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sqwan.common.util.AsyncImageLoader;
import com.sqwan.common.util.SqResUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AvatarAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private ItemClickListener itemClickListener;
    private List<String> dataList = new ArrayList();
    private int selected = -1;

    public interface ItemClickListener {
        void ItemOnClick(int i);
    }

    public AvatarAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<String> list, String str) {
        this.dataList.clear();
        this.dataList.addAll(list);
        List<String> list2 = this.dataList;
        if (list2 != null && list2.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= this.dataList.size()) {
                    break;
                }
                if (str.equals(this.dataList.get(i))) {
                    this.selected = i;
                    break;
                }
                i++;
            }
        }
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(SqResUtils.getLayoutId(this.context, "sysq_item_float_avatar"), viewGroup, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        new AsyncImageLoader(this.context).loadDrawable(this.dataList.get(i), viewHolder.ivAvatar, new AsyncImageLoader.ImageCallback() { // from class: com.sy37sdk.account.floatview.ui.AvatarAdapter.1
            @Override // com.sqwan.common.util.AsyncImageLoader.ImageCallback
            public void imageLoaded(Bitmap bitmap, ImageView imageView, String str) {
                viewHolder.ivAvatar.setImageBitmap(bitmap);
            }
        });
        if (this.selected == i) {
            viewHolder.ivSelectStatus.setImageResource(SqResUtils.getIdByName("sysq_ic_avatar_selected", "drawable", this.context));
        } else {
            viewHolder.ivSelectStatus.setImageResource(SqResUtils.getIdByName("sysq_ic_avatar_unselect", "drawable", this.context));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.floatview.ui.AvatarAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AvatarAdapter.this.selected = i;
                AvatarAdapter.this.notifyDataSetChanged();
                if (AvatarAdapter.this.itemClickListener != null) {
                    AvatarAdapter.this.itemClickListener.ItemOnClick(i);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        ImageView ivSelectStatus;

        ViewHolder(View view) {
            super(view);
            this.ivAvatar = (ImageView) view.findViewById(SqResUtils.getId(AvatarAdapter.this.context, "iv_avatar"));
            this.ivSelectStatus = (ImageView) view.findViewById(SqResUtils.getId(AvatarAdapter.this.context, "iv_status"));
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public List<String> getDatas() {
        return this.dataList;
    }
}
