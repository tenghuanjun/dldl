package com.cy.yyjia.zhe28.databinding;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.RatingBarBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CommentBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityCommentDetailBindingImpl extends ActivityCommentDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final Navigation mboundView1;
    private final LinearLayout mboundView10;
    private final ImageView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView16;
    private final TextView mboundView17;
    private final ImageView mboundView5;
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv, 21);
        sparseIntArray.put(R.id.btn, 22);
    }

    public ActivityCommentDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityCommentDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (Button) bindings[22], (TextView) bindings[13], (EditText) bindings[19], (AppCompatRatingBar) bindings[8], (RecyclerView) bindings[21], (RecyclerView) bindings[20], (RecyclerView) bindings[15], (TextView) bindings[14], (ImageView) bindings[4], (ShapeTextView) bindings[18], (ImageView) bindings[2], (TextView) bindings[3], (ImageView) bindings[6], (ImageView) bindings[7]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityCommentDetailBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityCommentDetailBindingImpl.this.et);
                String str = ActivityCommentDetailBindingImpl.this.mText;
                ActivityCommentDetailBindingImpl activityCommentDetailBindingImpl = ActivityCommentDetailBindingImpl.this;
                if (activityCommentDetailBindingImpl != null) {
                    activityCommentDetailBindingImpl.setText(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.content.setTag(null);
        this.et.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        Navigation navigation = (Navigation) bindings[1];
        this.mboundView1 = navigation;
        navigation.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) bindings[10];
        this.mboundView10 = linearLayout2;
        linearLayout2.setTag(null);
        ImageView imageView = (ImageView) bindings[11];
        this.mboundView11 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[12];
        this.mboundView12 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[16];
        this.mboundView16 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[17];
        this.mboundView17 = textView3;
        textView3.setTag(null);
        ImageView imageView2 = (ImageView) bindings[5];
        this.mboundView5 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[9];
        this.mboundView9 = imageView3;
        imageView3.setTag(null);
        this.ratingbar.setTag(null);
        this.rvEdit.setTag(null);
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
            this.mDirtyFlags = 32L;
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
        if (23 == variableId) {
            setData((CommentBean) variable);
        } else {
            if (111 != variableId) {
                return false;
            }
            setText((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentDetailBinding
    public void setData(CommentBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityCommentDetailBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(111);
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
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        if (fieldId == 47) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
        if (fieldId != 53) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        boolean z2;
        String str5;
        String str6;
        boolean zIsEmpty;
        String str7;
        String str8;
        String str9;
        String str10;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        List<String> list;
        boolean z7;
        int i;
        boolean z8;
        String str11;
        String str12;
        String str13;
        String str14;
        boolean z9;
        String str15;
        boolean z10;
        long j2;
        boolean z11;
        Drawable drawable;
        boolean z12;
        boolean z13;
        boolean z14;
        String str16;
        String strValueOf;
        boolean z15;
        List<String> list2;
        boolean zHideMonthCard;
        String tagIcon;
        String dateline;
        int score;
        String tagName;
        boolean zIsEmpty2;
        String qualityImg;
        String savingCardImg;
        String nickName;
        String user_avatar;
        String monthCardImg;
        boolean zHideSqk;
        String vieLevelImg;
        String str17;
        long j3;
        String showText;
        String strValueOf2;
        String message;
        int iIs_quality;
        int i2;
        UserBean user;
        int pid;
        int i3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CommentBean commentBean = this.mData;
        String str18 = this.mText;
        if ((61 & j) != 0) {
            if ((j & 33) != 0) {
                if (commentBean != null) {
                    message = commentBean.getMessage();
                    iIs_quality = commentBean.is_quality();
                    int reply_num = commentBean.getReply_num();
                    List<String> uploadImg = commentBean.getUploadImg();
                    user = commentBean.getUser();
                    tagIcon = commentBean.getTagIcon();
                    dateline = commentBean.getDateline();
                    score = commentBean.getScore();
                    tagName = commentBean.getTagName();
                    pid = commentBean.getPid();
                    qualityImg = commentBean.getQualityImg();
                    i2 = reply_num;
                    list2 = uploadImg;
                } else {
                    message = null;
                    iIs_quality = 0;
                    i2 = 0;
                    list2 = null;
                    user = null;
                    tagIcon = null;
                    dateline = null;
                    score = 0;
                    tagName = null;
                    pid = 0;
                    qualityImg = null;
                }
                int length = message != null ? message.length() : 0;
                z = iIs_quality == 0;
                str16 = i2 + "条回复";
                strValueOf = String.valueOf(i2);
                zIsEmpty = TextUtils.isEmpty(tagIcon);
                z15 = pid != 0;
                int size = list2 != null ? list2.size() : 0;
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
                z13 = length <= 80;
                z14 = size == 0;
                z12 = i3 != 1;
                zIsEmpty2 = TextUtils.isEmpty(vieLevelImg);
            } else {
                z12 = false;
                z13 = false;
                z14 = false;
                z = false;
                str16 = null;
                strValueOf = null;
                zIsEmpty = false;
                z15 = false;
                list2 = null;
                zHideMonthCard = false;
                tagIcon = null;
                dateline = null;
                score = 0;
                tagName = null;
                zIsEmpty2 = false;
                qualityImg = null;
                savingCardImg = null;
                nickName = null;
                user_avatar = null;
                monthCardImg = null;
                zHideSqk = false;
                vieLevelImg = null;
            }
            long j4 = j & 37;
            if (j4 != 0) {
                showText = commentBean != null ? commentBean.getShowText() : null;
                boolean z16 = (showText != null ? showText.length() : 0) > 83;
                if (j4 != 0) {
                    j |= z16 ? 128L : 64L;
                }
                str17 = z16 ? "收起" : "展开";
                j3 = 49;
            } else {
                str17 = null;
                j3 = 49;
                showText = null;
            }
            if ((j & j3) != 0) {
                strValueOf2 = String.valueOf(commentBean != null ? commentBean.getLike_num() : 0);
            } else {
                strValueOf2 = null;
            }
            if ((j & 41) != 0) {
                z2 = zIsEmpty2;
                str11 = savingCardImg;
                str12 = nickName;
                str13 = user_avatar;
                str14 = monthCardImg;
                z9 = zHideSqk;
                z8 = z15;
                list = list2;
                i = score;
                str9 = qualityImg;
                z5 = z12;
                z7 = z14;
                str4 = strValueOf;
                str2 = tagIcon;
                z3 = (commentBean != null ? commentBean.getIslikeNum() : 0) == 1;
                str6 = str16;
                str = str17;
                str5 = dateline;
                str7 = vieLevelImg;
            } else {
                str = str17;
                z2 = zIsEmpty2;
                str11 = savingCardImg;
                str12 = nickName;
                str13 = user_avatar;
                str14 = monthCardImg;
                z9 = zHideSqk;
                str7 = vieLevelImg;
                z8 = z15;
                list = list2;
                i = score;
                str9 = qualityImg;
                z5 = z12;
                z7 = z14;
                str4 = strValueOf;
                str2 = tagIcon;
                z3 = false;
                str6 = str16;
                str5 = dateline;
            }
            z4 = zHideMonthCard;
            str10 = strValueOf2;
            str8 = showText;
            String str19 = tagName;
            z6 = z13;
            str3 = str19;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            z = false;
            z2 = false;
            str5 = null;
            str6 = null;
            zIsEmpty = false;
            str7 = null;
            str8 = null;
            str9 = null;
            str10 = null;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
            list = null;
            z7 = false;
            i = 0;
            z8 = false;
            str11 = null;
            str12 = null;
            str13 = null;
            str14 = null;
            z9 = false;
        }
        long j5 = j & 34;
        if (j5 != 0) {
            j2 = 37;
            str15 = str9;
            z10 = (str18 != null ? str18.length() : 0) == 0;
        } else {
            str15 = str9;
            z10 = false;
            j2 = 37;
        }
        if ((j & j2) != 0) {
            z11 = z;
            TextViewBindingAdapter.setText(this.content, str8);
            TextViewBindingAdapter.setText(this.tvFolder, str);
        } else {
            z11 = z;
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.et, str18);
            DataBindingHelper.setViewGone(this.rvEdit, z10);
        }
        if ((32 & j) != 0) {
            drawable = null;
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
        } else {
            drawable = null;
        }
        if ((33 & j) != 0) {
            this.mboundView1.setTitle(str6);
            DataBindingHelper.setViewGone(this.mboundView10, zIsEmpty);
            DataBindingHelper.setImg(this.mboundView11, str2, drawable);
            TextViewBindingAdapter.setText(this.mboundView12, str3);
            TextViewBindingAdapter.setText(this.mboundView16, str5);
            TextViewBindingAdapter.setText(this.mboundView17, str4);
            DataBindingHelper.setViewGone(this.mboundView5, z2);
            DataBindingHelper.setImg(this.mboundView5, str7, drawable);
            DataBindingHelper.setViewGone(this.mboundView9, z11);
            DataBindingHelper.setImg(this.mboundView9, str15, drawable);
            DataBindingHelper.setViewGone(this.ratingbar, z8);
            RatingBarBindingAdapter.setRating(this.ratingbar, i);
            DataBindingHelper.setViewGone(this.rvPic, z7);
            DataBindingHelper.setRvData(this.rvPic, list);
            DataBindingHelper.setViewGone(this.tvFolder, z6);
            DataBindingHelper.setViewGone(this.tvOff, z5);
            DataBindingHelper.setUserIcon(this.userIcon, str13);
            TextViewBindingAdapter.setText(this.userName, str12);
            DataBindingHelper.setViewGone(this.vip1, z9);
            DataBindingHelper.setImg(this.vip1, str11, null);
            DataBindingHelper.setViewGone(this.vip2, z4);
            DataBindingHelper.setImg(this.vip2, str14, null);
        }
        if ((41 & j) != 0) {
            DataBindingHelper.setSelected(this.tvPraise, z3);
        }
        if ((j & 49) != 0) {
            TextViewBindingAdapter.setText(this.tvPraise, str10);
        }
    }
}
