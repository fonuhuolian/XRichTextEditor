package org.fonuhuolian.xrichtexteditor;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class XRoundImage extends RelativeLayout {

    private int bgColor = 0xffff0000;

    public XRoundImage(Context context) {
        this(context, null);
    }

    public XRoundImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRoundImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XRoundImage);
        bgColor = ta.getColor(R.styleable.XRoundImage_roundColor, bgColor);
        ta.recycle();

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.x_round_layout, this, true);

        this.findViewById(R.id.x_round_img).setBackgroundColor(bgColor);
    }


    public int getBgColor() {
        return bgColor;
    }
}

