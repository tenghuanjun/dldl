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
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: TapTapAccount.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223Ba\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eB;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003JK\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0007HÖ\u0001J!\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201HÇ\u0001R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0016R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0016R\u001c\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u0016¨\u00064"}, d2 = {"Lcom/taptap/sdk/login/TapTapAccount;", "", "seen1", "", "accessToken", "Lcom/taptap/sdk/login/AccessToken;", "openId", "", "unionId", "name", "avatar", "email", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/taptap/sdk/login/AccessToken;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/taptap/sdk/login/AccessToken;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken$annotations", "()V", "getAccessToken", "()Lcom/taptap/sdk/login/AccessToken;", "getAvatar$annotations", "getAvatar", "()Ljava/lang/String;", "getEmail$annotations", "getEmail", "getName$annotations", BillingClientConstants.METHOD_GET_PRODUCT_NAME, "getOpenId$annotations", "getOpenId", "getUnionId$annotations", "getUnionId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", SqConstants.OAUTH_SQ, "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Serializable
public final /* data */ class TapTapAccount {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AccessToken accessToken;
    private final String avatar;
    private final String email;
    private final String name;
    private final String openId;
    private final String unionId;

    public static /* synthetic */ TapTapAccount copy$default(TapTapAccount tapTapAccount, AccessToken accessToken, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            accessToken = tapTapAccount.accessToken;
        }
        if ((i & 2) != 0) {
            str = tapTapAccount.openId;
        }
        String str6 = str;
        if ((i & 4) != 0) {
            str2 = tapTapAccount.unionId;
        }
        String str7 = str2;
        if ((i & 8) != 0) {
            str3 = tapTapAccount.name;
        }
        String str8 = str3;
        if ((i & 16) != 0) {
            str4 = tapTapAccount.avatar;
        }
        String str9 = str4;
        if ((i & 32) != 0) {
            str5 = tapTapAccount.email;
        }
        return tapTapAccount.copy(accessToken, str6, str7, str8, str9, str5);
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

    @SerialName("unionid")
    public static /* synthetic */ void getUnionId$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AccessToken getAccessToken() {
        return this.accessToken;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getOpenId() {
        return this.openId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUnionId() {
        return this.unionId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAvatar() {
        return this.avatar;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    public final TapTapAccount copy(AccessToken accessToken, String openId, String unionId, String name, String avatar, String email) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(openId, "openId");
        Intrinsics.checkNotNullParameter(unionId, "unionId");
        return new TapTapAccount(accessToken, openId, unionId, name, avatar, email);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TapTapAccount)) {
            return false;
        }
        TapTapAccount tapTapAccount = (TapTapAccount) other;
        return Intrinsics.areEqual(this.accessToken, tapTapAccount.accessToken) && Intrinsics.areEqual(this.openId, tapTapAccount.openId) && Intrinsics.areEqual(this.unionId, tapTapAccount.unionId) && Intrinsics.areEqual(this.name, tapTapAccount.name) && Intrinsics.areEqual(this.avatar, tapTapAccount.avatar) && Intrinsics.areEqual(this.email, tapTapAccount.email);
    }

    public int hashCode() {
        int iHashCode = ((((this.accessToken.hashCode() * 31) + this.openId.hashCode()) * 31) + this.unionId.hashCode()) * 31;
        String str = this.name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.avatar;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.email;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "TapTapAccount(accessToken=" + this.accessToken + ", openId=" + this.openId + ", unionId=" + this.unionId + ", name=" + this.name + ", avatar=" + this.avatar + ", email=" + this.email + ')';
    }

    /* JADX INFO: compiled from: TapTapAccount.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/taptap/sdk/login/TapTapAccount$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/taptap/sdk/login/TapTapAccount;", "tap-login-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<TapTapAccount> serializer() {
            return TapTapAccount$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TapTapAccount(int i, @SerialName(SqConstants.ACCESS_TOKEN) AccessToken accessToken, @SerialName(SqConstants.OPEN_ID) String str, @SerialName("unionid") String str2, @SerialName("name") String str3, @SerialName("avatar") String str4, @SerialName("email") String str5, SerializationConstructorMarker serializationConstructorMarker) {
        if (63 != (i & 63)) {
            PluginExceptionsKt.throwMissingFieldException(i, 63, TapTapAccount$$serializer.INSTANCE.getDescriptor());
        }
        this.accessToken = accessToken;
        this.openId = str;
        this.unionId = str2;
        this.name = str3;
        this.avatar = str4;
        this.email = str5;
    }

    public TapTapAccount(AccessToken accessToken, String openId, String unionId, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(openId, "openId");
        Intrinsics.checkNotNullParameter(unionId, "unionId");
        this.accessToken = accessToken;
        this.openId = openId;
        this.unionId = unionId;
        this.name = str;
        this.avatar = str2;
        this.email = str3;
    }

    @JvmStatic
    public static final void write$Self(TapTapAccount self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, AccessToken$$serializer.INSTANCE, self.accessToken);
        output.encodeStringElement(serialDesc, 1, self.openId);
        output.encodeStringElement(serialDesc, 2, self.unionId);
        output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.name);
        output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.avatar);
        output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.email);
    }

    public final AccessToken getAccessToken() {
        return this.accessToken;
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
