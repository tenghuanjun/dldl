package org.joor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Processor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class CompileOptions {
    final List<String> options;
    final List<? extends Processor> processors;

    public CompileOptions() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    private CompileOptions(List<? extends Processor> list, List<String> list2) {
        this.processors = list;
        this.options = list2;
    }

    public final CompileOptions processors(Processor... processorArr) {
        return processors(Arrays.asList(processorArr));
    }

    public final CompileOptions processors(List<? extends Processor> list) {
        return new CompileOptions(list, this.options);
    }

    public final CompileOptions options(String... strArr) {
        return options(Arrays.asList(strArr));
    }

    public final CompileOptions options(List<String> list) {
        return new CompileOptions(this.processors, list);
    }
}
