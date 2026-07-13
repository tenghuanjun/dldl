package com.shuyu.gsyvideoplayer.player;

import com.shuyu.gsyvideoplayer.model.GSYModel;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class BasePlayerManager implements IPlayerManager {
    protected IPlayerInitSuccessListener mPlayerInitSuccessListener;

    public IPlayerInitSuccessListener getPlayerPreparedSuccessListener() {
        return this.mPlayerInitSuccessListener;
    }

    public void setPlayerInitSuccessListener(IPlayerInitSuccessListener iPlayerInitSuccessListener) {
        this.mPlayerInitSuccessListener = iPlayerInitSuccessListener;
    }

    protected void initSuccess(GSYModel gSYModel) {
        IPlayerInitSuccessListener iPlayerInitSuccessListener = this.mPlayerInitSuccessListener;
        if (iPlayerInitSuccessListener != null) {
            iPlayerInitSuccessListener.onPlayerInitSuccess(getMediaPlayer(), gSYModel);
        }
    }
}
