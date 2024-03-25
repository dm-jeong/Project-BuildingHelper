<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/resources/js/delete_register_link.js"/>"></script>

<title>관리소 회원 관리 페이지</title>

</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="container">
		<form:form modelAttribute="Register_manager_link" method="post" class="text-center form-floating col-md-6 col-lg-4 mx-auto border p-3 box-shadow-light-gray rounded my-4">
			<h2>${r_house_name} <br>가입 링크 생성기</h2>
			<div class="form-floating  mb-4">
				<form:input path="register_house_name" class="form-control focus-ring focus-ring-danger border rounded-2"
					id="floatingInput" placeholder="house_name" value="${r_house_name}" readonly="true" />
				<label for="floatingInput">공동 주택 이름</label>
			</div>
			<div class="form-floating mb-4">
				<form:input path="register_house_dong" class="form-control" id="register_house_dong" required="true"/>
				<label for="floatingInput">동</label>
			</div>
			<div class="form-floating mb-4">
				<form:input path="register_house_hosu" class="form-control" id="register_house_hosu" required="true"/>
				<label for="floatingInput">호</label>
			</div>
				<div class="form-floating mb-4">
					<button class="btn btn-primary col-12" type="submit" value="로그인"
						class="col-10">회원가입 링크 생성</button>
				</div>
		</form:form>
		
		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-8">
				<h2 class="text-center">최근 생성한 가입 링크 5개</h2>
				<div class="table-responsive">
					<table class="table">
					    <thead class="table-responsive ">
					      <tr>
					        <th scope="col">동</th>
					        <th scope="col">호</th>
					        <th scope="col" class="d-none d-md-table-cell">가입 링크</th>
					        <th scope="col" class="d-md-none text-center">가입 링크</th>
					        <th scope="col" class="d-none d-md-table-cell">링크 삭제</th>
					        <th scope="col" class="d-md-none text-center">링크 삭제</th>
					      </tr>
					    </thead>
					    <c:forEach items="${list_of_register_url_recent_5_links}" var="register_recent_link">
						    <tbody>
							<tr>
								<td>
									<p>${register_recent_link.register_house_dong}동</p>
								</td>
								<td>
									<p>${register_recent_link.register_house_hosu}호</p>
								</td>
								<td class="d-none d-md-table-cell">
									<p class="mb-0">
										<a href="<c:url value="${register_recent_link.register_url_link}"/>">${register_recent_link.register_url_link}</a>
									</p>
								</td>
								<td class="d-md-none text-center">
									<p class="mb-0">
										<a href="<c:url value="${register_link.register_url_link}"/>">가입 링크</a>
									</p>
								</td>								
								<td class="d-none d-md-table-cell">
									<button type="button" class="btn btn-danger" onclick="del_register_link('${register_link.getRegister_link_id()}')">링크 삭제</button>
								</td>
								<td class="d-md-none text-center">
									<button type="button" class="btn btn-danger" onclick="del_register_link('${register_link.getRegister_link_id()}')">링크 삭제</button>
								</td>								
							</tr>
						    </tbody>
						</c:forEach>
					</table>		
				</div>
			</div>
		</div>
		
		<hr>
		<div class="d-flex justify-content-center mt-2">
			<div class="col-12 col-md-8">
				
				<div class="table-responsive">
					<h2 class="text-center">모든 가입 링크</h2>
					<table class="table">
					    <thead class="table-responsive ">
					      <tr>
					        <th scope="col">동</th>
					        <th scope="col">호</th>
					        <th scope="col" class="d-none d-md-table-cell">가입 링크</th>
					        <th scope="col" class="d-md-none text-center">가입 링크</th>
					        <th scope="col" class="d-none d-md-table-cell">링크 삭제</th>
					        <th scope="col" class="d-md-none text-center">링크 삭제</th>
					      </tr>
					    </thead>
					    <c:forEach items="${list_of_register_url}" var="register_link">
						    <tbody>
							<tr>
								<td>
									<p>${register_link.register_house_dong}동</p>
								</td>
								<td>
									<p>${register_link.register_house_hosu}호</p>
								</td>
								<td class="d-none d-md-table-cell">
									<p class="mb-0">
										<a href="<c:url value="${register_link.register_url_link}"/>">${register_link.register_url_link}</a>
									</p>
								</td>
								<td class="d-md-none text-center">
									<p class="mb-0">
										<a href="<c:url value="${register_link.register_url_link}"/>">가입 링크</a>
									</p>
								</td>								
								<td class="d-none d-md-table-cell">
									<button type="button" class="btn btn-danger" onclick="del_register_link('${register_link.getRegister_link_id()}')">링크 삭제</button>
								</td>
								<td class="d-md-none text-center">
									<button type="button" class="btn btn-danger" onclick="del_register_link('${register_link.getRegister_link_id()}')">링크 삭제</button>
								</td>															
							</tr>
						    </tbody>
						</c:forEach>
					</table>		
				</div>
			</div>
		</div>
			
	</div>

	<%@ include file="./module/footer.jsp"%>
</body>
</html>