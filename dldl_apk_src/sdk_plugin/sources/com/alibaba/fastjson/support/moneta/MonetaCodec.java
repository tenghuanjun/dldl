package com.alibaba.fastjson.support.moneta;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.sqwan.common.constants.SqConstants;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import javax.money.Monetary;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.javamoney.moneta.Money;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MonetaCodec implements ObjectSerializer, ObjectDeserializer {
    public static final MonetaCodec instance = new MonetaCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 0;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        Money money = (Money) obj;
        if (money == null) {
            jSONSerializer.writeNull();
            return;
        }
        SerializeWriter serializeWriter = jSONSerializer.out;
        serializeWriter.writeFieldValue(AbstractJsonLexerKt.BEGIN_OBJ, "numberStripped", money.getNumberStripped());
        serializeWriter.writeFieldValue(AbstractJsonLexerKt.COMMA, SqConstants.CURRENCY, money.getCurrency().getCurrencyCode());
        serializeWriter.write(125);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        String string;
        JSONObject object = defaultJSONParser.parseObject();
        Object obj2 = object.get(SqConstants.CURRENCY);
        if (obj2 instanceof JSONObject) {
            string = ((JSONObject) obj2).getString("currencyCode");
        } else {
            string = obj2 instanceof String ? (String) obj2 : null;
        }
        Object obj3 = object.get("numberStripped");
        if (obj3 instanceof BigDecimal) {
            return (T) Money.of((BigDecimal) obj3, Monetary.getCurrency(string, new String[0]));
        }
        throw new UnsupportedOperationException();
    }
}
