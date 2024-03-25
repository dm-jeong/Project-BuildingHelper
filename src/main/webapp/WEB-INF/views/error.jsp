<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>에러 페이지</title>
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="container">
		<h1>에러가 발생했습니다.</h1>
		<p>죄송합니다. 요청하신 페이지를 찾을 수 없습니다.</p>
		<p>방문하시려는 페이지의 주소가 잘못 입력되었거나, 페이지의 주소가
			변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.</p> 
		<p>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</p>
		<p>관련 문의사항은 고객센터에 연락주시면 친절하게 안내해 드리겠습니다. 감사합니다.</p>
	</div>
	<%@ include file="./module/footer.jsp"%>
</body>
</html>