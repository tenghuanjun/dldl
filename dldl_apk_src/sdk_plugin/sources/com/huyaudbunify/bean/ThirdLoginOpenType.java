package com.huyaudbunify.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum ThirdLoginOpenType {
    GG(0),
    FB(1),
    TW(2),
    WX(3),
    QQ(4),
    WB(5),
    LETV(6),
    LENOVO(7),
    QIE(8),
    TT(9),
    WXMP(10),
    GAME_QQ(11),
    GAME_WX(12),
    INS(13),
    LINE(14),
    ZALO(15),
    GAME_QQ_OTHER(16),
    GAME_WX_OTHER(17),
    TECENT_GAME(18),
    STEAM(19),
    YY(20),
    VK(21),
    BX(22),
    QQMP(23),
    APPLE(24),
    HW(25),
    YOUZAN(26),
    HUYA_WX(27),
    PUBG(28),
    HY(29),
    WXGZ(30);

    private int openType;

    ThirdLoginOpenType(int i) {
        this.openType = i;
    }

    public int getOpenType() {
        return this.openType;
    }
}
