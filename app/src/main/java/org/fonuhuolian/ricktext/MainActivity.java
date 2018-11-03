package org.fonuhuolian.ricktext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.fonuhuolian.xrichtexteditor.XRichTextEditor;

public class MainActivity extends AppCompatActivity {

    private XRichTextEditor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editor = (XRichTextEditor) findViewById(R.id.e);
    }
}
