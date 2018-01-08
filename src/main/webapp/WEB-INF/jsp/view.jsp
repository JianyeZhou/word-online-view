<%--
  Created by IntelliJ IDEA.
  User: Eden
  Date: 2018/1/8
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<title>预览文件 - Office文件在线预览Demo - Marscore</title>
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
                <li class="active"><a href="<%=ctxPath%>/view">View</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <div class="starter-template">
        <h1>预览文件</h1>
        <c:if test="${empty wordFile}">
            <p class="lead">未选中需预览的文件</p>
            <div class="text-center">
                <a href="javascript:history.go(-1);"><button type="button" class="btn btn-warning">返回上一步</button></a>&nbsp;&nbsp;
                <a href="<%=ctxPath%>/"><button type="button" class="btn btn-info">转到主页</button></a>
            </div>
        </c:if>
        <c:if test="${not empty wordFile}">
        <p class="lead">${wordFile.name} - ${wordFile.gmtCreated}</p>
    </div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#office" aria-controls="office" role="tab" data-toggle="tab">Office官方预览</a></li>
        <li role="presentation"><a href="#pdf" aria-controls="pdf" role="tab" data-toggle="tab">本地PDF预览</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="office">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="http://view.officeapps.live.com/op/view.aspx?src=${wordFile.url}"></iframe>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="pdf">
            <div class="embed-responsive embed-responsive-16by9">
                <iframe class="embed-responsive-item" src="plugin/viewer.html?file=${wordFile.pdfUrl}"></iframe>
            </div>
        </div>
    </div>
    </c:if>

</div><!-- /.container -->

</body>
</html>
