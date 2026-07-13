package com.google.protobuf;

/* JADX INFO: loaded from: classes3.dex */
interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
