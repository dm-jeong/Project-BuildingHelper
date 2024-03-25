<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

<title>관리비 조회</title>
</head>


<body>
	<%@ include file="module/header.jsp"%>

	<div class="container">
		<div class="row" align="center">
	        <h1> ${expense_check.expense_year}년 ${expense_check.expense_month}월분</h1>
	        
			<h3> (${ad.house_name }) : ${ck.house_dong } ${ck.house_hosu }</h3>
							
			<h3> (${address.house_name }) : ${house.house_dong } ${house.house_hosu }</h3>

			<p>	${expense_check.expense_price }원
			<p>	${expense_check.expense_state }
			<p>	${expense_check.expense_paid}
			
			<h1>세부내역</h1>

			
			<p> 전기세 : ${expense_check.e_charge }
			<p> 수도세 : ${expense_check.water_charge }
			<p> 공용관리비 : ${expense_check.p_charge }
					
		</div>
	</div>
		<br>
		<%@ include file="module/footer.jsp"%>
</body>
</html>