package com.huya.mtp.data;

import com.huya.mtp.data.exception.NoParserException;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.data.exception.ValidationException;
import com.huya.mtp.data.parser.Parser;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.data.transporter.param.Result;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class DataEntity<P extends Params, R extends Result<?>, Rsp> {
    private Parser<R, Rsp> mResponseParser = initResponseParser();

    public abstract P getRequestParams();

    protected abstract Parser<R, Rsp> initResponseParser();

    public void validateResponse(Rsp rsp) throws ValidationException {
    }

    public Rsp decodeResponse(R r) throws ParseException {
        Parser<R, Rsp> parser = this.mResponseParser;
        if (parser == null) {
            throw new NoParserException("result parser is null");
        }
        return parser.decode(r);
    }

    public R encodeResponse(Rsp rsp) throws ParseException {
        Parser<R, Rsp> parser = this.mResponseParser;
        if (parser == null) {
            throw new NoParserException("result parser is null");
        }
        return parser.encode(rsp);
    }
}
