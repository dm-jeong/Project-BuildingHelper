<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>동아리 게시판</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
</head>
<body>
	<%@ include file="./module/header.jsp"%>
	<div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">${club_by_id.c_title} 게시판</h1>
        </div>
    </div>
    <div class="container mb-4">
	    <div class="row">
	        <div class="col-md-4 custom-img">
	            <img src="<c:url value='/resources/images/${club_by_id.c_file_name}'/>" alt="club image" class="img-fluid" style="max-height: 250px;">
	        </div>
	        <div class="col-md-8 d-flex flex-column justify-content-center">
	        	<p><b>동아리 소개</b></p>
	            <p>${club_by_id.c_description}</p>
	        </div>
	    </div>
	</div>
	<hr>
	<div class="container">
        <a href="<c:url value='/clubs/clubboard/addpost?c_id=${c_id}'/>" class="btn btn-secondary mb-3" role="button">글 등록</a>
        <table class="table">
            <thead>
                <tr>
                	<th>글 번호</th>
                    <th style="width: 50%;">제목</th>
                    <th>작성자</th>
                    <th>작성 시간</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="post" items="${posts}" varStatus="status">
                    <tr>
                    	<td>${pageMaker.total_count - ((pageMaker.cri.page - 1) * pageMaker.cri.per_num) - status.index}</td>
                        <td><a href="<c:url value='clubboard/view?id=${post.cb_id}'/>">${post.cb_title}</a></td>
                        <td>${post.cb_writer_id}</td>
                        <td>${post.cb_time}</td>
                        <td>${post.cb_count}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div>
		    <ul class="pagination justify-content-center">
		        <c:if test="${pageMaker.prev}"> <!-- 이전 페이지 그룹이 있을 때만 '이전' 버튼을 출력 -->
				    <li class="page-item">
				        <a class="page-link" href="/buildingHelper/clubs/clubboard?c_id=${c_id}&page=${pageMaker.start_page - 1}">이전</a>
				    </li>
				</c:if>
		
		        <c:forEach begin="${pageMaker.start_page}" end="${pageMaker.end_page}" var="pageNum">
				    <li class="page-item">
				        <a class="page-link" href="/buildingHelper/clubs/clubboard?c_id=${c_id}&page=${pageNum}">${pageNum}</a>
				    </li>
				</c:forEach>
		
		        <c:if test="${pageMaker.next}"> <!-- 다음 페이지 그룹이 있을 때만 '다음' 버튼을 출력 -->
				    <li class="page-item">
				        <a class="page-link" href="/buildingHelper/clubs/clubboard?c_id=${c_id}&page=${pageMaker.end_page + 1}">다음</a>
				    </li>
				</c:if>
		    </ul>
		</div>
    </div>
	<%@ include file="./module/footer.jsp"%>
</body>
</html>