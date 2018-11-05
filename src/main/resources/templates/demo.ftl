<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>开始使用layui</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>

<form class="layui-form" action="${base}/aaa" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-block">
            <input type="text" name="name" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">密码格式：十六位大小写英文字母和数字</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
            <select name="city" lay-verify="required">
                <option value=""></option>
                <option value="1">北京</option>
                <option value="2">上海</option>
                <option value="3">广州</option>
                <option value="4">深圳</option>
                <option value="5">杭州</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">复选框</label>
        <div class="layui-input-block">
            <input type="checkbox" name="like" value="1" title="写作" checked>
            <input type="checkbox" name="like" value="2" title="阅读">
            <input type="checkbox" name="like" value="3" title="发呆">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开关</label>
        <div class="layui-input-block">
            <input type="checkbox" name="status" lay-skin="switch" lay-text="开启|关闭" value="1">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男" checked>
            <input type="radio" name="sex" value="2" title="女">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">文本域</label>
        <div class="layui-input-block">
            <div id="editor">
            </div>
            <textarea id="editorContent" name="content" style="display:none" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="layui/layui.all.js"></script>
<script src="layui/css/layui.css"></script>
<script type="text/javascript" src="wangEditor.js"></script>
<script type="text/javascript">
    var $ = layui.jquery;
    var E = window.wangEditor;
    var editor = new E('#editor');
    var $editorContent = $('#editorContent');
    editor.customConfig.uploadImgServer = '${base}/upload/editor';
    editor.customConfig.uploadFileName = 'files';
    editor.customConfig.onchange = function (html) {
        $editorContent.val(html)
    };
    editor.create();
    $editorContent.val(editor.txt.html());
</script>
</body>
</html>
