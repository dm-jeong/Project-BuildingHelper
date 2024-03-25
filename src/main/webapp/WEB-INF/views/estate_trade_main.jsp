<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>

<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>부동산 거래 게시판</title>
</head>
	
<body>
	<%@ include file="module/header.jsp"%>
	
    <div class="container">
        <div class="row justify-content-center align-items-center"> 
            <div class="col-lg-5">
                <div class=""> 
                    <h1>소파처럼 편안한</h1>
                    <h1>부동산 직거래</h1>
                    <p class="mb-4">나와 이웃이 살던 집, 편하게 직거래해보세요.</p>
                </div>
                <p><a href="estate/add" class="btn btn-secondary">매물 올리기</a></p>
            </div>
            <div class="col-lg-7">
                <div class="hero-img-wrap">
                    <img src="resources/images/couch.jpg" class="img-fluid">
                </div>
            </div>
        </div>
    </div>

	<div class="jumbotron mt-5 mb-5">
		<div class="container">
			<h1>부동산 직거래 게시글</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row row-cols-1 row-cols-lg-2 g-4">
		    <c:forEach items="${estate_trade}" var="estate_trade">
		        <div class="col">
	                <a href="estate/trade?id=${estate_trade.estate_trade_id }" class="text-decoration-none text-dark">
		            	<div class="card h-100">
		            		<div class="row">
			                    <!-- 왼쪽에 이미지 -->
			                    <div class="col-4">
		                        	<img src="resources/images/${estate_trade.file_name}" class="card-img estate-trade-card-img object-fit-cover">				                    	
			                    </div>
			                    <!-- 오른쪽에 텍스트 -->
			                    <div class="col-8">
			                        <div class="card-body ">
			                            <h5 class="card-title">${estate_trade.title} - ${estate_trade.modified_address}</h5>
			                            <p class="card-text mb-1">${estate_trade.building_type}</p>
			                            <span class="card-text badge rounded-pill bg-secondary statusBadge text-center mb-2">${estate_trade.sales_status}</span>
			                            <p class="card-text">${estate_trade.reg_date_time_diff}</p>
			                        </div>
			                    </div>
		                	</div>
		            	</div>
	                </a>
		        </div>
		    </c:forEach>
		</div>
	</div>
	<%@ include file="module/footer.jsp"%>
</body>
<script type="text/javascript" src="<c:url value="/resources/js/controllers.js"/>"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var statusBadges = document.querySelectorAll('.statusBadge');

        statusBadges.forEach(function (statusBadge) {
            changeStatus(statusBadge);
        });

        function changeStatus(statusBadge) {
            var salesStatus = statusBadge.textContent.trim();

            switch (salesStatus) {
                case '판매중':
                    statusBadge.classList.remove("bg-secondary", "bg-primary");
                    statusBadge.classList.add("bg-success");
                    break;
                case '예약중':
                    statusBadge.classList.remove("bg-secondary", "bg-success");
                    statusBadge.classList.add("bg-primary");
                    break;
                case '판매완료':
                    statusBadge.classList.remove("bg-success", "bg-primary");
                    statusBadge.classList.add("bg-secondary");
                    break;
            }
        }
    });
</script>

</html>