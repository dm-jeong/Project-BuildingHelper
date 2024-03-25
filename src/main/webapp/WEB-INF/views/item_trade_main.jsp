<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>거래게시판</title>

<style>

	



</style>

<body>
<%@ include file="module/header.jsp" %>

<div class="container">
	<div class="row">
        <div class="col-md-5 d-flex align-items-center">
            <div style="width: 100%;">
                <p>중고거래</p>
                <h1>믿을만한<br>이웃 간 중고거래</h1>
                <p>우리 아파트 주민들과 가깝고 따뜻한 거래를<br>지금 경험해보세요.</p>
                <a class="btn btn-secondary" href="itemtrade/add">거래 등록</a>
            </div>
        </div>
        <div class="col-md-7 text-end">
            <img src="resources/images/project-1.jpg" alt="우리동네 중고 직거래 사진" class="img-fluid">
        </div>
    </div>

	<div class="jumbotron mt-5 mb-5">
		<h1>아파트 입주민 거래 게시판</h1>
	</div>
	
	<div class="row row-cols-3 row-cols-xl-6 g-4">
		<c:forEach items="${item_trade}" var="item">
			<a href="itemtrade/trade?id=${item.item_trade_id }" class="text-decoration-none">
				<div class="card h-100">
               	 	<img class="card-img-top object-fit-cover" src="resources/images/${item.file_name}" style="height:200px;"  />
   	                <div class="card-body" style="height:10%;">
						<h5 class="card-title">${item.title}</h5>
			            <div class="d-flex justify-content-between align-items-center" style="font-size: 0.8rem;">
			                <p class="card-text mb-0">가격: ${item.price}원</p>
			                <p class="card-text">${item.reg_date_time_diff}</p>
			            </div>
					</div>	
				</div>
			</a>
		</c:forEach>
	</div>
</div>
	<%@ include file="module/footer.jsp" %>
</body>
</html>