package com.donkingliang.imageselector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.donkingliang.imageselector.R;
import com.donkingliang.imageselector.entry.Image;
import com.donkingliang.imageselector.utils.VersionUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ImageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int TYPE_CAMERA = 1;
    private static final int TYPE_IMAGE = 2;
    private boolean isSingle;
    private boolean isViewImage;
    private Context mContext;
    private ArrayList<Image> mImages;
    private LayoutInflater mInflater;
    private OnItemClickListener mItemClickListener;
    private int mMaxCount;
    private OnImageSelectListener mSelectListener;
    private boolean useCamera;
    private ArrayList<Image> mSelectImages = new ArrayList<>();
    private boolean isAndroidQ = VersionUtils.isAndroidQ();

    public interface OnImageSelectListener {
        void OnImageSelect(Image image, boolean z, int i);
    }

    public interface OnItemClickListener {
        void OnCameraClick();

        void OnItemClick(Image image, int i);
    }

    public ImageAdapter(Context context, int i, boolean z, boolean z2) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMaxCount = i;
        this.isSingle = z;
        this.isViewImage = z2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            return new ViewHolder(this.mInflater.inflate(R.layout.adapter_images_item, viewGroup, false));
        }
        return new ViewHolder(this.mInflater.inflate(R.layout.adapter_camera, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 2) {
            final Image image = getImage(i);
            Glide.with(this.mContext).load(this.isAndroidQ ? image.getUri() : image.getPath()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).into(viewHolder.ivImage);
            setItemSelect(viewHolder, this.mSelectImages.contains(image));
            viewHolder.ivGif.setVisibility(image.isGif() ? 0 : 8);
            viewHolder.ivSelectIcon.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.adapter.ImageAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ImageAdapter.this.checkedImage(viewHolder, image);
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.adapter.ImageAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ImageAdapter.this.isViewImage) {
                        if (ImageAdapter.this.mItemClickListener != null) {
                            int adapterPosition = viewHolder.getAdapterPosition();
                            OnItemClickListener onItemClickListener = ImageAdapter.this.mItemClickListener;
                            Image image2 = image;
                            if (ImageAdapter.this.useCamera) {
                                adapterPosition--;
                            }
                            onItemClickListener.OnItemClick(image2, adapterPosition);
                            return;
                        }
                        return;
                    }
                    ImageAdapter.this.checkedImage(viewHolder, image);
                }
            });
            return;
        }
        if (getItemViewType(i) == 1) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.donkingliang.imageselector.adapter.ImageAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ImageAdapter.this.mItemClickListener != null) {
                        ImageAdapter.this.mItemClickListener.OnCameraClick();
                    }
                }
            });
        }
    }

    public int getItemViewType(int i) {
        return (this.useCamera && i == 0) ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkedImage(ViewHolder viewHolder, Image image) {
        if (this.mSelectImages.contains(image)) {
            unSelectImage(image);
            setItemSelect(viewHolder, false);
        } else if (this.isSingle) {
            clearImageSelect();
            selectImage(image);
            setItemSelect(viewHolder, true);
        } else if (this.mMaxCount <= 0 || this.mSelectImages.size() < this.mMaxCount) {
            selectImage(image);
            setItemSelect(viewHolder, true);
        }
    }

    private void selectImage(Image image) {
        this.mSelectImages.add(image);
        OnImageSelectListener onImageSelectListener = this.mSelectListener;
        if (onImageSelectListener != null) {
            onImageSelectListener.OnImageSelect(image, true, this.mSelectImages.size());
        }
    }

    private void unSelectImage(Image image) {
        this.mSelectImages.remove(image);
        OnImageSelectListener onImageSelectListener = this.mSelectListener;
        if (onImageSelectListener != null) {
            onImageSelectListener.OnImageSelect(image, false, this.mSelectImages.size());
        }
    }

    public int getItemCount() {
        return this.useCamera ? getImageCount() + 1 : getImageCount();
    }

    private int getImageCount() {
        ArrayList<Image> arrayList = this.mImages;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public ArrayList<Image> getData() {
        return this.mImages;
    }

    public void refresh(ArrayList<Image> arrayList, boolean z) {
        this.mImages = arrayList;
        this.useCamera = z;
        notifyDataSetChanged();
    }

    private Image getImage(int i) {
        ArrayList<Image> arrayList = this.mImages;
        if (this.useCamera) {
            i--;
        }
        return arrayList.get(i);
    }

    public Image getFirstVisibleImage(int i) {
        ArrayList<Image> arrayList = this.mImages;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        if (this.useCamera) {
            return this.mImages.get(i > 0 ? i - 1 : 0);
        }
        ArrayList<Image> arrayList2 = this.mImages;
        if (i < 0) {
            i = 0;
        }
        return arrayList2.get(i);
    }

    private void setItemSelect(ViewHolder viewHolder, boolean z) {
        if (z) {
            viewHolder.ivSelectIcon.setImageResource(R.drawable.icon_image_select);
            viewHolder.ivMasking.setAlpha(0.5f);
        } else {
            viewHolder.ivSelectIcon.setImageResource(R.drawable.icon_image_un_select);
            viewHolder.ivMasking.setAlpha(0.2f);
        }
    }

    private void clearImageSelect() {
        if (this.mImages == null || this.mSelectImages.size() != 1) {
            return;
        }
        int iIndexOf = this.mImages.indexOf(this.mSelectImages.get(0));
        this.mSelectImages.clear();
        if (iIndexOf != -1) {
            if (this.useCamera) {
                iIndexOf++;
            }
            notifyItemChanged(iIndexOf);
        }
    }

    public void setSelectedImages(ArrayList<String> arrayList) {
        if (this.mImages == null || arrayList == null) {
            return;
        }
        for (String str : arrayList) {
            if (isFull()) {
                return;
            }
            Iterator<Image> it = this.mImages.iterator();
            while (true) {
                if (it.hasNext()) {
                    Image next = it.next();
                    if (str.equals(next.getPath())) {
                        if (!this.mSelectImages.contains(next)) {
                            this.mSelectImages.add(next);
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    private boolean isFull() {
        if (this.isSingle && this.mSelectImages.size() == 1) {
            return true;
        }
        return this.mMaxCount > 0 && this.mSelectImages.size() == this.mMaxCount;
    }

    public ArrayList<Image> getSelectImages() {
        return this.mSelectImages;
    }

    public void setOnImageSelectListener(OnImageSelectListener onImageSelectListener) {
        this.mSelectListener = onImageSelectListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCamera;
        ImageView ivGif;
        ImageView ivImage;
        ImageView ivMasking;
        ImageView ivSelectIcon;

        public ViewHolder(View view) {
            super(view);
            this.ivImage = (ImageView) view.findViewById(R.id.iv_image);
            this.ivSelectIcon = (ImageView) view.findViewById(R.id.iv_select);
            this.ivMasking = (ImageView) view.findViewById(R.id.iv_masking);
            this.ivGif = (ImageView) view.findViewById(R.id.iv_gif);
            this.ivCamera = (ImageView) view.findViewById(R.id.iv_camera);
        }
    }
}
