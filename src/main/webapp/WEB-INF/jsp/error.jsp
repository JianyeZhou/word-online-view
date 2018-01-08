<%--
  Created by IntelliJ IDEA.
  User: Eden
  Date: 2018/1/8
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
    <title>404 - Office文件在线预览Demo - Marscore</title>
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
                <li><a href="<%=ctxPath%>/upload">Upload</a></li>
                <li><a href="<%=ctxPath%>/view">View</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>出错了</h1>
        <p class="lead">${message}</p>
    </div>
<div class="text-center">
    <a href="javascript:history.go(-1);"><button type="button" class="btn btn-warning">返回上一步</button></a>&nbsp;&nbsp;
    <a href="<%=ctxPath%>/"><button type="button" class="btn btn-info">转到主页</button></a>
</div>
</div><!-- /.container -->
</body>
</html>