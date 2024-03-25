<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>글 등록하기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>
<body>
    <%@ include file="./module/header.jsp"%>
    <div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">글 등록하기</h1>
        </div>
    </div>
    <div class="container">
        <form action="<c:url value='/clubs/clubboard/addpost'/>" method="post" enctype="multipart/form-data" class="form-horizontal">
        	<fieldset>
				<legend class="mb-4">게시글 입력</legend>
	            <div class="form-group row mb-1">
					<label class="col-sm-2 control-label">제목</label>
					<div class="col-sm-4">
						<input name="title" class="form-control"/>
					</div>
				</div>
	            <div class="form-group row mb-1">
					<label class="col-sm-2 control-label">내용</label>
					<div class="col-sm-4">
						<textarea name="description" class="form-control"></textarea>
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
						<input type="submit" class="btn btn-primary mb-4" value="등록"/>
						<a href="<c:url value='/clubs/clubboard?c_id=${c_id}' />" class="btn btn-secondary mb-4">돌아가기</a>
					</div>
				</div>
		    </fieldset>
        </form>
    </div>
    <%@ include file="./module/footer.jsp"%>
</body>
</html>
