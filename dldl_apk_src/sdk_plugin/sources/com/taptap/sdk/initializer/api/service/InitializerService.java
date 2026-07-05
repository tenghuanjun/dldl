package com.taptap.sdk.initializer.api.service;

import com.taptap.sdk.servicemanager.ServiceManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: InitializerService.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH&J\n\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\n\u0010\r\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0011"}, d2 = {"Lcom/taptap/sdk/initializer/api/service/InitializerService;", "Lcom/taptap/sdk/servicemanager/ServiceManager$Service;", "checkInitialize", "", "getAchievementUrl", "", "type", "getAppId", "", "()Ljava/lang/Integer;", "getRelationActivityLoopInterval", "", "getRelationAddFriendUrl", "getRelationNotificationUrl", "getRelationUrl", "getReviewUrl", "getShareUrl", "tap-initializer-api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface InitializerService extends ServiceManager.Service {
    boolean checkInitialize();

    String getAchievementUrl(String type);

    Integer getAppId();

    long getRelationActivityLoopInterval();

    String getRelationAddFriendUrl();

    String getRelationNotificationUrl();

    String getRelationUrl(String type);

    String getReviewUrl(String type);

    String getShareUrl(String type);
}
