package org.fonuhuolian.xrichtexteditor;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class SoftKeyBroadManager implements ViewTreeObserver.OnGlobalLayoutListener {

    private boolean isFirst = true;//只用获取一次

    public interface SoftKeyboardStateListener {
        void onSoftKeyboardOpened(int keyboardHeightInPx);

        void onSoftKeyboardClosed();
    }

    private final List<SoftKeyboardStateListener> listeners = new LinkedList<SoftKeyboardStateListener>();
    private final View activityRootView;
    private int lastSoftKeyboardHeightInPx;
    private boolean isSoftKeyboardOpened;
    private int statusBarHeight = 0;
    private int navigationBarHeight = 0;
    private Context activity;

    public SoftKeyBroadManager(Context activity, View activityRootView) {
        this(activity, activityRootView, false);
    }

    public SoftKeyBroadManager(Context activity, View activityRootView, boolean isSoftKeyboardOpened) {
        this.activityRootView = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        this.activity = activity;
    }

    @Override
    public void onGlobalLayout() {

        final Rect r = new Rect();
        activityRootView.getWindowVisibleDisplayFrame(r);

        int height = activityRootView.getRootView().getHeight();


        // 不可见区域
        final int heightDiff = height - (r.bottom - r.top);
        // 可见区域
        int heightVisible = height - heightDiff;

        if (isFirst) {

            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = activity.getResources().getDimensionPixelSize(x);

                Field field2 = c.getField("navigation_bar_height");
                int x2 = Integer.parseInt(field2.get(obj).toString());
                navigationBarHeight = activity.getResources().getDimensionPixelSize(x2);

                if (heightDiff < 500) {

                    if (r.top != 0) {
                        // 无沉浸式
                        if (height - r.top > heightVisible) {
                            // 有底部导航啦
                            // 不做操作
                        } else {
                            // 无底部导航啦
                            navigationBarHeight = 0;
                        }
                    } else {
                        // 沉浸式
                        if (height - r.top > heightVisible) {
                            // 有底部导航啦
                            statusBarHeight = 0;
                        } else {
                            // 无底部导航啦
                            navigationBarHeight = 0;
                            statusBarHeight = 0;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            isFirst = false;
        }

        if (!isSoftKeyboardOpened && heightDiff > 500) {
            // 如果高度超过500 键盘可能被打开
            isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened(heightDiff - statusBarHeight - navigationBarHeight);
        } else if (isSoftKeyboardOpened && heightDiff < 500) {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    public void setIsSoftKeyboardOpened(boolean isSoftKeyboardOpened) {
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
    }

    public boolean isSoftKeyboardOpened() {
        return isSoftKeyboardOpened;
    }


    public int getLastSoftKeyboardHeightInPx() {
        return lastSoftKeyboardHeightInPx;
    }

    public void addSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.add(listener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.remove(listener);
    }

    private void notifyOnSoftKeyboardOpened(int keyboardHeightInPx) {
        this.lastSoftKeyboardHeightInPx = keyboardHeightInPx;

        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardOpened(keyboardHeightInPx);
            }
        }
    }

    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardClosed();
            }
        }
    }

}
