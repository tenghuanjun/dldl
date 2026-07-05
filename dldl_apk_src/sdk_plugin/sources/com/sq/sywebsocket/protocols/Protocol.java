package com.sq.sywebsocket.protocols;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Protocol implements IProtocol {
    private final String providedProtocol;
    private static final Pattern patternSpace = Pattern.compile(" ");
    private static final Pattern patternComma = Pattern.compile(",");

    public Protocol(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.providedProtocol = str;
    }

    @Override // com.sq.sywebsocket.protocols.IProtocol
    public boolean acceptProvidedProtocol(String str) {
        if ("".equals(this.providedProtocol)) {
            return true;
        }
        for (String str2 : patternComma.split(patternSpace.matcher(str).replaceAll(""))) {
            if (this.providedProtocol.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sq.sywebsocket.protocols.IProtocol
    public String getProvidedProtocol() {
        return this.providedProtocol;
    }

    @Override // com.sq.sywebsocket.protocols.IProtocol
    public IProtocol copyInstance() {
        return new Protocol(getProvidedProtocol());
    }

    @Override // com.sq.sywebsocket.protocols.IProtocol
    public String toString() {
        return getProvidedProtocol();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.providedProtocol.equals(((Protocol) obj).providedProtocol);
    }

    public int hashCode() {
        return this.providedProtocol.hashCode();
    }
}
