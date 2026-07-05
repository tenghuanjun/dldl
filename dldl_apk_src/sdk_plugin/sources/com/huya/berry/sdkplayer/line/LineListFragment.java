package com.huya.berry.sdkplayer.line;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.duowan.HUYA.ScreenType;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.live.common.framework.fragment.BaseDialogFragment;
import com.duowan.live.one.module.report.Report;
import com.huya.android.support.v7.widget.LinearLayoutManager;
import com.huya.android.support.v7.widget.RecyclerView;
import com.huya.berry.endlive.event.HuyaSdkInterface;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.module.Player.PlayerHelper;
import com.huya.berry.module.Player.SMObject;
import com.huya.berry.sdkplayer.line.VideoLineAdapter;
import com.sqwan.liveshow.huya.SqR;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LineListFragment extends BaseDialogFragment {
    public static final String TAG = "LineListFragment";
    private boolean mIsLandscape;
    private LinearLayout mLlLineList;
    private LinearLayout mLlRateList;
    private RecyclerView mRvLineList;
    private RecyclerView mRvRateList;
    private VideoLineAdapter mVideoLineAdapter;
    private VideoLineAdapter mVideoRateAdapter;
    private View mViewLayout;
    private List<VideoInfo> mItemLineList = new ArrayList();
    private List<VideoInfo> mItemRateList = new ArrayList();
    private VideoLineAdapter.OnItemClickListener mVideoLineAdapterItemClickListener = new VideoLineAdapter.OnItemClickListener() { // from class: com.huya.berry.sdkplayer.line.LineListFragment.2
        @Override // com.huya.berry.sdkplayer.line.VideoLineAdapter.OnItemClickListener
        public void onItemClick(VideoInfo videoInfo) {
            for (VideoInfo videoInfo2 : LineListFragment.this.mItemLineList) {
                if (videoInfo2.name.equals(videoInfo.name)) {
                    videoInfo2.selected = !videoInfo2.selected;
                    PlayerHelper.line = videoInfo.value;
                    LineListFragment.this.setBitRateData(videoInfo.value);
                    ArkUtils.send(new HuyaSdkInterface.SwitchLive());
                    LineListFragment.this.dismissAllowingStateLoss();
                } else {
                    videoInfo2.selected = false;
                }
            }
            LineListFragment.this.mVideoLineAdapter.notifyDataSetChanged();
        }
    };
    private VideoLineAdapter.OnItemClickListener mVideoRateAdapterItemClickListener = new VideoLineAdapter.OnItemClickListener() { // from class: com.huya.berry.sdkplayer.line.LineListFragment.3
        @Override // com.huya.berry.sdkplayer.line.VideoLineAdapter.OnItemClickListener
        public void onItemClick(VideoInfo videoInfo) {
            for (VideoInfo videoInfo2 : LineListFragment.this.mItemRateList) {
                if (videoInfo2.name.equals(videoInfo.name)) {
                    videoInfo2.selected = !videoInfo2.selected;
                    PlayerHelper.bitRate = videoInfo.value;
                    PlayerHelper.disPlayName = videoInfo.name;
                    ArkUtils.send(new HuyaSdkInterface.SwitchRate());
                    if (videoInfo.value == -1 && videoInfo.name.contains("蓝光")) {
                        if (PlayerHelper.defaultBitRate <= 3000) {
                            Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_3000);
                        } else if (PlayerHelper.defaultBitRate <= 4000) {
                            Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_4000);
                        } else if (PlayerHelper.defaultBitRate > 4000) {
                            Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_ABOVE4K);
                        } else {
                            Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_ABOVE4K);
                        }
                    } else if (videoInfo.value <= 800) {
                        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_800);
                    } else if (videoInfo.value <= 1200) {
                        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_1200);
                    } else if (videoInfo.value <= 2000) {
                        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_2000);
                    } else if (videoInfo.value <= 3000) {
                        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_3000);
                    } else if (videoInfo.value <= 4000) {
                        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_4000);
                    } else if (videoInfo.value > 4000) {
                        Report.event(SdkReportConst.LIVE_FULLSCREEN_QUALITY_ABOVE4K);
                    }
                    LineListFragment.this.dismissAllowingStateLoss();
                } else {
                    videoInfo2.selected = false;
                }
            }
            LineListFragment.this.mVideoRateAdapter.notifyDataSetChanged();
        }
    };
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.huya.berry.sdkplayer.line.LineListFragment.4
        @Override // com.huya.android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }
    };

    public static LineListFragment getInstance(FragmentManager fragmentManager) {
        LineListFragment lineListFragment = (LineListFragment) fragmentManager.findFragmentByTag(TAG);
        return lineListFragment == null ? new LineListFragment() : lineListFragment;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, ResourceUtil.getStyleResIDByName("Widget.FullScreenUp"));
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate;
        this.mIsLandscape = CommonUtil.isScreenLandScape();
        if (PlayerHelper.mScreenType == ScreenType.ST_Horizonal) {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_line_list), (ViewGroup) null);
        } else {
            viewInflate = layoutInflater.inflate(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_fragment_line_list_portrait), (ViewGroup) null);
        }
        initView(viewInflate);
        return viewInflate;
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
        setCancelable(true);
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        VideoLineAdapter videoLineAdapter = this.mVideoLineAdapter;
        if (videoLineAdapter != null) {
            videoLineAdapter.onDestroy();
            this.mVideoLineAdapter = null;
        }
        VideoLineAdapter videoLineAdapter2 = this.mVideoRateAdapter;
        if (videoLineAdapter2 != null) {
            videoLineAdapter2.onDestroy();
            this.mVideoRateAdapter = null;
        }
        this.mRvLineList = null;
        this.mRvRateList = null;
        ArkUtils.send(new CommonEvent.HideBottomUIMenu());
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.duowan.live.common.framework.fragment.BaseDialogFragment, android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }

    private void initView(View view) {
        this.mViewLayout = view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.view_layout));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_line_list));
        this.mLlLineList = linearLayout;
        this.mRvLineList = (RecyclerView) linearLayout.findViewById(ResourceUtil.getIdResIDByName(SqR.id.rv_line_list));
        this.mVideoLineAdapter = new VideoLineAdapter(getActivity());
        this.mRvLineList.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.mRvLineList.setAdapter(this.mVideoLineAdapter);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_rate_list));
        this.mLlRateList = linearLayout2;
        this.mRvRateList = (RecyclerView) linearLayout2.findViewById(ResourceUtil.getIdResIDByName(SqR.id.rv_rate_list));
        this.mVideoRateAdapter = new VideoLineAdapter(getActivity());
        this.mRvRateList.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.mRvRateList.setAdapter(this.mVideoRateAdapter);
        if (PlayerHelper.singleStreamInfo != null) {
            Vector<Integer> vectorLineIndexsSort = lineIndexsSort(PlayerHelper.singleStreamInfo.getLineIndexs());
            for (int i = 0; i < vectorLineIndexsSort.size(); i++) {
                VideoInfo videoInfo = new VideoInfo();
                int iIntValue = vectorLineIndexsSort.get(i).intValue();
                videoInfo.name = String.format(ArkValue.gContext.getString(ResourceUtil.getStringResIDByName(SqR.string.hyberry_line)), Integer.valueOf(iIntValue));
                videoInfo.value = iIntValue;
                if (iIntValue == PlayerHelper.line) {
                    videoInfo.selected = true;
                } else {
                    videoInfo.selected = false;
                }
                this.mItemLineList.add(videoInfo);
            }
        }
        this.mVideoLineAdapter.setData(this.mItemLineList);
        setBitRateData(PlayerHelper.line);
        this.mRvLineList.addOnScrollListener(this.mOnScrollListener);
        this.mVideoLineAdapter.setOnItemClickListener(this.mVideoLineAdapterItemClickListener);
        this.mVideoRateAdapter.setOnItemClickListener(this.mVideoRateAdapterItemClickListener);
        this.mViewLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huya.berry.sdkplayer.line.LineListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LineListFragment.this.dismissAllowingStateLoss();
            }
        });
    }

    private Vector<Integer> lineIndexsSort(Vector<Integer> vector) {
        int size = vector.size();
        int i = 0;
        while (true) {
            int i2 = size - 1;
            if (i >= i2) {
                return vector;
            }
            int i3 = 0;
            while (i3 < i2 - i) {
                int i4 = i3 + 1;
                if (vector.get(i3).intValue() > vector.get(i4).intValue()) {
                    int iIntValue = vector.get(i3).intValue();
                    vector.set(i3, vector.get(i4));
                    vector.set(i4, Integer.valueOf(iIntValue));
                }
                i3 = i4;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBitRateData(int i) {
        SMObject.LineInfo lineInfoByIndex;
        this.mItemRateList = new ArrayList();
        if (PlayerHelper.singleStreamInfo == null || (lineInfoByIndex = PlayerHelper.singleStreamInfo.getLineInfoByIndex(i)) == null) {
            return;
        }
        Vector<SMObject.BitRateInfo> bitRateList = lineInfoByIndex.getBitRateList();
        boolean z = false;
        for (int i2 = 0; i2 < bitRateList.size(); i2++) {
            VideoInfo videoInfo = new VideoInfo();
            SMObject.BitRateInfo bitRateInfo = bitRateList.get(i2);
            videoInfo.name = bitRateInfo.disPlayName;
            videoInfo.value = bitRateInfo.H264BitRate > 0 ? bitRateInfo.H264BitRate : bitRateInfo.H265BitRate;
            if (videoInfo.value == PlayerHelper.bitRate) {
                videoInfo.selected = true;
                PlayerHelper.bitRate = videoInfo.value;
                PlayerHelper.disPlayName = videoInfo.name;
                z = true;
            } else {
                videoInfo.selected = false;
            }
            this.mItemRateList.add(videoInfo);
        }
        if (!z) {
            Iterator<VideoInfo> it = this.mItemRateList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VideoInfo next = it.next();
                if (next.value < PlayerHelper.bitRate) {
                    next.selected = true;
                    PlayerHelper.bitRate = next.value;
                    PlayerHelper.disPlayName = next.name;
                    ArkUtils.send(new HuyaSdkInterface.SwitchRate());
                    break;
                }
            }
        }
        this.mVideoRateAdapter.setData(this.mItemRateList);
    }
}
