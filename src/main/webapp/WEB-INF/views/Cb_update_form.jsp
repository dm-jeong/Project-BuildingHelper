<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>게시글 수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>
<body>
    <%@ include file="./module/header.jsp"%>
    <div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">글 수정하기</h1>
        </div>
    </div>
    <div class="container">
        <form:form action="edit" method="post" modelAttribute="post" enctype="multipart/form-data" class="form-horizontal">
		    <fieldset>
		        <legend class="mb-4">게시글 수정</legend>
		        <form:hidden path="cb_id"/>
		        <div class="form-group row mb-1">
		            <label class="col-sm-2 control-label">제목</label>
		            <div class="col-sm-4">
		                <input name="cb_title" class="form-control" value="${post.cb_title}"/>
		            </div>
		        </div>
		        <div class="form-group row mb-1">
		            <label class="col-sm-2 control-label">내용</label>
		            <div class="col-sm-4">
		                <textarea name="cb_description" class="form-control">${post.cb_description}</textarea>
		            </div>
		        </div>
		        <div class="form-group row mb-3">
		            <label class="col-sm-2 control-label">이미지</label>
		            <div class="col-sm-4">
		                <input type="file" id="file" name="file"/> 
		            </div>
		        </div>
		        <div class="form-group row">
		            <div class="d-flex gap-2">
		                <input type="submit" class="btn btn-success mb-4" value="수정"/>
		                <a href="<c:url value='/clubs/clubboard/view?id=${post.cb_id}' />" class="btn btn-secondary mb-4">돌아가기</a>
		            </div>
		        </div>
		    </fieldset>
		</form:form>
    </div>
    <%@ include file="./module/footer.jsp"%>
</body>
</html>
