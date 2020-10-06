# XRichTextEditor

本库 在'jp.wasabeef:richeditor-android:1.2.2'基础上进行了简单的样式更改

一、添加依赖

`root build.gradle `
```
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```
`module build.gradle `
```
implementation 'com.github.fonuhuolian:XRichTextEditor:1.2.9'
```

二、xml

```
<!--可输入-->
<org.fonuhuolian.xrichtexteditor.XRichTextEditor
  android:id="@+id/editor"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:x_rich_padding="10dp"/>
```
```
<!--不可输入-->
<org.fonuhuolian.xrichtexteditor.XRichWebView
  android:id="@+id/e"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  app:x_rich_web_padding="10dp"/>
```

三、代码

```
// ******可输入******
// 富文本导航栏点击图片按钮的事件
xEditor.setListener(new XRichPicIconClickListener() {
  @Override
  public void clickPicIcon() {
    // 根据实际情况选择图片的逻辑操作
    // 成功后调用方法插入图片
    xEditor.insertImage(url, "pic\" style=\"max-width:98%");
  }
);
// 设置富文本内容
xEditor.setContent();
// 富文本内容是否加载完成
xEditor.isLoadComplete();
// 获取富文本的html文本
xEditor.getHtmlText();
// 转换成Email样式的html文本
XRichTextEditor.covertEmailHtml(String quotations, String contentHtml);
// 超文本转换为普通文字
XRichTextEditor.convertHTMLToText(String htmlStr);
//　跳转页面一直要使用startActivityForResult（）方式，并在回调处调用同名方法
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
  xEditor.onActivityResult();
}
```

```
// ******不可输入******

// 加载富文本内容
xRichWebview.setContent();
// 富文本内容是否加载完成
xRichWebview.isLoadComplete();
// 获取富文本的html文本
xRichWebview.getHtmlText();
// 获取WebView
xRichWebview.getWebView();
// 转换成Email样式的html文本
XRichWebView.covertEmailHtml(String quotations, String contentHtml);
// 超文本转换为普通文字
XRichWebView.convertHTMLToText(String htmlStr);
```

四、感谢 `wasabeef`提供富文本库



