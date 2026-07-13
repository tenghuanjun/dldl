package com.google.protobuf;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
interface MutabilityOracle {
    public static final MutabilityOracle IMMUTABLE = new MutabilityOracle() { // from class: com.google.protobuf.MutabilityOracle.1
        @Override // com.google.protobuf.MutabilityOracle
        public void ensureMutable() {
            throw new UnsupportedOperationException();
        }
    };

    void ensureMutable();
}
