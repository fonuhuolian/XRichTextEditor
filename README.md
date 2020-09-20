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
implementation 'com.github.fonuhuolian:XRichTextEditor:1.2.3'
```

二、xml

```
<org.fonuhuolian.xrichtexteditor.XRichTextEditor
  android:id="@+id/editor"
  android:layout_width="match_parent"
  android:layout_height="match_parent" />
```

三、代码

```
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
```
四、感谢 `wasabeef`提供富文本库



