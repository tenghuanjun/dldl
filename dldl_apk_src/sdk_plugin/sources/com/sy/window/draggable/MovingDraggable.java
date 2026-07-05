package com.sy.window.draggable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MovingDraggable extends BaseDraggable {
    private boolean mMoveTouch;
    private float mViewDownX;
    private float mViewDownY;

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000e, code lost:
    
        if (r6 != 3) goto L23;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            int r6 = r7.getAction()
            r0 = 0
            if (r6 == 0) goto L61
            r1 = 1
            if (r6 == r1) goto L57
            r2 = 2
            if (r6 == r2) goto L11
            r7 = 3
            if (r6 == r7) goto L57
            goto L6f
        L11:
            float r6 = r7.getRawX()
            int r2 = r5.getWindowInvisibleWidth()
            float r2 = (float) r2
            float r6 = r6 - r2
            float r2 = r7.getRawY()
            int r3 = r5.getWindowInvisibleHeight()
            float r3 = (float) r3
            float r2 = r2 - r3
            float r3 = r5.mViewDownX
            float r6 = r6 - r3
            r3 = 0
            float r6 = java.lang.Math.max(r6, r3)
            float r4 = r5.mViewDownY
            float r2 = r2 - r4
            float r2 = java.lang.Math.max(r2, r3)
            r5.updateLocation(r6, r2)
            boolean r6 = r5.mMoveTouch
            if (r6 == 0) goto L3f
            r5.dispatchExecuteDraggingCallback()
            goto L6f
        L3f:
            float r6 = r5.mViewDownX
            float r2 = r7.getX()
            float r3 = r5.mViewDownY
            float r7 = r7.getY()
            boolean r6 = r5.isFingerMove(r6, r2, r3, r7)
            if (r6 == 0) goto L6f
            r5.mMoveTouch = r1
            r5.dispatchStartDraggingCallback()
            goto L6f
        L57:
            boolean r6 = r5.mMoveTouch
            if (r6 == 0) goto L5e
            r5.dispatchStopDraggingCallback()
        L5e:
            boolean r6 = r5.mMoveTouch
            return r6
        L61:
            float r6 = r7.getX()
            r5.mViewDownX = r6
            float r6 = r7.getY()
            r5.mViewDownY = r6
            r5.mMoveTouch = r0
        L6f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy.window.draggable.MovingDraggable.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
