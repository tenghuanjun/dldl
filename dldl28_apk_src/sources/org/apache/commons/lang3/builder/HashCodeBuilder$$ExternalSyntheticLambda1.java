package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.util.function.Function;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class HashCodeBuilder$$ExternalSyntheticLambda1 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((Field) obj).getName();
    }
}
