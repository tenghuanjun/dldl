package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.RatingBarBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameCommentBindingImpl extends ItemGameCommentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataPraiseAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView10;
    private final ShapeLinearLayout mboundView14;
    private final TextView mboundView16;
    private final TextView mboundView17;
    private final TextView mboundView18;
    private final ImageView mboundView4;
    private final ImageView mboundView8;
    private final ImageView mboundView9;

    public ItemGameCommentBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }

    private ItemGameCommentBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[11], (AppCompatRatingBar) bindings[7], (RecyclerView) bindings[15], (RecyclerView) bindings[13], (TextView) bindings[12], (ImageView) bindings[3], (ShapeTextView) bindings[19], (ImageView) bindings[1], (TextView) bindings[2], (ImageView) bindings[5], (ImageView) bindings[6]);
        this.mDirtyFlags = -1L;
        this.content.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[14];
        this.mboundView14 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView2 = (TextView) bindings[16];
        this.mboundView16 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[17];
        this.mboundView17 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[18];
        this.mboundView18 = textView4;
        textView4.setTag(null);
        ImageView imageView = (ImageView) bindings[4];
        this.mboundView4 = imageView;
        imageView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[8];
        this.mboundView8 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag(null);
        this.ratingbar.setTag(null);
        this.rv.setTag(null);
        this.rvPic.setTag(null);
        this.tvFolder.setTag(null);
        this.tvOff.setTag(null);
        this.tvPraise.setTag(null);
        this.userIcon.setTag(null);
        this.userName.setTag(null);
        this.vip1.setTag(null);
        this.vip2.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (23 != variableId) {
            return false;
        }
        setData((CommentBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameCommentBinding
    public void setData(CommentBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((CommentBean) object, fieldId);
    }

    private boolean onChangeData(CommentBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId == 102) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        if (fieldId == 47) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        if (fieldId != 53) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        long j2;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        String str5;
        String str6;
        String str7;
        boolean z2;
        boolean zIsEmpty;
        boolean z3;
        String str8;
        String str9;
        String str10;
        boolean z4;
        boolean z5;
        boolean z6;
        OnClickListenerImpl onClickListenerImpl;
        boolean z7;
        boolean z8;
        List<String> list;
        boolean z9;
        List<CommentBean> list2;
        int i;
        boolean z10;
        String str11;
        String str12;
        String str13;
        String str14;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        String strValueOf;
        OnClickListenerImpl onClickListenerImpl2;
        List<String> uploadImg;
        boolean z15;
        boolean zHideMonthCard;
        String tagIcon;
        String dateline;
        List<CommentBean> list3;
        int score;
        String tagName;
        String qualityImg;
        String savingCardImg;
        String nickName;
        String user_avatar;
        String monthCardImg;
        boolean zHideSqk;
        String vieLevelImg;
        String str15;
        String showText;
        String strValueOf2;
        String message;
        int iIs_quality;
        int i2;
        int to_uid;
        UserBean user;
        int pid;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CommentBean commentBean = this.mData;
        if ((31 & j) != 0) {
            if ((j & 17) != 0) {
                if (commentBean != null) {
                    message = commentBean.getMessage();
                    iIs_quality = commentBean.is_quality();
                    int reply_num = commentBean.getReply_num();
                    uploadImg = commentBean.getUploadImg();
                    to_uid = commentBean.getTo_uid();
                    user = commentBean.getUser();
                    tagIcon = commentBean.getTagIcon();
                    dateline = commentBean.getDateline();
                    list3 = commentBean.getList();
                    score = commentBean.getScore();
                    tagName = commentBean.getTagName();
                    pid = commentBean.getPid();
                    OnClickListenerImpl onClickListenerImpl3 = this.mDataPraiseAndroidViewViewOnClickListener;
                    if (onClickListenerImpl3 == null) {
                        onClickListenerImpl3 = new OnClickListenerImpl();
                        this.mDataPraiseAndroidViewViewOnClickListener = onClickListenerImpl3;
                    }
                    OnClickListenerImpl value = onClickListenerImpl3.setValue(commentBean);
                    qualityImg = commentBean.getQualityImg();
                    onClickListenerImpl2 = value;
                    i2 = reply_num;
                } else {
                    message = null;
                    iIs_quality = 0;
                    i2 = 0;
                    onClickListenerImpl2 = null;
                    uploadImg = null;
                    to_uid = 0;
                    user = null;
                    tagIcon = null;
                    dateline = null;
                    list3 = null;
                    score = 0;
                    tagName = null;
                    pid = 0;
                    qualityImg = null;
                }
                int length = message != null ? message.length() : 0;
                z = iIs_quality == 0;
                String str16 = "共" + i2;
                strValueOf = String.valueOf(i2);
                z3 = to_uid != 0;
                z15 = pid != 0;
                int size = uploadImg != null ? uploadImg.size() : 0;
                if (user != null) {
                    savingCardImg = user.getSavingCardImg();
                    nickName = user.getNickName();
                    int iIs_official = user.is_official();
                    user_avatar = user.getUser_avatar();
                    monthCardImg = user.getMonthCardImg();
                    zHideSqk = user.hideSqk();
                    vieLevelImg = user.getVieLevelImg();
                    zHideMonthCard = user.hideMonthCard();
                    i3 = iIs_official;
                } else {
                    i3 = 0;
                    zHideMonthCard = false;
                    savingCardImg = null;
                    nickName = null;
                    user_avatar = null;
                    monthCardImg = null;
                    zHideSqk = false;
                    vieLevelImg = null;
                }
                int size2 = list3 != null ? list3.size() : 0;
                z11 = length <= 80;
                str3 = str16 + "条回复";
                z12 = size == 0;
                z13 = i3 != 1;
                zIsEmpty = TextUtils.isEmpty(vieLevelImg);
                z14 = size2 == 0;
            } else {
                z11 = false;
                str3 = null;
                z12 = false;
                z = false;
                z13 = false;
                z14 = false;
                strValueOf = null;
                zIsEmpty = false;
                z3 = false;
                onClickListenerImpl2 = null;
                uploadImg = null;
                z15 = false;
                zHideMonthCard = false;
                tagIcon = null;
                dateline = null;
                list3 = null;
                score = 0;
                tagName = null;
                qualityImg = null;
                savingCardImg = null;
                nickName = null;
                user_avatar = null;
                monthCardImg = null;
                zHideSqk = false;
                vieLevelImg = null;
            }
            long j3 = j & 19;
            if (j3 != 0) {
                showText = commentBean != null ? commentBean.getShowText() : null;
                boolean z16 = (showText != null ? showText.length() : 0) > 83;
                if (j3 != 0) {
                    j |= z16 ? 64L : 32L;
                }
                str15 = z16 ? "收起" : "展开";
            } else {
                str15 = null;
                showText = null;
            }
            if ((j & 25) != 0) {
                strValueOf2 = String.valueOf(commentBean != null ? commentBean.getLike_num() : 0);
            } else {
                strValueOf2 = null;
            }
            if ((j & 21) != 0) {
                boolean z17 = (commentBean != null ? commentBean.getIslikeNum() : 0) == 1;
                str10 = strValueOf2;
                str = tagIcon;
                i = score;
                str11 = savingCardImg;
                str12 = nickName;
                str13 = user_avatar;
                str14 = monthCardImg;
                j2 = 19;
                z9 = z12;
                z7 = z13;
                str4 = strValueOf;
                str9 = str15;
                str8 = dateline;
                str5 = tagName;
                str7 = vieLevelImg;
                z8 = z11;
                list2 = list3;
                str2 = qualityImg;
                list = uploadImg;
                z10 = z15;
                z5 = zHideMonthCard;
                z6 = zHideSqk;
                onClickListenerImpl = onClickListenerImpl2;
                z4 = z17;
                z2 = z14;
                str6 = showText;
            } else {
                z2 = z14;
                str10 = strValueOf2;
                str = tagIcon;
                i = score;
                str11 = savingCardImg;
                str12 = nickName;
                str6 = showText;
                str13 = user_avatar;
                str14 = monthCardImg;
                j2 = 19;
                z9 = z12;
                z7 = z13;
                str4 = strValueOf;
                str9 = str15;
                str8 = dateline;
                str5 = tagName;
                str7 = vieLevelImg;
                z8 = z11;
                list2 = list3;
                str2 = qualityImg;
                list = uploadImg;
                z10 = z15;
                z5 = zHideMonthCard;
                z6 = zHideSqk;
                onClickListenerImpl = onClickListenerImpl2;
                z4 = false;
            }
        } else {
            j2 = 19;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            z = false;
            str5 = null;
            str6 = null;
            str7 = null;
            z2 = false;
            zIsEmpty = false;
            z3 = false;
            str8 = null;
            str9 = null;
            str10 = null;
            z4 = false;
            z5 = false;
            z6 = false;
            onClickListenerImpl = null;
            z7 = false;
            z8 = false;
            list = null;
            z9 = false;
            list2 = null;
            i = 0;
            z10 = false;
            str11 = null;
            str12 = null;
            str13 = null;
            str14 = null;
        }
        long j4 = j & j2;
        String str17 = str;
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.content, str6);
            TextViewBindingAdapter.setText(this.tvFolder, str9);
        }
        if ((j & 17) != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, str5);
            DataBindingHelper.setViewGone(this.mboundView14, z2);
            DataBindingHelper.setViewGone(this.mboundView16, z3);
            TextViewBindingAdapter.setText(this.mboundView16, str3);
            TextViewBindingAdapter.setText(this.mboundView17, str8);
            TextViewBindingAdapter.setText(this.mboundView18, str4);
            DataBindingHelper.setViewGone(this.mboundView4, zIsEmpty);
            DataBindingHelper.setImg(this.mboundView4, str7, null);
            DataBindingHelper.setViewGone(this.mboundView8, z);
            DataBindingHelper.setImg(this.mboundView8, str2, null);
            DataBindingHelper.setImg(this.mboundView9, str17, null);
            DataBindingHelper.setViewGone(this.ratingbar, z10);
            RatingBarBindingAdapter.setRating(this.ratingbar, i);
            DataBindingHelper.setRvData(this.rv, list2);
            DataBindingHelper.setViewGone(this.rvPic, z9);
            DataBindingHelper.setRvData(this.rvPic, list);
            DataBindingHelper.setViewGone(this.tvFolder, z8);
            DataBindingHelper.setViewGone(this.tvOff, z7);
            this.tvPraise.setOnClickListener(onClickListenerImpl);
            DataBindingHelper.setUserIcon(this.userIcon, str13);
            TextViewBindingAdapter.setText(this.userName, str12);
            DataBindingHelper.setViewGone(this.vip1, z6);
            DataBindingHelper.setImg(this.vip1, str11, null);
            DataBindingHelper.setViewGone(this.vip2, z5);
            DataBindingHelper.setImg(this.vip2, str14, null);
        }
        if ((21 & j) != 0) {
            DataBindingHelper.setSelected(this.tvPraise, z4);
        }
        if ((j & 25) != 0) {
            TextViewBindingAdapter.setText(this.tvPraise, str10);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private CommentBean value;

        public OnClickListenerImpl setValue(CommentBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.praise(arg0);
        }
    }
}
