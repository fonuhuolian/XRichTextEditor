package org.fonuhuolian.ricktext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import org.fonuhuolian.xrichtexteditor.XRichTextEditor;

public class MainActivity extends AppCompatActivity {

    private XRichTextEditor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editor = (XRichTextEditor) findViewById(R.id.e);
    }

    @Override
    public void onBackPressed() {

        if (editor.isShowPop()) {
            editor.closePop();
        } else
            super.onBackPressed();
    }

    // 监听返回键(有软键盘的情况下想直接返回，需要拦截KeyEvent.ACTION_UP事件)
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (editor.isShowPop()) {
                editor.closePop();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
