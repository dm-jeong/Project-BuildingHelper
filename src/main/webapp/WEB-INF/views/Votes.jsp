<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>투표 게시판</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">투표 게시판</h1>
        </div>
    </div>
    <div class="container py-2 mb-3">
	    <div class="row align-items-center">
	    	<div class="col-auto">
	    		<a class="nav-link" href="<c:url value="/votes/add"/>"><button type="button" class="btn btn-secondary">등록하기</button></a>
	    	</div>
	    </div>
	</div>
	<div class="container">
        <h2>아파트 투표</h2>
        <table class="table">
            <thead>
                <tr>
                	<th>투표 번호</th>
                    <th style="width: 40%;">제목</th>
                    <th>등록 시간</th>
                    <th>종료 시간</th>
                    <th>작성자</th>
                </tr>
            </thead>
            <tbody>
			    <c:forEach var="vote" items="${votes1}" varStatus="status">
					<c:choose>
						<c:when test="${sessionScope.house.address_id eq vote.v_address_id}">
						<!-- 로그인 세션과 address_id가 같고 category가 관리소인 경우 -->
						    <tr>
						        <td>${pageMaker1.total_count - ((pageMaker1.cri.page - 1) * pageMaker1.cri.per_num) - status.index}</td>
						        <td><a href="#" onclick="checkVoteEnd('${vote.v_end_time}', '${vote.v_id}')">${vote.v_title}</a></td>
						        <td>${vote.v_start_time}</td>
						        <td>${vote.v_end_time}</td>
						        <td>${vote.v_creator_id}(${vote.v_category})</td>
						    </tr>
						</c:when>
					</c:choose>
			    </c:forEach>
			</tbody>
        </table>
        <div class="mb-4">
		    <ul class="pagination justify-content-center">
		        <c:if test="${pageMaker1.prev}"> <!-- 이전 페이지 그룹이 있을 때만 '이전' 버튼을 출력 -->
		            <li class="page-item">
		                <a class="page-link" href="/buildingHelper/votes?page1=${pageMaker1.start_page - 1}&page2=${pageMaker2.cri.page}">이전</a>
		            </li>
		        </c:if>
		
		        <c:forEach begin="${pageMaker1.start_page}" end="${pageMaker1.end_page}" var="pageNum">
		            <li class="page-item">
		                <a class="page-link" href="/buildingHelper/votes?page1=${pageNum}&page2=${pageMaker2.cri.page}">${pageNum}</a>
		            </li>
		        </c:forEach>
		
		        <c:if test="${pageMaker1.next}"> <!-- 다음 페이지 그룹이 있을 때만 '다음' 버튼을 출력 -->
		            <li class="page-item">
		                <a class="page-link" href="/buildingHelper/votes?page1=${pageMaker1.end_page + 1}&page2=${pageMaker2.cri.page}">다음</a>
		            </li>
		        </c:if>
		    </ul>
		</div>
		<hr>
        <h2 class="mt-4">우리동 투표</h2>
        <table class="table">
            <thead>
                <tr>
                	<th>투표 번호</th>
                    <th style="width: 40%;">제목</th>
                    <th>등록 시간</th>
                    <th>종료 시간</th>
                    <th>동</th>
                    <th>작성자</th>
                </tr>
            </thead>
            <tbody>
			    <c:forEach var="vote" items="${votes2}" varStatus="status">
					<c:choose>
						<c:when test="${sessionScope.house.address_id eq vote.v_address_id and sessionScope.house.house_dong eq vote.v_house_dong}">
						<!-- 로그인 세션과 address_id가 같고 house_dong이 같으면서 category가 세대원이나 동대표인 경우 -->
						    <tr>
						        <td>${pageMaker2.total_count - ((pageMaker2.cri.page - 1) * pageMaker2.cri.per_num) - status.index}</td>
						        <td><a href="#" onclick="checkVoteEnd('${vote.v_end_time}', '${vote.v_id}')">${vote.v_title}</a></td>
						        <td>${vote.v_start_time}</td>
						        <td>${vote.v_end_time}</td>
						        <td>${vote.v_house_dong}</td>
						        <td>${vote.v_creator_id}(${vote.v_category})</td>
						    </tr>
						</c:when>
					</c:choose>
			    </c:forEach>
			</tbody>
        </table>
        <div>
		    <ul class="pagination justify-content-center">
		        <c:if test="${pageMaker2.prev}"> <!-- 이전 페이지 그룹이 있을 때만 '이전' 버튼을 출력 -->
		            <li class="page-item">
		                <a class="page-link" href="/buildingHelper/votes?page1=${pageMaker1.cri.page}&page2=${pageMaker2.start_page - 1}">이전</a>
		            </li>
		        </c:if>
		
		        <c:forEach begin="${pageMaker2.start_page}" end="${pageMaker2.end_page}" var="pageNum">
		            <li class="page-item">
		                <a class="page-link" href="/buildingHelper/votes?page1=${pageMaker1.cri.page}&page2=${pageNum}">${pageNum}</a>
		            </li>
		        </c:forEach>
		
		        <c:if test="${pageMaker2.next}"> <!-- 다음 페이지 그룹이 있을 때만 '다음' 버튼을 출력 -->
		            <li class="page-item">
		                <a class="page-link" href="/buildingHelper/votes?page1=${pageMaker1.cri.page}&page2=${pageMaker2.end_page + 1}">다음</a>
		            </li>
		        </c:if>
		    </ul>
		</div>
	</div>
	<%@ include file="./module/footer.jsp"%>
</body>
<script>
	function checkVoteEnd(endTime, v_id) <%-- 투표가 종료되었는지 확인하는 자바스크립트 --%>
	{
	    var currentTime = new Date().getTime();
	    var voteEndTime = new Date(endTime).getTime();
	    
	    if (currentTime > voteEndTime) {
	        alert('투표가 종료되었습니다.');
	    } else {
	        location.href="/buildingHelper/votes/" + v_id;
	    }
	}
</script>
</html>
