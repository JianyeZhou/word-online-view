<%--
  Created by IntelliJ IDEA.
  User: Eden
  Date: 2018/1/8
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<title>上传文件 - Office文件在线预览Demo - Marscore</title>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Office文件在线预览Demo</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<%=ctxPath%>/">Home</a></li>
                <li class="active"><a href="<%=ctxPath%>/upload">Upload</a></li>
                <li><a href="<%=ctxPath%>/view">View</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>上传文件</h1>
        <p class="lead">支持主流的office格式文件。</p>
    </div>

    <form action="<%=ctxPath%>/upload" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <input type="file" name="file" required="required">
            <p class="help-block">格式应为docx/doc/xlsx/xls/pptx/ppt</p>
        </div>
        <div class="form-group">
            <label for="name">重命名文件</label>
            <input type="text" class="form-control" id="name" name="name">
            <p class="help-block">默认文件名为上传的文件名</p>
        </div>
        <button type="submit" class="btn btn-default">上传</button>
    </form>

</div><!-- /.container -->
<script src="<%=ctxPath%>/js/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $("form").submit(function () {
            layer.load(1);
        });
    });
</script>

</body>
</html>
