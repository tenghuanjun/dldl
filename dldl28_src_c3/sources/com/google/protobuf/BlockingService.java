package com.google.protobuf;

import com.google.protobuf.Descriptors;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface BlockingService {
    Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message message) throws ServiceException;

    Descriptors.ServiceDescriptor getDescriptorForType();

    Message getRequestPrototype(Descriptors.MethodDescriptor methodDescriptor);

    Message getResponsePrototype(Descriptors.MethodDescriptor methodDescriptor);
}
