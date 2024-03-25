<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>글 보기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	            <img src="<c:url value='/resources/images/${club_by_id.c_file_name}'/>" alt="club image" class="img-fluid" style="max-height: 500px;">
	        </div>
	        <div class="col-md-8 d-flex flex-column justify-content-center">
	        	<p><b>동아리 소개</b></p>
	            <p>${club_by_id.c_description}</p>
	        </div>
	    </div>
	</div>
	<hr>
    <div class="container">
        <h1>${post.cb_title}</h1>
        <div class="d-flex gap-4 mb-4">
        	<p><b>작성자:</b> ${post.cb_writer_id}</p>
        	<p><b>작성 시간:</b> ${post.cb_time}</p>
	        <p><b>조회수:</b> ${post.cb_count}</p>
        </div>
        <div class="col-md-10 mb-4">
		    <div class="row">
		        <div class="custom-img mb-4">
		            <c:if test="${not empty post.cb_file_name}">
		                <img src="<c:url value='/resources/images/${post.cb_file_name}'/>" alt="attached image" class="img-fluid" style="max-height: 500px;"/>
		            </c:if>
		        </div>
		        <p>${post.cb_description}</p>
		    </div>
		    <div class="d-flex justify-content-end gap-2">
		        <c:if test="${sessionScope.resident.r_login_id eq post.cb_writer_id}">
					<a href="<c:url value='/clubs/clubboard/edit?cb_id=${post.cb_id}'/>" class="btn btn-success" role="button">수정</a>
				</c:if>
				<c:if test="${sessionScope.resident.r_category eq '관리소' or sessionScope.resident.r_login_id eq post.cb_writer_id}">
					<a href="javascript:delete_confirm('${post.cb_id}')" class="btn btn-danger" role="button">삭제</a>
				</c:if>
			</div>
		</div>
		<div class="col-md-10">
		    <div class="mt-4">
		    	<p><b>댓글</b></p>
		        <c:forEach items="${reply_list}" var="reply">
		            <div class="card mb-1" style="min-height: 50px">
					    <div class="d-flex align-items-center card-body gap-3" style="display: flex;">
					        <h5 style="flex: 1;">${reply.cr_writer_id}</h5>
					        <p style="flex: 4;">${reply.cr_description}</p>
					        <p class="ms-auto text-muted" style="flex: 1.2;">${reply.cr_time}</p>
					        <c:if test="${sessionScope.resident.r_category eq '관리소' or sessionScope.resident.r_login_id eq reply.cr_writer_id}">
					        	<a href="/buildingHelper/clubs/clubboard/replydelete?cr_id=${reply.cr_id}" class="btn btn-secondary" role="button"><i class="bi bi-x-lg"></i></a>
					        </c:if>
					    </div>
					</div>
		        </c:forEach>
		    </div>
		    <div class="mt-4">
		        <form action="reply" method="post">
		            <div class="mb-3">
		                <textarea name="description" class="form-control" id="description" rows="3"></textarea>
		            </div>
		            <div class="d-flex justify-content-end">
		            	<button type="submit" class="btn btn-primary mb-4">댓글 달기</button>
		            </div>
		        </form>
		    </div>
		</div>
    </div>
    <hr>
    <div class="container">
        <a href="<c:url value='/clubs/clubboard/addpost?c_id=${c_id}'/>" class="btn btn-primary mt-4 mb-2" role="button">글 등록</a>
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
                        <td><a href="<c:url value='view?id=${post.cb_id}'/>">${post.cb_title}</a></td>
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
				        <a class="page-link" href="/buildingHelper/clubs/clubboard/view?id=${post.cb_id}&page=${pageMaker.start_page - 1}">이전</a>
				    </li>
				</c:if>
		
		        <c:forEach begin="${pageMaker.start_page}" end="${pageMaker.end_page}" var="pageNum">
				    <li class="page-item">
				        <a class="page-link" href="/buildingHelper/clubs/clubboard/view?id=${post.cb_id}&page=${pageNum}">${pageNum}</a>
				    </li>
				</c:forEach>
		
		        <c:if test="${pageMaker.next}"> <!-- 다음 페이지 그룹이 있을 때만 '다음' 버튼을 출력 -->
				    <li class="page-item">
				        <a class="page-link" href="/buildingHelper/clubs/clubboard/view?id=${post.cb_id}&page=${pageMaker.end_page + 1}">다음</a>
				    </li>
				</c:if>
		    </ul>
		</div>
    </div>
    <%@ include file="./module/footer.jsp"%>
</body>
<script>
	function delete_confirm(cb_id)
	{
		if(confirm("삭제하시겠습니까?") == true) location.href="/buildingHelper/clubs/clubboard/delete?cb_id=" + cb_id;
		else return;
	}
</script>
</html>
