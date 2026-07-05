package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.sy37sdk.account.alifast.FastLoginConstants;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbstractExpandedDecoder {
    private final GeneralAppIdDecoder generalDecoder;
    private final BitArray information;

    public abstract String parseInformation() throws NotFoundException, FormatException;

    AbstractExpandedDecoder(BitArray bitArray) {
        this.information = bitArray;
        this.generalDecoder = new GeneralAppIdDecoder(bitArray);
    }

    protected final BitArray getInformation() {
        return this.information;
    }

    protected final GeneralAppIdDecoder getGeneralDecoder() {
        return this.generalDecoder;
    }

    public static AbstractExpandedDecoder createDecoder(BitArray bitArray) {
        if (bitArray.get(1)) {
            return new AI01AndOtherAIs(bitArray);
        }
        if (!bitArray.get(2)) {
            return new AnyAIDecoder(bitArray);
        }
        int iExtractNumericValueFromBitArray = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 4);
        if (iExtractNumericValueFromBitArray == 4) {
            return new AI013103decoder(bitArray);
        }
        if (iExtractNumericValueFromBitArray == 5) {
            return new AI01320xDecoder(bitArray);
        }
        int iExtractNumericValueFromBitArray2 = GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 5);
        if (iExtractNumericValueFromBitArray2 == 12) {
            return new AI01392xDecoder(bitArray);
        }
        if (iExtractNumericValueFromBitArray2 == 13) {
            return new AI01393xDecoder(bitArray);
        }
        switch (GeneralAppIdDecoder.extractNumericValueFromBitArray(bitArray, 1, 7)) {
            case 56:
                return new AI013x0x1xDecoder(bitArray, "310", FastLoginConstants.Code.FAILURE_CLICK_OTHER_WAY);
            case 57:
                return new AI013x0x1xDecoder(bitArray, "320", FastLoginConstants.Code.FAILURE_CLICK_OTHER_WAY);
            case 58:
                return new AI013x0x1xDecoder(bitArray, "310", FastLoginConstants.Code.FAILURE_VERIFY_FAIL);
            case 59:
                return new AI013x0x1xDecoder(bitArray, "320", FastLoginConstants.Code.FAILURE_VERIFY_FAIL);
            case 60:
                return new AI013x0x1xDecoder(bitArray, "310", FastLoginConstants.Code.FAILURE_CLICK_BACK);
            case 61:
                return new AI013x0x1xDecoder(bitArray, "320", FastLoginConstants.Code.FAILURE_CLICK_BACK);
            case 62:
                return new AI013x0x1xDecoder(bitArray, "310", "17");
            case 63:
                return new AI013x0x1xDecoder(bitArray, "320", "17");
            default:
                throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(bitArray)));
        }
    }
}
