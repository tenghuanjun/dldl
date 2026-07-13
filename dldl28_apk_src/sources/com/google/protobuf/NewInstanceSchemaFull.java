package com.google.protobuf;

import com.google.protobuf.GeneratedMessageV3;

/* JADX INFO: loaded from: classes3.dex */
final class NewInstanceSchemaFull implements NewInstanceSchema {
    NewInstanceSchemaFull() {
    }

    @Override // com.google.protobuf.NewInstanceSchema
    public Object newInstance(Object obj) {
        return ((GeneratedMessageV3) obj).newInstance(GeneratedMessageV3.UnusedPrivateParameter.INSTANCE);
    }
}
