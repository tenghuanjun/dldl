package com.donkingliang.imageselector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.imageselector.R;
import com.donkingliang.imageselector.entry.Folder;
import com.donkingliang.imageselector.entry.Image;
import com.donkingliang.imageselector.utils.VersionUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class FolderAdapter extends RecyclerView.Adapter<ViewHolder> {
    private boolean isAndroidQ = VersionUtils.isAndroidQ();
    private Context mContext;
    private ArrayList<Folder> mFolders;
    private LayoutInflater mInflater;
    private OnFolderSelectListener mListener;
    private int mSelectItem;

    public interface OnFolderSelectListener {
        void OnFolderSelect(Folder folder);
    }

    public FolderAdapter(Context context, ArrayList<Folder> arrayList) {
        this.mContext = context;
        this.mFolders = arrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.adapter_folder, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Folder folder = this.mFolders.get(i);
        ArrayList<Image> images = folder.getImages();
        viewHolder.tvFolderName.setText(folder.getName());
        viewHolder.ivSelect.setVisibility(this.mSelectItem == i ? 0 : 8);
        if (images != null && !images.isEmpty()) {
            viewHolder.tvFolderSize.setText(this.mContext.getString(R.string.selector_image_num, Integer.valueOf(images.size())));
            RequestManager requestManagerWith = Glide.with(this.mContext);
            boolean z = this.isAndroidQ;
            Image image = images.get(0);
            requestManagerWith.load(z ? image.getUri() : image.getPath()).apply((BaseRequestOptions<?>) new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).into(viewHolder.ivImage);
        } else {
            viewHolder.tvFolderSize.setText(this.mContext.getString(R.string.selector_image_num, 0));
            viewHolder.ivImage.setImageBitmap(null);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.adapter.FolderAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FolderAdapter.this.mSelectItem = viewHolder.getAdapterPosition();
                FolderAdapter.this.notifyDataSetChanged();
                if (FolderAdapter.this.mListener != null) {
                    FolderAdapter.this.mListener.OnFolderSelect(folder);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<Folder> arrayList = this.mFolders;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public void setOnFolderSelectListener(OnFolderSelectListener onFolderSelectListener) {
        this.mListener = onFolderSelectListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        ImageView ivSelect;
        TextView tvFolderName;
        TextView tvFolderSize;

        public ViewHolder(View view) {
            super(view);
            this.ivImage = (ImageView) view.findViewById(R.id.iv_image);
            this.ivSelect = (ImageView) view.findViewById(R.id.iv_select);
            this.tvFolderName = (TextView) view.findViewById(R.id.tv_folder_name);
            this.tvFolderSize = (TextView) view.findViewById(R.id.tv_folder_size);
        }
    }
}
