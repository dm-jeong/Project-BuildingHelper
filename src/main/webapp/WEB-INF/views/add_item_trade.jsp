<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>판매 게시글 등록</title>
</head>
<style>
	.box-shadow-light-gray {
		box-shadow : 4px 8px 16px #d7d7d7;
	}
</style>
<body>
	<%@ include file="./module/header.jsp"%>

	<br>
	<br>
	<div class="container ">
		<div class="col-12 col-xxl-6 mx-auto box-shadow-light-gray" style="padding: 3rem;">
	
			<div class="jumbotron">
					<h1 class="display-5 text-center" style="margin:30px;">개인 거래 판매글 등록</h1>
			</div>
			<div class="row">
				<form:form modelAttribute="item_trade"
					action="add?${_csrf.parameterName}=${_csrf.token}"
					class="form-horizontal" enctype="multipart/form-data">
					<fieldset>
						<div class="form-floating mb-3">
							<form:input path="title" class="form-control" id="floatingInput" placeholder="원룸"/>
							<label for="floatingInput" class="col-sm-2 control-label mb-3">제목</label>
						</div>
						<div class="form-floating mb-3">
							<form:input path="category" class="form-control" id="floatingInput" placeholder="원룸"/>
							<label for="floatingInput" class="col-sm-2 control-label mb-3">분류</label>
						</div>
						<div class="form-floating mb-3">
							<form:input path="price" class="form-control" id="floatingInput" placeholder="원룸" />
							<label for="floatingInput" class="col-sm-2 control-label mb-3">가격 </label>
						</div>
						<div class="form-group mb-3">
							<label class="col-sm-2 control-label mb-3">설명 </label>
								<form:textarea path="description" cols="50" rows="10"
									class="form-control" />
						</div>
						<div class="form-group mb-3">
							<label class="col-sm-2 control-label mb-3">사진</label>					
							<input type="file" id="img_name" name="img_name" class="form-control" accept="image/*" multiple="multiple">
							<!-- 사진 미리보기 -->				
						   <div id="preview"></div>
						</div>
			
						<div class="form-group mb-3">
							<div class="col-sm-offset-2 col-6">
								<input type="submit" class="btn btn-primary" value="등록" />
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
	<hr>

	<%@ include file="module/footer.jsp"%>
	
    <script src="<c:url value="/resources/js/controllers.js"/>"></script>

</body>
</html>