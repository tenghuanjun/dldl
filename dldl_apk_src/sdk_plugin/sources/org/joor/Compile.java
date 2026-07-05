package org.joor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class Compile {

    @FunctionalInterface
    interface ThrowingBiFunction<T, U, R> {
        R apply(T t, U u) throws Exception;
    }

    Compile() {
    }

    static Class<?> compile(String str, String str2, CompileOptions compileOptions) {
        final ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException unused) {
            JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
            try {
                ClassFileManager classFileManager = new ClassFileManager(systemJavaCompiler.getStandardFileManager((DiagnosticListener) null, (Locale) null, (Charset) null));
                ArrayList arrayList = new ArrayList();
                arrayList.add(new CharSequenceJavaFileObject(str, str2));
                StringWriter stringWriter = new StringWriter();
                ArrayList arrayList2 = new ArrayList(compileOptions.options);
                if (!arrayList2.contains("-classpath")) {
                    StringBuilder sb = new StringBuilder();
                    String property = System.getProperty("path.separator");
                    String property2 = System.getProperty("java.class.path");
                    if (property2 != null && !"".equals(property2)) {
                        sb.append(property2);
                    }
                    if (classLoader instanceof URLClassLoader) {
                        for (URL url : ((URLClassLoader) classLoader).getURLs()) {
                            if (sb.length() > 0) {
                                sb.append(property);
                            }
                            if ("file".equals(url.getProtocol())) {
                                sb.append(new File(url.toURI()));
                            }
                        }
                    }
                    arrayList2.addAll(Arrays.asList("-classpath", sb.toString()));
                }
                JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(stringWriter, classFileManager, (DiagnosticListener) null, arrayList2, (Iterable) null, arrayList);
                if (!compileOptions.processors.isEmpty()) {
                    task.setProcessors(compileOptions.processors);
                }
                task.call();
                if (classFileManager.isEmpty()) {
                    throw new ReflectException("Compilation error: " + stringWriter);
                }
                if (Reflect.CACHED_LOOKUP_CONSTRUCTOR != null) {
                    return classFileManager.loadAndReturnMainClass(str, new ThrowingBiFunction() { // from class: org.joor.-$$Lambda$Compile$IDn2vivNgHNKbkj2qyuRrMcKsA8
                        @Override // org.joor.Compile.ThrowingBiFunction
                        public final Object apply(Object obj, Object obj2) {
                            return Compile.lambda$compile$0(classLoader, (String) obj, (byte[]) obj2);
                        }
                    });
                }
                return null;
            } catch (ReflectException e) {
                throw e;
            } catch (Exception e2) {
                throw new ReflectException("Error while compiling " + str, e2);
            }
        }
    }

    static /* synthetic */ Class lambda$compile$0(ClassLoader classLoader, String str, byte[] bArr) throws Exception {
        return (Class) Reflect.on(classLoader).call("defineClass", str, bArr, 0, Integer.valueOf(bArr.length)).get();
    }

    static final class JavaFileObject extends SimpleJavaFileObject {
        final ByteArrayOutputStream os;

        JavaFileObject(String str, JavaFileObject.Kind kind) {
            super(URI.create("string:///" + str.replace('.', '/') + kind.extension), kind);
            this.os = new ByteArrayOutputStream();
        }

        byte[] getBytes() {
            return this.os.toByteArray();
        }

        public OutputStream openOutputStream() {
            return this.os;
        }

        public CharSequence getCharContent(boolean z) {
            return new String(this.os.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    static final class ClassFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
        private Map<String, byte[]> classes;
        private final Map<String, JavaFileObject> fileObjectMap;

        ClassFileManager(StandardJavaFileManager standardJavaFileManager) {
            super(standardJavaFileManager);
            this.fileObjectMap = new HashMap();
        }

        public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) {
            JavaFileObject javaFileObject = new JavaFileObject(str, kind);
            this.fileObjectMap.put(str, javaFileObject);
            return javaFileObject;
        }

        boolean isEmpty() {
            return this.fileObjectMap.isEmpty();
        }

        Map<String, byte[]> classes() {
            if (this.classes == null) {
                this.classes = new HashMap();
                for (Map.Entry<String, JavaFileObject> entry : this.fileObjectMap.entrySet()) {
                    this.classes.put(entry.getKey(), entry.getValue().getBytes());
                }
            }
            return this.classes;
        }

        Class<?> loadAndReturnMainClass(String str, ThrowingBiFunction<String, byte[], Class<?>> throwingBiFunction) throws Exception {
            Class<?> cls = null;
            for (Map.Entry<String, byte[]> entry : classes().entrySet()) {
                Class<?> clsApply = throwingBiFunction.apply(entry.getKey(), entry.getValue());
                if (str.equals(entry.getKey())) {
                    cls = clsApply;
                }
            }
            return cls;
        }
    }

    static final class CharSequenceJavaFileObject extends SimpleJavaFileObject {
        final CharSequence content;

        public CharSequenceJavaFileObject(String str, CharSequence charSequence) {
            super(URI.create("string:///" + str.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.content = charSequence;
        }

        public CharSequence getCharContent(boolean z) {
            return this.content;
        }
    }
}
