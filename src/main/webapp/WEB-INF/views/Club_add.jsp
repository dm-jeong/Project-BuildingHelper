<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>동아리 등록</title>
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>
<body>
    <%@ include file="./module/header.jsp"%>
    <div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">새 동아리 등록</h1>
        </div>
    </div>
    <div class="container">
        <form:form action="./add" method="post" modelAttribute="club" enctype="multipart/form-data" class="form-horizontal">
	        <fieldset>
				<legend class="mb-4">동아리 정보 입력</legend>
	            <div class="form-group row mb-1">
					<label class="col-sm-2 control-label">동아리 이름</label>
					<div class="col-sm-4">
						<form:input path="c_title" class="form-control"/>
					</div>
				</div>
	            <div class="form-group row mb-1">
					<label class="col-sm-2 control-label">동아리 소개글</label>
					<div class="col-sm-4">
						<form:textarea path="c_description" class="form-control"/>
					</div>
				</div>
	            <div class="form-group row mb-3">
					<label class="col-sm-2 control-label">동아리 이미지</label>
					<div class="col-sm-4">
						<input type="file" id="c_file" name="c_file"/> 
					</div>
				</div>
	            <div class="form-group row">
					<div class="d-flex gap-2">
						<input type="submit" class="btn btn-primary mb-4" value="등록"/>
						<a href="<c:url value="/clubs"/>" class="btn btn-secondary mb-4">돌아가기</a>
					</div>
				</div>
	        </fieldset>
        </form:form>
    </div>
    <%@ include file="./module/footer.jsp"%>
</body>
</html>
