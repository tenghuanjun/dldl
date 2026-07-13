package com.tencent.mm.opensdk.diffdev.a;

/* JADX INFO: loaded from: classes3.dex */
public enum d {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(500);


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1023a;

    d(int i) {
        this.f1023a = i;
    }

    public int a() {
        return this.f1023a;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "UUIDStatusCode:" + this.f1023a;
    }
}
