package com.huya.berry.gamesdk.gameid;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GameIdOptions {
    private static GameIdOptions mInstance = new GameIdOptions();
    public String[] gameIdArr;

    public static GameIdOptions getInstance() {
        return mInstance;
    }
}
