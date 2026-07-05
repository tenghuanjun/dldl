package com.google.zxing.pdf417.encoder;

import androidx.constraintlayout.solver.widgets.Optimizer;
import com.alibaba.fastjson.asm.Opcodes;
import com.aliyun.aliyunface.ToygerConst;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.google.zxing.WriterException;
import com.google.zxing.pdf417.PDF417Common;
import com.nirvana.tools.crash.BuildConfig;
import com.social.sdk.common.Code;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
final class PDF417ErrorCorrection {
    private static final int[][] EC_COEFFICIENTS = {new int[]{27, 917}, new int[]{522, 568, 723, 809}, new int[]{237, StatusLine.HTTP_PERM_REDIRECT, 436, 284, 646, 653, 428, 379}, new int[]{274, TTAdConstant.STYLE_SIZE_RADIO_9_16, 232, 755, 599, 524, 801, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_ID, 295, 116, 442, 428, 295, 42, Opcodes.ARETURN, 65}, new int[]{361, 575, 922, 525, Opcodes.ARETURN, 586, 640, 321, 536, 742, 677, 742, 687, 284, Opcodes.INSTANCEOF, 517, 273, 494, Optimizer.OPTIMIZATION_STANDARD, 147, 593, 800, 571, 320, 803, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_IS_AD, 231, 390, 685, 330, 63, TTAdConstant.IMAGE_LIST_SIZE_CODE}, new int[]{539, 422, 6, 93, 862, 771, 453, 106, 610, 287, 107, 505, 733, 877, 381, 612, 723, 476, 462, 172, 430, 609, 858, 822, 543, 376, 511, 400, 672, 762, 283, Opcodes.INVOKESTATIC, 440, 35, 519, 31, 460, 594, 225, 535, 517, 352, 605, Opcodes.IFLE, 651, Code.AUTHORIZE_CANCEL_CODE, 488, 502, 648, 733, 717, 83, TTAdConstant.SDK_NOT_SUPPORT_LIVE_MATE_CODE, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, 400, 925, 749, TTAdConstant.VIDEO_COVER_URL_CODE, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, 148, 447, 631, 292, ToygerConst.TOYGER_UI_MSG_GUID_LOAD_LOCAL, 490, 704, 516, 258, 457, ToygerConst.TOYGER_UI_MSG_GUID_LOG, 594, 723, 674, 292, 272, 96, 684, 432, 686, 606, 860, 569, Opcodes.INSTANCEOF, 219, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA, 186, 236, 287, 192, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, 156, 732, 95, 270, 447, 90, 507, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, 602, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, 242, 726, AdBaseConstants.DEFAULT_BROADCAST_CHECK_TIME, 269, 375, 898, 845, 454, 354, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_START_TOAST, 814, 587, 804, 34, 211, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, 108, 539}, new int[]{524, 894, 75, 766, 882, 857, 74, 204, 82, 586, 708, 250, ToygerConst.TOYGER_UI_MSG_GUID_FACE_AUTH, 786, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_DEEP_LINK, 720, 858, 194, 311, ToygerConst.TOYGER_UI_MSG_START_LOADING, 275, 190, 375, 850, 438, 733, 194, 280, Code.AUTHORIZE_CANCEL_CODE, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, 204, 796, 605, 540, ToygerConst.TOYGER_UI_MSG_START_LOADING, 801, 700, 799, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_APP_ICON, 439, TTAdConstant.DEEPLINK_FALL_BACK_CODE, 592, 668, 353, 859, 370, 694, 325, 240, 216, 257, 284, 549, 209, 884, 315, 70, 329, 793, 490, 274, 877, Opcodes.IF_ICMPGE, 749, 812, 684, 461, 334, 376, 849, 521, StatusLine.HTTP_TEMP_REDIRECT, 291, 803, 712, 19, 358, 399, ToygerConst.TOYGER_UI_MSG_GUID_LOAD_LOCAL, 103, 511, 51, 8, 517, 225, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_PACKAGE_NAME, 538, ToygerConst.TOYGER_UI_MSG_GUID_CLOSE, 90, 2, 290, 743, Opcodes.IFNONNULL, 655, ToygerConst.TOYGER_UI_MSG_ERROR_CODE, 329, 49, 802, 580, 355, 588, Opcodes.NEWARRAY, 462, 10, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_MODEL_TYPE, 628, 320, 479, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_START_TOAST, 739, 71, Optimizer.OPTIMIZATION_STANDARD, 318, 374, 601, 192, 605, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_NOTIFICATION_JUMP_URL, 673, 687, 234, 722, 384, Opcodes.RETURN, 752, 607, 640, 455, Opcodes.INSTANCEOF, 689, 707, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_HEADERS, TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, 126, 32, 681, 331, 792, 620, 60, 609, 441, Opcodes.GETFIELD, 791, 893, 754, 605, 383, 228, 749, 760, 213, 54, 297, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_MODEL_TYPE, 54, 834, 299, 922, 191, ToygerConst.TOYGER_UI_MSG_RETRY_FACE_SCAN, 532, 609, 829, 189, 20, Opcodes.GOTO, 29, 872, 449, 83, TTAdConstant.DEEPLINK_UNAVAILABLE_CODE, 41, 656, 505, 579, 481, 173, TTAdConstant.SDK_NOT_SUPPORT_LIVE_MATE_CODE, 251, 688, 95, 497, 555, 642, 543, StatusLine.HTTP_TEMP_REDIRECT, Opcodes.IF_ICMPEQ, 924, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, TTAdConstant.IMAGE_LIST_CODE, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, 155, 914, 299, 229, 643, 294, 871, 306, 88, 87, Opcodes.INSTANCEOF, 352, 781, 846, 75, 327, 520, 435, 543, BuildConfig.VERSION_CODE, TTAdConstant.STYLE_SIZE_RADIO_2_3, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, TTAdConstant.INTERACTION_TYPE_CODE, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, 290, 204, 681, TTAdConstant.DOWNLOAD_URL_CODE, 855, 85, 99, 62, 482, Opcodes.GETFIELD, 20, 297, 451, 593, ToygerConst.TOYGER_UI_MSG_START_LOADING, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_NOTIFICATION_JUMP_URL, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, 513, 192, 516, 258, 240, 518, 794, 395, 768, 848, 51, 610, 384, 168, 190, 826, 328, 596, 786, 303, 570, 381, TTAdConstant.VIDEO_COVER_URL_CODE, 641, 156, 237, 151, 429, 531, 207, 676, 710, 89, 168, 304, TTAdConstant.DEEPLINK_UNAVAILABLE_CODE, 40, 708, 575, Opcodes.IF_ICMPGE, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, TTAdConstant.VALUE_CLICK_AREA_SAAS_AUTH, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_AUTO_INSTALL_WITHOUT_NOTIFICATION, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, ToygerConst.TOYGER_UI_MSG_FACE_COMPLETE, 452, Opcodes.GOTO, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, ToygerConst.TOYGER_UI_MSG_START_PHOTINUS, 283, 711, 472, 420, 245, 288, 594, 394, 511, 327, 589, 777, 699, 688, 43, TTAdConstant.INTERACTION_TYPE_CODE, 842, 383, 721, 521, 560, 644, 714, 559, 62, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_IS_SHOW_NOTIFICATION, 873, 663, 713, Opcodes.IF_ICMPEQ, 672, 729, 624, 59, Opcodes.INSTANCEOF, TTAdConstant.LIVE_FEED_URL_CODE, Opcodes.IFLE, 209, 563, 564, 343, 693, 109, 608, 563, 365, Opcodes.PUTFIELD, 772, 677, 310, 248, 353, 708, TTAdConstant.IMAGE_LIST_SIZE_CODE, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, 424, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, Opcodes.INVOKESTATIC, 45, 787, 680, 18, 66, TTAdConstant.DOWNLOAD_URL_CODE, 369, 54, 492, 228, 613, 830, 922, 437, 519, 644, ToygerConst.TOYGER_UI_MSG_GUID_FACE_AUTH, 789, 420, 305, 441, 207, 300, 892, 827, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_BACKUP_URLS, 537, 381, 662, 513, 56, 252, 341, 242, 797, 838, 837, 720, 224, StatusLine.HTTP_TEMP_REDIRECT, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, 249, 321, 881, 699, 535, 673, 782, 210, 815, ToygerConst.TOYGER_UI_MSG_GUID_FACE_AUTH, 303, 843, 922, 281, 73, 469, 791, 660, Opcodes.IF_ICMPGE, 498, StatusLine.HTTP_PERM_REDIRECT, 155, 422, ToygerConst.TOYGER_UI_MSG_GUID_LOG, 817, Opcodes.NEW, 62, 16, 425, 535, 336, 286, 437, 375, 273, 610, 296, Opcodes.INVOKESPECIAL, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, TTAdConstant.VALUE_CLICK_AREA_SAAS_AUTH, 720, 742, 330, 5, 39, 923, 311, 424, 242, 749, 321, 54, 669, 316, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, 316, 486, 721, 610, 46, 656, 447, 171, 616, 464, 190, 531, 297, 321, 762, 752, 533, 175, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_MODEL_TYPE, 14, 381, 433, 717, 45, 111, 20, 596, 284, 736, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_DEEP_LINK, 646, TTAdConstant.IMAGE_CODE, 877, 669, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_BACKUP_URLS, 919, 45, 780, TTAdConstant.DOWNLOAD_URL_CODE, 164, 332, 899, Opcodes.IF_ACMPEQ, 726, AdBaseConstants.DEFAULT_BROADCAST_CHECK_TIME, 325, 498, 655, TTAdConstant.VALUE_CLICK_AREA_SAAS_AUTH, 752, 768, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, 675, TTAdConstant.IMAGE_LIST_SIZE_CODE, 389, 244, 31, 121, 303, Optimizer.OPTIMIZATION_STANDARD}};

