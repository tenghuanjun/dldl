package com.demo.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ShareAdapter extends BaseAdapter {
    private List<String> imgs;
    private LayoutInflater inflater;
    private Context mContext;
    private OnClickShareListener mOnClickShareListener;

    public interface OnClickShareListener {
        void clickDelete(int i);

        void clickShare(String str);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public ShareAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.imgs = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.imgs.size();
    }

    @Override // android.widget.Adapter
    public String getItem(int i) {
        return this.imgs.get(i);
    }

    public void setData(List<String> list) {
        this.imgs = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.sy37_item_share, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.etImgId = (EditText) view.findViewById(R.id.et_img_id);
            viewHolder.btnShare = (Button) view.findViewById(R.id.btn_share);
            viewHolder.btnDelete = (Button) view.findViewById(R.id.btn_delete);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String item = getItem(i);
        if (!TextUtils.isEmpty(item)) {
            viewHolder.etImgId.setText(item);
        } else {
            viewHolder.etImgId.setText("");
        }
        viewHolder.btnShare.setOnClickListener(new View.OnClickListener() { // from class: com.demo.base.ShareAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ShareAdapter.this.mOnClickShareListener != null) {
                    ShareAdapter.this.mOnClickShareListener.clickShare(viewHolder.etImgId.getText().toString());
                }
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() { // from class: com.demo.base.ShareAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ShareAdapter.this.mOnClickShareListener != null) {
                    ShareAdapter.this.mOnClickShareListener.clickDelete(i);
                }
            }
        });
        return view;
    }

    private class ViewHolder {
        private Button btnDelete;
        private Button btnShare;
        private EditText etImgId;

        private ViewHolder() {
        }
    }

    public void setOnClickShareListener(OnClickShareListener onClickShareListener) {
        this.mOnClickShareListener = onClickShareListener;
    }
}
