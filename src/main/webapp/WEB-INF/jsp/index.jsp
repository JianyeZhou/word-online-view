<%--
  Created by IntelliJ IDEA.
  User: Eden
  Date: 2018/1/8
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
    <title>Office文件在线预览Demo - Marscore</title>
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
                <li class="active"><a href="<%=ctxPath%>/">Home</a></li>
                <li><a href="<%=ctxPath%>/upload">Upload</a></li>
                <li><a href="<%=ctxPath%>/view">View</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>Office在线预览</h1>
        <p class="lead">您可以下载或预览已上传的文件，也可以点击上传按钮上传新的文件。<br>文件格式支持doc，docx，ppt，pptx等主流office格式...</p>
    </div>

    <a href="<%=ctxPath%>/upload"><div class="text-right uploadButton"><button type="button" class="btn btn-primary">上传Office文件</button></div></a>

    <table class="table table-hover">
        <thead><tr><th>ID</th><th>文件名</th><th>上传日期</th><th>操作</th></tr></thead>
        <tbody>
        <c:forEach items="${wordFileList}" var="wordFile">
            <tr>
                <th>${wordFile.id}</th><th>${wordFile.name}</th><th>${wordFile.gmtCreated}</th>
                <th>
                    <a title="在线查看" href="<%=ctxPath%>/view?id=${wordFile.id}"><span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span></a>&nbsp;&nbsp;
                    <a title="下载原件" href="${wordFile.url}"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>&nbsp;&nbsp;
                    <a title="删除文件" href="<%=ctxPath%>/delete?id=${wordFile.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>&nbsp;&nbsp;
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${empty wordFileList}"><p class="text-center">暂无历史文件</p></c:if>
</div><!-- /.container -->
</body>
</html>