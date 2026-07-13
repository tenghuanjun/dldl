package com.volcengine.e;

import com.volcengine.common.SDKContext;
import com.volcengine.common.extensions.api.gamepad.GamePadExtension;
import com.volcengine.common.innerapi.HttpService;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a implements GamePadExtension {
    @Override // com.volcengine.common.extensions.api.gamepad.GamePadExtension
    public void get(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, HttpService.Callback callback) {
        SDKContext.getHttpService().get(list, list2, map, map2, callback);
    }

    @Override // com.volcengine.common.extensions.api.gamepad.GamePadExtension
    public void post(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, String str, HttpService.Callback callback) {
        SDKContext.getHttpService().post(list, list2, map, map2, str, callback);
    }

    @Override // com.volcengine.common.extensions.api.gamepad.GamePadExtension
    public void reportCommon(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        SDKContext.getMonitorService().reportCommon(str, map, map2, map3);
    }
}
