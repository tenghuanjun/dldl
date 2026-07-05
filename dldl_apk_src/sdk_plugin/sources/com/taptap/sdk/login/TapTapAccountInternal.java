package com.taptap.sdk.login;

import com.sqwan.common.constants.SqConstants;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
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

/* JADX INFO: compiled from: TapTapAccountInternal.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 72\u00020\u0001:\u000267Bm\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fBG\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b¢\u0006\u0002\u0010\u0010J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\bHÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003JO\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\t\u0010.\u001a\u00020\bHÖ\u0001J!\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205HÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u000b\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\n\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0017R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u0017R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0012\u001a\u0004\b\u001f\u0010\u0014R\u001c\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\u0017¨\u00068"}, d2 = {"Lcom/taptap/sdk/login/TapTapAccountInternal;", "", "seen1", "", "accessToken", "Lcom/taptap/sdk/login/AccessToken;", "sdkToken", "openId", "", "unionId", "name", "avatar", "email", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/taptap/sdk/login/AccessToken;Lcom/taptap/sdk/login/AccessToken;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/taptap/sdk/login/AccessToken;Lcom/taptap/sdk/login/AccessToken;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken$annotations", "()V", "getAccessToken", "()Lcom/taptap/sdk/login/AccessToken;", "getAvatar$annotations", "getAvatar", "()Ljava/lang/String;", "getEmail$annotations", "getEmail", "getName$annotations", BillingClientConstants.METHOD_GET_PRODUCT_NAME, "getOpenId$annotations", "getOpenId", "getSdkToken$annotations", "getSdkToken", "getUnionId$annotations", "getUnionId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class TapTapAccountInternal {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AccessToken accessToken;
    private final String avatar;
    private final String email;
    private final String name;
    private final String openId;
    private final AccessToken sdkToken;
    private final String unionId;

    public static /* synthetic */ TapTapAccountInternal copy$default(TapTapAccountInternal tapTapAccountInternal, AccessToken accessToken, AccessToken accessToken2, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            accessToken = tapTapAccountInternal.accessToken;
        }
        if ((i & 2) != 0) {
            accessToken2 = tapTapAccountInternal.sdkToken;
        }
        AccessToken accessToken3 = accessToken2;
        if ((i & 4) != 0) {
            str = tapTapAccountInternal.openId;
        }
        String str6 = str;
        if ((i & 8) != 0) {
            str2 = tapTapAccountInternal.unionId;
        }
        String str7 = str2;
        if ((i & 16) != 0) {
            str3 = tapTapAccountInternal.name;
        }
        String str8 = str3;
        if ((i & 32) != 0) {
            str4 = tapTapAccountInternal.avatar;
        }
        String str9 = str4;
        if ((i & 64) != 0) {
            str5 = tapTapAccountInternal.email;
        }
        return tapTapAccountInternal.copy(accessToken, accessToken3, str6, str7, str8, str9, str5);
    }

    @SerialName(SqConstants.ACCESS_TOKEN)
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @SerialName("avatar")
    public static /* synthetic */ void getAvatar$annotations() {
    }

    @SerialName("email")
    public static /* synthetic */ void getEmail$annotations() {
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @SerialName(SqConstants.OPEN_ID)
    public static /* synthetic */ void getOpenId$annotations() {
    }

    @SerialName("sdk_token")
    public static /* synthetic */ void getSdkToken$annotations() {
    }

    @SerialName("unionid")
    public static /* synthetic */ void getUnionId$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AccessToken getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AccessToken getSdkToken() {
        return this.sdkToken;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getOpenId() {
        return this.openId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUnionId() {
        return this.unionId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAvatar() {
        return this.avatar;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final TapTapAccountInternal copy(AccessToken accessToken, AccessToken sdkToken, String openId, String unionId, String name, String avatar, String email) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
        Intrinsics.checkNotNullParameter(openId, "openId");
        Intrinsics.checkNotNullParameter(unionId, "unionId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        Intrinsics.checkNotNullParameter(email, "email");
        return new TapTapAccountInternal(accessToken, sdkToken, openId, unionId, name, avatar, email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TapTapAccountInternal)) {
            return false;
        }
        TapTapAccountInternal tapTapAccountInternal = (TapTapAccountInternal) other;
        return Intrinsics.areEqual(this.accessToken, tapTapAccountInternal.accessToken) && Intrinsics.areEqual(this.sdkToken, tapTapAccountInternal.sdkToken) && Intrinsics.areEqual(this.openId, tapTapAccountInternal.openId) && Intrinsics.areEqual(this.unionId, tapTapAccountInternal.unionId) && Intrinsics.areEqual(this.name, tapTapAccountInternal.name) && Intrinsics.areEqual(this.avatar, tapTapAccountInternal.avatar) && Intrinsics.areEqual(this.email, tapTapAccountInternal.email);
    }

    public int hashCode() {
        return (((((((((((this.accessToken.hashCode() * 31) + this.sdkToken.hashCode()) * 31) + this.openId.hashCode()) * 31) + this.unionId.hashCode()) * 31) + this.name.hashCode()) * 31) + this.avatar.hashCode()) * 31) + this.email.hashCode();
    }

    public String toString() {
        return "TapTapAccountInternal(accessToken=" + this.accessToken + ", sdkToken=" + this.sdkToken + ", openId=" + this.openId + ", unionId=" + this.unionId + ", name=" + this.name + ", avatar=" + this.avatar + ", email=" + this.email + ')';
    }

    /* JADX INFO: compiled from: TapTapAccountInternal.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/login/TapTapAccountInternal$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/login/TapTapAccountInternal;", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<TapTapAccountInternal> serializer() {
            return TapTapAccountInternal$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TapTapAccountInternal(int i, @SerialName(SqConstants.ACCESS_TOKEN) AccessToken accessToken, @SerialName("sdk_token") AccessToken accessToken2, @SerialName(SqConstants.OPEN_ID) String str, @SerialName("unionid") String str2, @SerialName("name") String str3, @SerialName("avatar") String str4, @SerialName("email") String str5, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, TapTapAccountInternal$$serializer.INSTANCE.getDescriptor());
        }
        this.accessToken = accessToken;
        this.sdkToken = accessToken2;
        if ((i & 4) == 0) {
            this.openId = "";
        } else {
            this.openId = str;
        }
        if ((i & 8) == 0) {
            this.unionId = "";
        } else {
            this.unionId = str2;
        }
        if ((i & 16) == 0) {
            this.name = "";
        } else {
            this.name = str3;
        }
        if ((i & 32) == 0) {
            this.avatar = "";
        } else {
            this.avatar = str4;
        }
        if ((i & 64) == 0) {
            this.email = "";
        } else {
            this.email = str5;
        }
    }

    public TapTapAccountInternal(AccessToken accessToken, AccessToken sdkToken, String openId, String unionId, String name, String avatar, String email) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(sdkToken, "sdkToken");
        Intrinsics.checkNotNullParameter(openId, "openId");
        Intrinsics.checkNotNullParameter(unionId, "unionId");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(avatar, "avatar");
        Intrinsics.checkNotNullParameter(email, "email");
        this.accessToken = accessToken;
        this.sdkToken = sdkToken;
        this.openId = openId;
        this.unionId = unionId;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
    }

    @JvmStatic
    public static final void write$Self(TapTapAccountInternal self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, AccessToken$$serializer.INSTANCE, self.accessToken);
        output.encodeSerializableElement(serialDesc, 1, AccessToken$$serializer.INSTANCE, self.sdkToken);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || !Intrinsics.areEqual(self.openId, "")) {
            output.encodeStringElement(serialDesc, 2, self.openId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.unionId, "")) {
            output.encodeStringElement(serialDesc, 3, self.unionId);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.name, "")) {
            output.encodeStringElement(serialDesc, 4, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.avatar, "")) {
            output.encodeStringElement(serialDesc, 5, self.avatar);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || !Intrinsics.areEqual(self.email, "")) {
            output.encodeStringElement(serialDesc, 6, self.email);
        }
    }

    public /* synthetic */ TapTapAccountInternal(AccessToken accessToken, AccessToken accessToken2, String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(accessToken, accessToken2, (i & 4) != 0 ? "" : str, (i & 8) != 0 ? "" : str2, (i & 16) != 0 ? "" : str3, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? "" : str5);
    }

    public final AccessToken getAccessToken() {
        return this.accessToken;
    }

    public final AccessToken getSdkToken() {
        return this.sdkToken;
    }

    public final String getOpenId() {
        return this.openId;
    }

    public final String getUnionId() {
        return this.unionId;
    }

    public final String getName() {
        return this.name;
    }

    public final String getAvatar() {
        return this.avatar;
    }

    public final String getEmail() {
        return this.email;
    }
}
