package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* JADX INFO: loaded from: classes3.dex */
public interface BlockingRpcChannel {
    Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message, Message message2) throws ServiceException;
}
