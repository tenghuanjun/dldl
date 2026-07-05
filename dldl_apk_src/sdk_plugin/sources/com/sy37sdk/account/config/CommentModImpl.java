package com.sy37sdk.account.config;

import com.sqwan.common.mod.comment.ICommentMod;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CommentModImpl implements ICommentMod {
    @Override // com.sqwan.common.mod.comment.ICommentMod
    public void jumpComment() {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.COMMENT_SUCCESS);
        ConfigManager.getInstance().jumpComment();
    }

    @Override // com.sqwan.common.mod.comment.ICommentMod
    public void cancelJumpComment() {
        ConfigManager.getInstance().getCommentListener().onFailture(1, "用户取消好评跳转");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.COMMENT_CANCEL);
    }

    @Override // com.sqwan.common.mod.comment.ICommentMod
    public void closeComment() {
        ConfigManager.getInstance().getCommentListener().onFailture(2, "用户关闭好评跳转弹窗");
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.COMMENT_CLOSE);
    }
}
