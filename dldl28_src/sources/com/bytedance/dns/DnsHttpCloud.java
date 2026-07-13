package com.bytedance.dns;

import com.mobile.auth.gatewayauth.Constant;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DnsHttpCloud {
    private static final String MATCH_PING_IP = "(?<=from ).*(?=: icmp_seq=1 ttl=)";
    private static final int PING_COUNT = 3;
    private static final int RACE_DNS_SELECT_STRATEGY = 0;
    private static final int RANDOM_DNS_SELECT_STRATEGY = 1;
    private static final int TIMEOUT = 5;
    private final int mDnsSelectStrategy;
    private volatile boolean mIsProcessing = false;
    private final List mProcessedIpList = Collections.synchronizedList(new ArrayList());
    private final List mProcessingIpList = Collections.synchronizedList(new ArrayList());

    private static class PingTask implements Runnable {
        private final CountDownLatch mCountDownLatch;
        private final WrappedIpAddress mPingTarget;

        public PingTask(WrappedIpAddress wrappedIpAddress, CountDownLatch countDownLatch) {
            this.mPingTarget = wrappedIpAddress;
            this.mCountDownLatch = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    this.mPingTarget.startTimer();
                    boolean zPing = DnsHttpCloud.ping(this.mPingTarget.mIpAddress);
                    StringBuilder sb = new StringBuilder("Ping ");
                    sb.append(this.mPingTarget.mIpAddress);
                    sb.append(StringUtils.SPACE);
                    sb.append(zPing ? "success" : "fail");
                    a.a(sb.toString());
                    if (zPing) {
                        this.mPingTarget.stopTimer();
                    }
                } catch (Exception e) {
                    a.c(e.getMessage());
                }
            } finally {
                this.mCountDownLatch.countDown();
            }
        }
    }

    private class RaceTask implements Runnable {
        private final ExecutorService mExecutor;
        private final List mRacingIpList;

        public RaceTask(ExecutorService executorService, List list) {
            this.mExecutor = executorService;
            this.mRacingIpList = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    if (this.mRacingIpList.size() > 0) {
                        CountDownLatch countDownLatch = new CountDownLatch(this.mRacingIpList.size());
                        a.a("Start to race game..");
                        a.a("In total " + this.mRacingIpList.size() + " ip join the game");
                        for (int i = 0; i < this.mRacingIpList.size(); i++) {
                            this.mExecutor.execute(new PingTask((WrappedIpAddress) this.mRacingIpList.get(i), countDownLatch));
                        }
                        a.a("The race game is over - " + countDownLatch.await(5L, TimeUnit.SECONDS));
                        Collections.sort(DnsHttpCloud.this.mProcessingIpList);
                        DnsHttpCloud.this.mProcessedIpList.clear();
                        a.a("Rank as follows:");
                        for (int i2 = 0; i2 < DnsHttpCloud.this.mProcessingIpList.size(); i2++) {
                            a.a(((WrappedIpAddress) DnsHttpCloud.this.mProcessingIpList.get(i2)).getIpAddress().toString());
                            DnsHttpCloud.this.mProcessedIpList.add(((WrappedIpAddress) DnsHttpCloud.this.mProcessingIpList.get(i2)).getIpAddress());
                        }
                        DnsHttpCloud.this.mProcessingIpList.clear();
                    }
                } finally {
                    DnsHttpCloud.this.mIsProcessing = false;
                }
            } catch (InterruptedException | UnknownHostException e) {
                a.c(e.getMessage());
            }
        }
    }

    private static class WrappedIpAddress implements Comparable {
        private long mEndPingNanoTime;
        private final String mIpAddress;
        private long mStartPingNanoTime;

        public WrappedIpAddress(String str) {
            this.mIpAddress = str;
        }

        @Override // java.lang.Comparable
        public int compareTo(WrappedIpAddress wrappedIpAddress) {
            return Long.compare(wrappedIpAddress.duration(), duration());
        }

        public long duration() {
            return this.mEndPingNanoTime - this.mStartPingNanoTime;
        }

        public InetAddress getIpAddress() {
            return InetAddress.getByName(this.mIpAddress);
        }

        public void startTimer() {
            this.mStartPingNanoTime = System.nanoTime();
        }

        public void stopTimer() {
            this.mEndPingNanoTime = System.nanoTime();
        }
    }

    public DnsHttpCloud(int i) {
        this.mDnsSelectStrategy = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r5v8 */
    private static String execPing(String e, boolean z) throws Throwable {
        BufferedReader bufferedReader;
        String str = z ? "ping -s 8185 -c  " : "ping -c ";
        String string = "";
        ?? r2 = 0;
        r2 = 0;
        r2 = 0;
        r2 = 0;
        r2 = 0;
        r2 = 0;
        try {
            try {
                try {
                    e = Runtime.getRuntime().exec(str + "3 " + ((String) e));
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(e.getInputStream()));
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                }
            } catch (Exception e4) {
                e = e4;
                e = 0;
            } catch (Throwable th) {
                th = th;
                e = 0;
            }
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(line);
                    string = sb.toString();
                    r2 = sb;
                } catch (Exception e5) {
                    e = e5;
                    r2 = bufferedReader;
                    e.printStackTrace();
                    if (r2 != 0) {
                        r2.close();
                    }
                    if (e != 0) {
                        e.destroy();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r2 = bufferedReader;
                    if (r2 != 0) {
                        try {
                            r2.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            throw th;
                        }
                    }
                    if (e != 0) {
                        e.destroy();
                    }
                    throw th;
                }
                return string;
            }
            bufferedReader.close();
            e.waitFor();
            bufferedReader.close();
            if (e != 0) {
                e.destroy();
            }
            return string;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private InetAddress getRandomIpAddress() {
        if (this.mProcessedIpList.size() > 0) {
            ArrayList arrayList = new ArrayList(this.mProcessedIpList);
            return (InetAddress) arrayList.get(new Random().nextInt(arrayList.size()));
        }
        if (this.mProcessingIpList.size() <= 0) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList(this.mProcessingIpList);
        return ((WrappedIpAddress) arrayList2.get(new Random().nextInt(arrayList2.size()))).getIpAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean ping(String str) throws Throwable {
        StringBuilder sb = new StringBuilder(256);
        String strExecPing = execPing(str, false);
        if (Pattern.compile(MATCH_PING_IP).matcher(strExecPing).find()) {
            sb.append("\t");
            sb.append(strExecPing);
            String lowerCase = sb.toString().toLowerCase();
            if (!lowerCase.contains(Constant.API_PARAMS_KEY_TIMEOUT) && !lowerCase.contains("timed out") && !lowerCase.contains("unknown")) {
                return true;
            }
        }
        return false;
    }

    public void addHttpCloud(ExecutorService executorService, Set set) {
        int i;
        if (this.mIsProcessing) {
            return;
        }
        this.mIsProcessing = true;
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            while (true) {
                if (i >= this.mProcessedIpList.size()) {
                    arrayList.add(str);
                    break;
                } else {
                    if (str.equals(((InetAddress) this.mProcessedIpList.get(i)).getHostAddress())) {
                        a.a(str + " already exists");
                        break;
                    }
                    i++;
                }
            }
        }
        if (arrayList.size() <= 0) {
            this.mIsProcessing = false;
            return;
        }
        if (this.mDnsSelectStrategy == 0) {
            this.mProcessingIpList.clear();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.mProcessingIpList.add(new WrappedIpAddress((String) arrayList.get(i2)));
            }
            while (i < this.mProcessedIpList.size()) {
                this.mProcessingIpList.add(new WrappedIpAddress(((InetAddress) this.mProcessedIpList.get(i)).getHostAddress()));
                i++;
            }
            a.a("Current strategy is race mode");
            executorService.submit(new RaceTask(executorService, this.mProcessingIpList));
            return;
        }
        a.a("Current strategy is random mode");
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            try {
                a.a("Add " + ((String) arrayList.get(i3)) + " to processed list");
                this.mProcessedIpList.add(InetAddress.getByName((String) arrayList.get(i3)));
            } catch (UnknownHostException e) {
                a.c(e.getMessage());
            }
        }
        this.mIsProcessing = false;
    }

    public InetAddress getIpAddress() {
        try {
            if (this.mDnsSelectStrategy != 1 && this.mProcessedIpList.size() > 0) {
                return (InetAddress) this.mProcessedIpList.get(0);
            }
            return getRandomIpAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int processedSize() {
        return this.mProcessedIpList.size();
    }

    public int processingSize() {
        return this.mProcessingIpList.size();
    }
}
