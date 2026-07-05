package com.taptap.sdk.retrofit2;

import com.taptap.sdk.retrofit2.CallAdapter;
import com.taptap.sdk.retrofit2.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class BuiltInFactories {
    BuiltInFactories() {
    }

    List<? extends CallAdapter.Factory> createDefaultCallAdapterFactories(@Nullable Executor executor) {
        return Collections.singletonList(new DefaultCallAdapterFactory(executor));
    }

    List<? extends Converter.Factory> createDefaultConverterFactories() {
        return Collections.emptyList();
    }

    static final class Java8 extends BuiltInFactories {
        Java8() {
        }

        @Override // com.taptap.sdk.retrofit2.BuiltInFactories
        List<? extends CallAdapter.Factory> createDefaultCallAdapterFactories(@Nullable Executor executor) {
            return Arrays.asList(new CompletableFutureCallAdapterFactory(), new DefaultCallAdapterFactory(executor));
        }

        @Override // com.taptap.sdk.retrofit2.BuiltInFactories
        List<? extends Converter.Factory> createDefaultConverterFactories() {
            return Collections.singletonList(new OptionalConverterFactory());
        }
    }
}
