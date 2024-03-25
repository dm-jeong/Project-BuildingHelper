<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>${item_trade_by_id.title}</title>
<style>
	.custom-img {
	    max-width: 100%; /* 최대 너비 설정 */
	    max-height: 500px; /* 최대 높이 설정 */
	    object-fit: cover; /* 이미지를 컨테이너에 맞게 잘라서 보여줌 */
	}
    @media screen and (max-width: 768px) {
       .col-md-12 {
           padding: 15px;
       }
    }
</style>
</head>
<body>
	<%@ include file="module/header.jsp"%>

	<div class="jumbotron">
		<div class="container">
			<a href="<c:url value="/itemtrade" />" class="text-decoration-none" style="color:black;">
				<h1 class="display-5 text-center" style="margin:40px;">아파트 입주민 거래 게시판</h1>
			</a>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-6 mx-auto">
				<img src="<c:url value="/resources/images/${item_trade_by_id.file_name}"/>" class="img-thumbnail custom-img col-12" onclick="showOriginalImage('<c:url value="/resources/images/${item_trade_by_id.file_name}"/>')" style="cursor: pointer;">
				
				<script>
				    function showOriginalImage(imageUrl) {
				        var image = new Image();
				        image.src = imageUrl;
				        image.onload = function() {
				            var width = this.width;
				            var height = this.height;
				            var left = (screen.width - width) / 2;
				            var top = (screen.height - height) / 2;
				            var newWindow = window.open('', '', 'width=' + width + ', height=' + height + ', left=' + left + ', top=' + top + ', resizable=yes, scrollbars=yes');
				            
				            // 새로 열린 창에 이미지 로딩
				            newWindow.document.body.style.backgroundColor = "white";
				            newWindow.document.body.style.cursor = "pointer";
				            newWindow.document.body.style.margin = '0';
				            newWindow.document.body.innerHTML = "<img src='" + imageUrl + "' style='width:100%; height:100%; margin:0;' onclick='window.close();'>";
				        };
				    }
				</script>




				<hr>
				<div class="mb-3">
					<div class="col-md-12">
						<h3 class="mb-3"><strong>${item_trade_by_id.title }</strong></h3>
						<p>${item_trade_by_id.category } | ${time_diff }</p>
						<p>${item_trade_by_id.price}원</p><br>
						<h3><strong>소개</strong></h3>			
						<div style="max-width: 100%;">
							<pre style="font-family: SUIT-Regular, sans-serif; font-size: 16px; white-space: pre-wrap;">
							<p>${item_trade_by_id.description }</p>
							</pre>
						</div>
						
						
						
						
						<br>
						<p style="font-size:13px;">조회수 ${item_trade_by_id.board_count }</p>
						<br>
					</div>
				</div>
				
				
				<div class="form-group row">
   					 <div class="col-sm-offset-2 col-sm-10">
						<c:if test="${is_owner }">
							<a
								href="<c:url value="/itemtrade/update?id=${item_trade_by_id.item_trade_id}" />"
								class="btn btn-primary" role="button">수정</a>
	
							<form
								action="<c:url value='/itemtrade/delete/${item_trade_by_id.item_trade_id}'/>"
								method="post" style="display: inline-block; margin-left:5px;">
								<input type="hidden" name="_method" value="delete"> <input
									type="submit" value="삭제" class="btn btn-primary"
									onclick="return confirmDelete()">
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="<c:url value="/resources/js/controllers.js"/>"></script>
	<%@ include file="module/footer.jsp"%>
</body>
</html>
