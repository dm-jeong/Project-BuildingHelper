<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>물품 수정</title>
</head>
<body>
	<%@ include file="./module/header.jsp" %>
	<div class="jumbotron">
		<div class="container py-5">
			<h1 class="text-center display-6">물품 수정</h1>
		</div>
	</div>
	<div class="container">
				<form:form modelAttribute="update_s_item" action="update" method="post" class="form-horizontal" enctype="multipart/form-data">
					<fieldset>
						<form:hidden path="share_item_id"/>
						<legend class="mb-4">물품 정보 수정</legend>
						<div class="form-group row mb-1">
							<label class="col-sm-2 control-label">물품 이름</label>
							<div class="col-sm-4">
								<form:input path="name" class="form-control" value="${share_item.name}"/>
							</div>
						</div>
						<div class="form-group row mb-1">
							<label class="col-sm-2 control-label">분류</label>
							<div class="col-sm-4">
								<form:input path="s_category" class="form-control" value="${share_item.s_category}"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-2 control-label">설명</label>
							<div class="col-sm-4">
								<form:textarea path="s_description" class="form-control mb-3" value="${share_item.s_description}"/>
							</div>
						</div>
						<div class="form-group row mb-3">
							<label class="col-sm-2 control-label">물품 이미지</label>
							<div class="col-sm-4">
								<input type="file" id="file" name="file">
							</div>
						</div>
						<div class="form-group row">
							<div class="d-flex gap-2">
								<input type="submit" class="btn btn-success mb-4" value="수정"/>
								<a href="<c:url value="/shareitems"/>"  class="btn btn-secondary mb-4">돌아가기</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		<hr>
	<%@ include file="./module/footer.jsp" %>
</body>
</html>