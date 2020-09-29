package org.fonuhuolian.xrichtexteditor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XRichWebView extends FrameLayout {

    private RichWebView mEditor;

    private boolean isLoadComplete = false;

    private String htmlText = "";


    public XRichWebView(Context context) {
        this(context, null);
    }

    public XRichWebView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRichWebView(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        // 获取屏幕像素高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        float density = dm.density;       // 屏幕密度（像素比例：0.75/1.0/1.5/2.0,3.0,4等）

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.x_rich_web_view, this, true);
        mEditor = ((RichWebView) this.findViewById(R.id.rootLayout));

        mEditor.setFontSize(3);
        mEditor.setEditorFontColor(Color.parseColor("#999999"));

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XRichWebView);
        float padding = ta.getDimension(R.styleable.XRichWebView_x_rich_web_padding, 0);
        float paddingLeft = ta.getDimension(R.styleable.XRichWebView_x_rich_web_paddingLeft, 0);
        float paddingRight = ta.getDimension(R.styleable.XRichWebView_x_rich_web_paddingRight, 0);
        float paddingTop = ta.getDimension(R.styleable.XRichWebView_x_rich_web_paddingTop, 0);
        float paddingBottom = ta.getDimension(R.styleable.XRichWebView_x_rich_web_paddingBottom, 0);

        if (ta.getText(R.styleable.XRichWebView_x_rich_web_padding) != null && ta.getText(R.styleable.XRichWebView_x_rich_web_padding).toString().endsWith("dip")) {
            padding = padding / density;
        }

        if (ta.getText(R.styleable.XRichWebView_x_rich_web_paddingLeft) != null && ta.getText(R.styleable.XRichWebView_x_rich_web_paddingLeft).toString().endsWith("dip")) {
            paddingLeft = paddingLeft / density;
        }

        if (ta.getText(R.styleable.XRichWebView_x_rich_web_paddingRight) != null && ta.getText(R.styleable.XRichWebView_x_rich_web_paddingRight).toString().endsWith("dip")) {
            paddingRight = paddingRight / density;
        }

        if (ta.getText(R.styleable.XRichWebView_x_rich_web_paddingTop) != null && ta.getText(R.styleable.XRichWebView_x_rich_web_paddingTop).toString().endsWith("dip")) {
            paddingTop = paddingTop / density;
        }

        if (ta.getText(R.styleable.XRichWebView_x_rich_web_paddingBottom) != null && ta.getText(R.styleable.XRichWebView_x_rich_web_paddingBottom).toString().endsWith("dip")) {
            paddingBottom = paddingBottom / density;
        }


        ta.recycle();

        if (padding != 0) {
            paddingLeft = padding;
            paddingRight = padding;
            paddingTop = padding;
            paddingBottom = padding;
        }

        mEditor.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);

        mEditor.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress != 100) {
                    isLoadComplete = false;
                } else {
                    isLoadComplete = true;
                }
            }
        });
    }


    /**
     * TODO 转换Email回复格式
     *
     * @param quotations  引用的String eg. 发件人：1334@qq.com\n收件人:xxx@qq.com\n日期:...
     * @param contentHtml 具体邮件的内容html
     */
    public static String covertEmailHtml(String quotations, String contentHtml) {
        quotations = quotations.replaceAll("\\\\n", "<br>");
        quotations = quotations.replaceAll("\n", "<br>");
        quotations = quotations.replaceAll("\\\\r", "<br>");
        quotations = quotations.replaceAll("\r", "<br>");
        quotations = "<br><br><br><br><font color=\"#282828\" size=\"2\"><b>--原始邮件--<br><br></b></font><span style=\"background-color: #F1F1F1;width:calc(100% - 24px);display:-moz-inline-box;display:inline-block;border-radius:5px;padding:10px 5px 15px 20px;line-height:23px;font-size:13px;\">" + quotations;
        quotations = quotations + "</span><br><br><br><br>";
        quotations = quotations + contentHtml;

        return quotations;
    }

    // TODO 转换超文本转换为普通文字
    public static String convertHTMLToText(String htmlStr) {

        //先将换行符保留，然后过滤标签
        Pattern p_enter = Pattern.compile("<br/>", Pattern.CASE_INSENSITIVE);
        Matcher m_enter = p_enter.matcher(htmlStr);
        htmlStr = m_enter.replaceAll("\n");

        //过滤html标签
        Pattern p_html = Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        return m_html.replaceAll("");
    }


    public String getHtmlText() {
        return htmlText;
    }

    public boolean isLoadComplete() {
        return isLoadComplete;
    }


    public void setContent(String htmlText) {
        mEditor.setHtml(htmlText);
        this.htmlText = htmlText;
    }
}