    private PDF417ErrorCorrection() {
    }

    static int getErrorCorrectionCodewordCount(int i) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("Error correction level must be between 0 and 8!");
        }
        return 1 << (i + 1);
    }

    static int getRecommendedMinimumErrorCorrectionLevel(int i) throws WriterException {
        if (i <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        if (i <= 40) {
            return 2;
        }
        if (i <= 160) {
            return 3;
        }
        if (i <= 320) {
            return 4;
        }
        if (i <= 863) {
            return 5;
        }
        throw new WriterException("No recommendation possible");
    }

    static String generateErrorCorrection(CharSequence charSequence, int i) {
        int errorCorrectionCodewordCount = getErrorCorrectionCodewordCount(i);
        char[] cArr = new char[errorCorrectionCodewordCount];
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = errorCorrectionCodewordCount - 1;
            int iCharAt = (charSequence.charAt(i2) + cArr[i3]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i3 > 0) {
                cArr[i3] = (char) ((cArr[i3 - 1] + (929 - ((EC_COEFFICIENTS[i][i3] * iCharAt) % PDF417Common.NUMBER_OF_CODEWORDS))) % PDF417Common.NUMBER_OF_CODEWORDS);
                i3--;
            }
            cArr[0] = (char) ((929 - ((iCharAt * EC_COEFFICIENTS[i][0]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS);
        }
        StringBuilder sb = new StringBuilder(errorCorrectionCodewordCount);
        for (int i4 = errorCorrectionCodewordCount - 1; i4 >= 0; i4--) {
            if (cArr[i4] != 0) {
                cArr[i4] = (char) (929 - cArr[i4]);
            }
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }
}
