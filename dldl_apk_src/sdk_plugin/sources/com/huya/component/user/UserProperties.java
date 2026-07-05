package com.huya.component.user;

import android.graphics.Bitmap;
import com.duowan.auk.asignal.MapProperty;
import com.duowan.auk.asignal.Property;
import com.huya.component.user.api.data.PresenterInfo;
import com.huya.component.user.api.data.PresenterLevel;
import com.huya.component.user.api.data.UserInfo;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserProperties {
    public static final String MarkNickName = "yyNickName";
    public static final Property<String> nickName = new Property<>("", MarkNickName);
    public static final String MarkRoomId = "huyaRoomId";
    public static final Property<Integer> roomId = new Property<>(0, MarkRoomId);
    public static final String MarkSignature = "yySignature";
    public static final Property<String> signature = new Property<>("", MarkSignature);
    public static final String MarkGender = "gender";
    public static final Property<UserInfo.Gender> gender = new Property<>(null, MarkGender);
    public static final Property<String> avatarUrl = new Property<>("");
    public static final String MarkPortrait = "yyPortrait";
    public static final Property<Bitmap> portrait = new Property<>(null, MarkPortrait);
    public static final String MarkAvatar = "yyAvatar";
    public static final Property<Bitmap> avatar = new Property<>(null, MarkAvatar);
    public static final String MarkNobleLevel = "nobleLevel";
    public static final Property<Integer> nobleLevel = new Property<>(0, MarkNobleLevel);
    public static final String MarkReceviedGifts = "receviedGifts";
    public static final Property<Integer> receviedGifts = new Property<>(0, MarkReceviedGifts);
    public static final String MarkSubscribesCount = "subscribesCount";
    public static final Property<Integer> subscribesCount = new Property<>(0, MarkSubscribesCount);
    public static final String MarkUserInfoMap = "yyUserInfoMap";
    public static final MapProperty<Long, UserInfo> userInfoMap = new MapProperty<>(MarkUserInfoMap);
    public static final String MarkTempPortraitUrl = "yyTmepProtraitUrl";
    public static Property<String> tempPortraitUrl = new Property<>(null, MarkTempPortraitUrl);
    public static final String MarkTempNickName = "yyTempNickName";
    public static Property<String> tempNickName = new Property<>(null, MarkTempNickName);
    public static final String MarkYHuyaId = "huyaId";
    public static final Property<String> huyaId = new Property<>("", MarkYHuyaId);
    public static final String MarkHyNick = "hyNick";
    public static final Property<String> hyNick = new Property<>("", MarkHyNick);
    public static final String MarkPresenterInfo = "presenterInfo";
    public static final Property<PresenterInfo> presenterInfo = new Property<>(null, MarkPresenterInfo);
    public static final String MarkPresenterLevel = "presenterLevel";
    public static final Property<PresenterLevel> presenterLevel = new Property<>(null, MarkPresenterLevel);
    public static final String MarkSuperFansPermission = "superFansPermission";
    public static final Property<HashMap<Long, Boolean>> superFansPermission = new Property<>(new HashMap(), MarkSuperFansPermission);
    public static final Property<String> liveUrl = new Property<>("");
}
