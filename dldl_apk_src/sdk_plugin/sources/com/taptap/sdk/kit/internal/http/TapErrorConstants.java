package com.taptap.sdk.kit.internal.http;

import kotlin.Metadata;

/* JADX INFO: compiled from: TapErrorConstants.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapErrorConstants;", "", "()V", "ERROR_ACCESS_DENIED", "", "ERROR_AUTHORIZATION_PENDING", "ERROR_BUSINESS_ERROR", "ERROR_CAPTCHA_FAILED", "ERROR_CAPTCHA_NEEDS", "ERROR_FORBIDDEN", "ERROR_INVALID_CLIENT", "ERROR_INVALID_CREDENTIALS", "ERROR_INVALID_GRANT", "ERROR_INVALID_REQUEST", "ERROR_INVALID_TIME", "ERROR_LOCAL", "ERROR_NOT_FOUND", "ERROR_REPLAY_ATTACKS", "ERROR_SERVER_ERROR", "ERROR_SLOW_DOWN", "ERROR_UNSUPPORTED_GRANT_TYPE", "ERROR_UNSUPPORTED_RESPONSE_TYPE", "ERROR_UNSUPPORTED_SECRET_TYPE", "ERROR_USER_IS_DEACTIVATED", "ERROR_too_many_login_attempts", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapErrorConstants {
    public static final String ERROR_ACCESS_DENIED = "access_denied";
    public static final String ERROR_AUTHORIZATION_PENDING = "authorization_pending";
    public static final String ERROR_BUSINESS_ERROR = "business_code_error";
    public static final String ERROR_CAPTCHA_FAILED = "captcha.failed";
    public static final String ERROR_CAPTCHA_NEEDS = "captcha.needs";
    public static final String ERROR_FORBIDDEN = "forbidden";
    public static final String ERROR_INVALID_CLIENT = "invalid_client";
    public static final String ERROR_INVALID_CREDENTIALS = "invalid_credentials";
    public static final String ERROR_INVALID_GRANT = "invalid_grant";
    public static final String ERROR_INVALID_REQUEST = "invalid_request";
    public static final String ERROR_INVALID_TIME = "invalid_time";
    public static final String ERROR_LOCAL = "error_local";
    public static final String ERROR_NOT_FOUND = "not_found";
    public static final String ERROR_REPLAY_ATTACKS = "replay_attacks";
    public static final String ERROR_SERVER_ERROR = "server_error";
    public static final String ERROR_SLOW_DOWN = "slow_down";
    public static final String ERROR_UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";
    public static final String ERROR_UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
    public static final String ERROR_UNSUPPORTED_SECRET_TYPE = "unsupported_secret_type";
    public static final String ERROR_USER_IS_DEACTIVATED = "user_is_deactivated";
    public static final String ERROR_too_many_login_attempts = "too_many_login_attempts";
    public static final TapErrorConstants INSTANCE = new TapErrorConstants();

    private TapErrorConstants() {
    }
}
