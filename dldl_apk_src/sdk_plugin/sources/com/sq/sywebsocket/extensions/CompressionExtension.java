package com.sq.sywebsocket.extensions;

import com.sq.sywebsocket.exceptions.InvalidDataException;
import com.sq.sywebsocket.exceptions.InvalidFrameException;
import com.sq.sywebsocket.framing.ControlFrame;
import com.sq.sywebsocket.framing.DataFrame;
import com.sq.sywebsocket.framing.Framedata;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class CompressionExtension extends DefaultExtension {
    @Override // com.sq.sywebsocket.extensions.DefaultExtension, com.sq.sywebsocket.extensions.IExtension
    public void isFrameValid(Framedata framedata) throws InvalidDataException {
        if ((framedata instanceof DataFrame) && (framedata.isRSV2() || framedata.isRSV3())) {
            throw new InvalidFrameException("bad rsv RSV1: " + framedata.isRSV1() + " RSV2: " + framedata.isRSV2() + " RSV3: " + framedata.isRSV3());
        }
        if (framedata instanceof ControlFrame) {
            if (framedata.isRSV1() || framedata.isRSV2() || framedata.isRSV3()) {
                throw new InvalidFrameException("bad rsv RSV1: " + framedata.isRSV1() + " RSV2: " + framedata.isRSV2() + " RSV3: " + framedata.isRSV3());
            }
        }
    }
}
