package org.fonuhuolian.xrichtexteditor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import jp.wasabeef.richeditor.RichEditor;

public class XRichTextEditor extends FrameLayout implements View.OnClickListener {

    private Context mContext;

    private RichEditor mEditor;
    private LinearLayout mLayout;

    private FrameLayout xBack;
    private FrameLayout xGo;
    private FrameLayout xBold;
    private FrameLayout xUnderLine;
    private FrameLayout xItalic;
    private FrameLayout xSize;


    private XIconText iconText;
    private PopupWindow mPopupWindow;
    private PopupWindow mPopupWindow2;
    private View cancle;
    private int screenHeight;
    private int color;
    private String htmlText;


    public XRichTextEditor(Context context) {
        this(context, null);
    }

    public XRichTextEditor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRichTextEditor(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        // 获取屏幕像素高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenHeight = dm.heightPixels;

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.x_rich_text_editor, this, true);
        mLayout = ((LinearLayout) this.findViewById(R.id.x_layout));

        // 编辑器
        mEditor = ((RichEditor) this.findViewById(R.id.x_editor));
        mEditor.setEditorFontSize(14);
        color = Color.parseColor("#999999");
        mEditor.setEditorFontColor(color);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("开始输入...");


        // 初始化popwindow
        initPopWindow();
        // 初始化popwindow2
        initPopWindow2();
        // 初始化回退以及前进
        initBackAndGo();
        // 初始化字体样式1
        initFontStyle();
        // 初始化字体样式2
        initFontStyle2();


        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {

                if (TextUtils.isEmpty(text)) {
                    iconText.setTextColor(color);
                    mEditor.setTextColor(color);
                }

                htmlText = text;
            }
        });
    }


    private void initPopWindow() {

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.x_pop_size_layout, null, false);
        mPopupWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        contentView.findViewById(R.id.x_h1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(1);
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.x_h2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(2);
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.x_h3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(3);
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.x_h4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(4);
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.x_h5).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(5);
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.x_h6).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(6);
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });

        contentView.findViewById(R.id.x_cancle1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });

        contentView.findViewById(R.id.x_cancle2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });

        contentView.findViewById(R.id.x_cancle3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });

        cancle = contentView.findViewById(R.id.x_cancle4);
        cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null)
                    mPopupWindow.dismiss();
            }
        });
    }


    private void initPopWindow2() {

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.x_font_color_select_layout, null, false);
        mPopupWindow2 = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        mPopupWindow2.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        contentView.findViewById(R.id.x_round1).setOnClickListener(this);
        contentView.findViewById(R.id.x_round2).setOnClickListener(this);
        contentView.findViewById(R.id.x_round3).setOnClickListener(this);
        contentView.findViewById(R.id.x_round4).setOnClickListener(this);
        contentView.findViewById(R.id.x_round5).setOnClickListener(this);
        contentView.findViewById(R.id.x_round6).setOnClickListener(this);
        contentView.findViewById(R.id.x_round7).setOnClickListener(this);
        contentView.findViewById(R.id.x_round8).setOnClickListener(this);
        contentView.findViewById(R.id.x_round9).setOnClickListener(this);
        contentView.findViewById(R.id.x_round10).setOnClickListener(this);
        contentView.findViewById(R.id.x_round11).setOnClickListener(this);
        contentView.findViewById(R.id.x_round12).setOnClickListener(this);
        contentView.findViewById(R.id.x_round13).setOnClickListener(this);
        contentView.findViewById(R.id.x_round14).setOnClickListener(this);


        contentView.findViewById(R.id.x_cancle1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow2 != null)
                    mPopupWindow2.dismiss();
            }
        });

        cancle = contentView.findViewById(R.id.x_cancle4);
        cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow2 != null)
                    mPopupWindow2.dismiss();
            }
        });
    }


    private void initBackAndGo() {

        // 撤销
        xBack = ((FrameLayout) this.findViewById(R.id.x_back));
        xBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });


        // 恢复
        xGo = ((FrameLayout) this.findViewById(R.id.x_go));
        xGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.redo();
            }
        });
    }


    private void initFontStyle() {
        // 加粗
        xBold = ((FrameLayout) this.findViewById(R.id.x_bold));
        xBold.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });
        // 下滑线
        xUnderLine = ((FrameLayout) this.findViewById(R.id.x_underLine));
        xUnderLine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();

            }
        });
        // 斜体
        xItalic = ((FrameLayout) this.findViewById(R.id.x_italic));
        xItalic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });
    }

    private void initFontStyle2() {

        // 字体大小
        xSize = ((FrameLayout) this.findViewById(R.id.x_size));
        xSize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                mLayout.getLocationOnScreen(location);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) cancle.getLayoutParams();

                if (screenHeight - location[1] == dp2px(mContext, 44)) {
                    params.height = dp2px(mContext, 48);
                } else {
                    params.height = screenHeight - location[1] + dp2px(mContext, 4);
                }

                mPopupWindow.showAtLocation(mLayout, Gravity.NO_GRAVITY, 0, 0);
            }
        });


        // 字体颜色
        iconText = ((XIconText) this.findViewById(R.id.x_color));
        iconText.setTextColor(color);
        iconText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int[] location = new int[2];
                mLayout.getLocationOnScreen(location);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) cancle.getLayoutParams();

                if (screenHeight - location[1] == dp2px(mContext, 44)) {
                    params.height = dp2px(mContext, 48);
                } else {
                    params.height = screenHeight - location[1] + dp2px(mContext, 4);
                }

                mPopupWindow2.showAtLocation(mLayout, Gravity.NO_GRAVITY, 0, 0);
            }
        });
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {

        color = ((XRoundImage) v).getBgColor();


        if (mPopupWindow2 != null)
            mPopupWindow2.dismiss();

        mEditor.setTextColor(color);
        iconText.setTextColor(color);
    }


    public void insertImage(String url, String alt) {
        mEditor.insertImage(url, alt);
    }
}