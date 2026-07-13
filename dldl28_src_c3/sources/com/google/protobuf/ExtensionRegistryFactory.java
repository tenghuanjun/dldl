package com.google.protobuf;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
final class ExtensionRegistryFactory {
    static final Class<?> EXTENSION_REGISTRY_CLASS = reflectExtensionRegistry();
    static final String FULL_REGISTRY_CLASS_NAME = "com.google.protobuf.ExtensionRegistry";

    ExtensionRegistryFactory() {
    }

    static Class<?> reflectExtensionRegistry() {
        try {
            return Class.forName(FULL_REGISTRY_CLASS_NAME);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static ExtensionRegistryLite create() {
        ExtensionRegistryLite extensionRegistryLiteInvokeSubclassFactory = invokeSubclassFactory("newInstance");
        return extensionRegistryLiteInvokeSubclassFactory != null ? extensionRegistryLiteInvokeSubclassFactory : new ExtensionRegistryLite();
    }

    public static ExtensionRegistryLite createEmpty() {
        ExtensionRegistryLite extensionRegistryLiteInvokeSubclassFactory = invokeSubclassFactory("getEmptyRegistry");
        return extensionRegistryLiteInvokeSubclassFactory != null ? extensionRegistryLiteInvokeSubclassFactory : ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    static boolean isFullRegistry(ExtensionRegistryLite extensionRegistryLite) {
        Class<?> cls = EXTENSION_REGISTRY_CLASS;
        return cls != null && cls.isAssignableFrom(extensionRegistryLite.getClass());
    }

    private static final ExtensionRegistryLite invokeSubclassFactory(String str) {
        Class<?> cls = EXTENSION_REGISTRY_CLASS;
        if (cls == null) {
            return null;
        }
        try {
            return (ExtensionRegistryLite) cls.getDeclaredMethod(str, null).invoke(null, null);
        } catch (Exception unused) {
            return null;
        }
    }
}
