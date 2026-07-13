package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface ProtocolMessageEnum extends Internal.EnumLite {
    Descriptors.EnumDescriptor getDescriptorForType();

    @Override // com.google.protobuf.Internal.EnumLite
    int getNumber();

    Descriptors.EnumValueDescriptor getValueDescriptor();
}
