package com.volcengine.common.extensions.api.gamepad;

import com.volcengine.common.innerapi.HttpService;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface GamePadExtension {
    void get(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, HttpService.Callback callback);

    void post(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, String str, HttpService.Callback callback);

    void reportCommon(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3);
}
