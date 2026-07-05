package com.taptap.sdk.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.sqwan.common.constants.SqConstants;
import com.taptap.sdk.login.api.SetAsStringSerializer;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: AccessToken.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 42\u00020\u0001:\u000234BY\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003JA\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001J!\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.HÇ\u0001J\u0019\u0010/\u001a\u00020)2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0012\u001a\u0004\b\u0015\u0010\u0010R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0012\u001a\u0004\b\u001a\u0010\u0010¨\u00065"}, d2 = {"Lcom/taptap/sdk/login/AccessToken;", "Landroid/os/Parcelable;", "seen1", "", "kid", "", "tokenType", "macKey", "macAlgorithm", "scopes", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V", "getKid", "()Ljava/lang/String;", "getMacAlgorithm$annotations", "()V", "getMacAlgorithm", "getMacKey$annotations", "getMacKey", "getScopes$annotations", "getScopes", "()Ljava/util/Set;", "getTokenType$annotations", "getTokenType", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "$serializer", "Companion", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class AccessToken implements Parcelable {
    private final String kid;
    private final String macAlgorithm;
    private final String macKey;
    private final Set<String> scopes;
    private final String tokenType;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<AccessToken> CREATOR = new Creator();

    /* JADX INFO: compiled from: AccessToken.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AccessToken> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AccessToken createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            int i = parcel.readInt();
            LinkedHashSet linkedHashSet = new LinkedHashSet(i);
            for (int i2 = 0; i2 != i; i2++) {
                linkedHashSet.add(parcel.readString());
            }
            return new AccessToken(string, string2, string3, string4, linkedHashSet);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AccessToken[] newArray(int i) {
            return new AccessToken[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AccessToken copy$default(AccessToken accessToken, String str, String str2, String str3, String str4, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            str = accessToken.kid;
        }
        if ((i & 2) != 0) {
            str2 = accessToken.tokenType;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = accessToken.macKey;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = accessToken.macAlgorithm;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            set = accessToken.scopes;
        }
        return accessToken.copy(str, str5, str6, str7, set);
    }

    @SerialName("mac_algorithm")
    public static /* synthetic */ void getMacAlgorithm$annotations() {
    }

    @SerialName("mac_key")
    public static /* synthetic */ void getMacKey$annotations() {
    }

    @SerialName("scope")
    @Serializable(with = SetAsStringSerializer.class)
    public static /* synthetic */ void getScopes$annotations() {
    }

    @SerialName("token_type")
    public static /* synthetic */ void getTokenType$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKid() {
        return this.kid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMacKey() {
        return this.macKey;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMacAlgorithm() {
        return this.macAlgorithm;
    }

    public final Set<String> component5() {
        return this.scopes;
    }

    public final AccessToken copy(String kid, String tokenType, String macKey, String macAlgorithm, Set<String> scopes) {
        Intrinsics.checkNotNullParameter(kid, "kid");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        Intrinsics.checkNotNullParameter(macKey, "macKey");
        Intrinsics.checkNotNullParameter(macAlgorithm, "macAlgorithm");
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        return new AccessToken(kid, tokenType, macKey, macAlgorithm, scopes);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) other;
        return Intrinsics.areEqual(this.kid, accessToken.kid) && Intrinsics.areEqual(this.tokenType, accessToken.tokenType) && Intrinsics.areEqual(this.macKey, accessToken.macKey) && Intrinsics.areEqual(this.macAlgorithm, accessToken.macAlgorithm) && Intrinsics.areEqual(this.scopes, accessToken.scopes);
    }

    public int hashCode() {
        return (((((((this.kid.hashCode() * 31) + this.tokenType.hashCode()) * 31) + this.macKey.hashCode()) * 31) + this.macAlgorithm.hashCode()) * 31) + this.scopes.hashCode();
    }

    public String toString() {
        return "AccessToken(kid=" + this.kid + ", tokenType=" + this.tokenType + ", macKey=" + this.macKey + ", macAlgorithm=" + this.macAlgorithm + ", scopes=" + this.scopes + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeString(this.kid);
        parcel.writeString(this.tokenType);
        parcel.writeString(this.macKey);
        parcel.writeString(this.macAlgorithm);
        Set<String> set = this.scopes;
        parcel.writeInt(set.size());
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
    }

    /* JADX INFO: compiled from: AccessToken.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/login/AccessToken$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/login/AccessToken;", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<AccessToken> serializer() {
            return AccessToken$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ AccessToken(int i, String str, @SerialName("token_type") String str2, @SerialName("mac_key") String str3, @SerialName("mac_algorithm") String str4, @SerialName("scope") @Serializable(with = SetAsStringSerializer.class) Set set, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (i & 31)) {
            PluginExceptionsKt.throwMissingFieldException(i, 31, AccessToken$$serializer.INSTANCE.getDescriptor());
        }
        this.kid = str;
        this.tokenType = str2;
        this.macKey = str3;
        this.macAlgorithm = str4;
        this.scopes = set;
    }

    public AccessToken(String kid, String tokenType, String macKey, String macAlgorithm, Set<String> scopes) {
        Intrinsics.checkNotNullParameter(kid, "kid");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        Intrinsics.checkNotNullParameter(macKey, "macKey");
        Intrinsics.checkNotNullParameter(macAlgorithm, "macAlgorithm");
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        this.kid = kid;
        this.tokenType = tokenType;
        this.macKey = macKey;
        this.macAlgorithm = macAlgorithm;
        this.scopes = scopes;
    }

    @JvmStatic
    public static final void write$Self(AccessToken self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.kid);
        output.encodeStringElement(serialDesc, 1, self.tokenType);
        output.encodeStringElement(serialDesc, 2, self.macKey);
        output.encodeStringElement(serialDesc, 3, self.macAlgorithm);
        output.encodeSerializableElement(serialDesc, 4, SetAsStringSerializer.INSTANCE, self.scopes);
    }

    public final String getKid() {
        return this.kid;
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    public final String getMacKey() {
        return this.macKey;
    }

    public final String getMacAlgorithm() {
        return this.macAlgorithm;
    }

    public final Set<String> getScopes() {
        return this.scopes;
    }
}
