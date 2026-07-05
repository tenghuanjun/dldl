package com.taptap.sdk.db.data.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: EventType.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/taptap/sdk/db/data/model/EventType;", "", "(Ljava/lang/String;I)V", "value", "", "getValue", "()Ljava/lang/String;", "TRACK", "DEVICE_INITIALISE", "DEVICE_UPDATE", "DEVICE_ADD", "USER_INITIALISE", "USER_UPDATE", "USER_ADD", "tap-db_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public enum EventType {
    TRACK { // from class: com.taptap.sdk.db.data.model.EventType.TRACK
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "track";
        }
    },
    DEVICE_INITIALISE { // from class: com.taptap.sdk.db.data.model.EventType.DEVICE_INITIALISE
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "initialise";
        }
    },
    DEVICE_UPDATE { // from class: com.taptap.sdk.db.data.model.EventType.DEVICE_UPDATE
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "update";
        }
    },
    DEVICE_ADD { // from class: com.taptap.sdk.db.data.model.EventType.DEVICE_ADD
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "add";
        }
    },
    USER_INITIALISE { // from class: com.taptap.sdk.db.data.model.EventType.USER_INITIALISE
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "initialise";
        }
    },
    USER_UPDATE { // from class: com.taptap.sdk.db.data.model.EventType.USER_UPDATE
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "update";
        }
    },
    USER_ADD { // from class: com.taptap.sdk.db.data.model.EventType.USER_ADD
        @Override // com.taptap.sdk.db.data.model.EventType
        public String getValue() {
            return "add";
        }
    };

    /* synthetic */ EventType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getValue();
}
