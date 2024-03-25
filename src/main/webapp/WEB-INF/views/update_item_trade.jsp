<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>판매 게시글 등록</title>
</head>
<body>
<%@ include file="module/header.jsp" %>
		
<div class="container">
	<div class="jumbotron mb-5 mt-5">
			<h1 class="display-5 text-center">개인 거래 판매글 수정</h1>
	</div>	
	<div class="row">
		<div class="col-12 col-lg-6 mx-auto">
			<form:form modelAttribute="update" action = "update?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" enctype="multipart/form-data">
				<fieldset>
					<div class="form-group mb-3">
						<input type="hidden" name="item_trade_id" value="${item_trade.item_trade_id}" />
				        <c:if test="${not empty item_trade.file_name}">
				            <img src="<c:url value='/resources/images/${item_trade.file_name}' />" alt="개인거래게시판사진" class="img-thumbnail" />
				        </c:if>
					</div>
					
					<div class="form-floating mb-3">
						<form:input path="title" class="form-control" value="${item_trade.title}" id="floatingInput" placeholder="원룸" />
						<label for="floatingInput">제목</label>
					</div>
					<div class="form-floating mb-3">
						<form:input path="category" class="form-control" value="${item_trade.category}" id="floatingInput" placeholder="원룸" />
						<label for="floatingInput">분류</label>
					</div>
					<div class="form-floating mb-3">
						<form:input path="price" class="form-control" value="${item_trade.price}" id="floatingInput" placeholder="원룸"/>
						<label for="floatingInput">가격</label>
					</div>
					<div class="form-group mb-3">
						<label class="col-sm-2 control-label mb-3">설명</label>
						<textarea name="description" cols="50" rows="8" class="form-control">${item_trade.description}</textarea>
					</div>
					
					<div class="form-group mb-4">
						<label class="col-sm-2 control-label mb-3">사진</label>
						<input type="file" id="img_name" name="img_name" class="form-control" accept="image/*" multiple="multiple">
						<!-- 사진 미리보기 -->	
				   		<div id="preview"></div>
					</div>
					
					<div class="form-group mb-3">
						<div class="col-sm-offset-2 col-sm-10">
					    	<input type="submit" class="btn btn-primary" value="수정"/>
					    	<a href="<c:url value="/itemtrade"/>" class="btn btn-primary" style="margin-left:5px;">취소</a>
						</div>
					</div>
				</fieldset>
			</form:form>
			<hr>
		</div>
	</div>
</div>	
	<%@ include file="module/footer.jsp" %>
	
</body>
    <script src="<c:url value="/resources/js/controllers.js"/>"></script>

</html>