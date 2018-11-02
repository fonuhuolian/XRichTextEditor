package org.fonuhuolian.xrichtexteditor;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class XIconText extends AppCompatTextView {

    private Context mContext;

    public XIconText(Context context) {
        this(context, null);
    }

    public XIconText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XIconText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "iconfont.ttf");
        XIconText.this.setTypeface(typeface);
    }

}
