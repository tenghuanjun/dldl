package com.alipay.deviceid.module.x;

import com.alipay.deviceid.module.rpc.mrpc.core.HttpException;
import com.alipay.deviceid.module.rpc.mrpc.core.RpcException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class as extends ao {
    private aq g;

    public as(aq aqVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.g = aqVar;
    }

    @Override // com.alipay.deviceid.module.x.bc
    public final Object a() {
        av avVar = new av(this.g.a());
        avVar.b = this.b;
        avVar.c = this.e;
        avVar.e = this.f;
        avVar.a("id", String.valueOf(this.d));
        avVar.a("operationType", this.c);
        avVar.a("gzip", String.valueOf(this.g.d()));
        avVar.a(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> list = this.g.c().b;
        if (list != null && !list.isEmpty()) {
            Iterator<Header> it = list.iterator();
            while (it.hasNext()) {
                avVar.a(it.next());
            }
        }
        StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(avVar.toString());
        try {
            bb bbVar = this.g.b().a(avVar).get();
            if (bbVar != null) {
                return bbVar.a();
            }
            throw new RpcException((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(13, "", e);
        } catch (CancellationException e2) {
            throw new RpcException(13, "", e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(9, "", e3);
            }
            HttpException httpException = (HttpException) cause;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        }
    }
}
