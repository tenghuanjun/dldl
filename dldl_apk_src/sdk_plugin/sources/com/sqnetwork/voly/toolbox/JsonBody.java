package com.sqnetwork.voly.toolbox;

import com.sq.tools.network.ContentType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class JsonBody extends RequestBody {
    private static final MediaType CONTENT_TYPE = MediaType.get(ContentType.JSON);
    private final byte[] mContent;
    private final MediaType mMediaType;

    public JsonBody(JSONObject json) {
        this(CONTENT_TYPE, json);
    }

    public JsonBody(MediaType mediaType, JSONObject json) {
        this(mediaType, json.toString().getBytes(StandardCharsets.UTF_8));
    }

    public JsonBody(MediaType mediaType, byte[] bytes) {
        this.mMediaType = mediaType;
        this.mContent = bytes;
    }

    public JSONObject getJson() throws JSONException {
        return new JSONObject(new String(this.mContent, StandardCharsets.UTF_8));
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.mMediaType;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return this.mContent.length;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        sink.write(this.mContent);
    }
}
