package com.lzy.okgo.convert;

import android.os.Environment;
import android.text.TextUtils;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.utils.HttpUtils;
import com.lzy.okgo.utils.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes3.dex */
public class FileConvert implements Converter<File> {
    public static final String DM_TARGET_FOLDER = File.separator + "download" + File.separator;
    private Callback<File> callback;
    private String fileName;
    private String folder;

    public FileConvert() {
        this(null);
    }

    public FileConvert(String str) {
        this(Environment.getExternalStorageDirectory() + DM_TARGET_FOLDER, str);
    }

    public FileConvert(String str, String str2) {
        this.folder = str;
        this.fileName = str2;
    }

    public void setCallback(Callback<File> callback) {
        this.callback = callback;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lzy.okgo.convert.Converter
    public File convertResponse(Response response) throws Throwable {
        FileOutputStream fileOutputStream;
        String url = response.request().url().getUrl();
        if (TextUtils.isEmpty(this.folder)) {
            this.folder = Environment.getExternalStorageDirectory() + DM_TARGET_FOLDER;
        }
        if (TextUtils.isEmpty(this.fileName)) {
            this.fileName = HttpUtils.getNetFileName(response, url);
        }
        File file = new File(this.folder);
        IOUtils.createFolder(file);
        File file2 = new File(file, this.fileName);
        IOUtils.delFileOrFolder(file2);
        byte[] bArr = new byte[8192];
        InputStream inputStream = null;
        try {
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody != null) {
                InputStream inputStreamByteStream = responseBodyBody.byteStream();
                try {
                    Progress progress = new Progress();
                    progress.totalSize = responseBodyBody.contentLength();
                    progress.fileName = this.fileName;
                    progress.filePath = file2.getAbsolutePath();
                    progress.status = 2;
                    progress.url = url;
                    progress.tag = url;
                    fileOutputStream = new FileOutputStream(file2);
                    while (true) {
                        try {
                            int i = inputStreamByteStream.read(bArr);
                            if (i != -1) {
                                fileOutputStream.write(bArr, 0, i);
                                if (this.callback != null) {
                                    Progress.changeProgress(progress, i, new Progress.Action() { // from class: com.lzy.okgo.convert.FileConvert.1
                                        @Override // com.lzy.okgo.model.Progress.Action
                                        public void call(Progress progress2) {
                                            FileConvert.this.onProgress(progress2);
                                        }
                                    });
                                }
                            } else {
                                fileOutputStream.flush();
                                IOUtils.closeQuietly(inputStreamByteStream);
                                IOUtils.closeQuietly(fileOutputStream);
                                return file2;
                            }
                        } catch (Throwable th) {
                            th = th;
                            inputStream = inputStreamByteStream;
                            IOUtils.closeQuietly(inputStream);
                            IOUtils.closeQuietly(fileOutputStream);
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                }
            } else {
                IOUtils.closeQuietly(null);
                IOUtils.closeQuietly(null);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);
        throw th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgress(final Progress progress) {
        HttpUtils.runOnUiThread(new Runnable() { // from class: com.lzy.okgo.convert.FileConvert.2
            @Override // java.lang.Runnable
            public void run() {
                FileConvert.this.callback.downloadProgress(progress);
            }
        });
    }
}
