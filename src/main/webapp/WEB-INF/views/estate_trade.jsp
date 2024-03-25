<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${api_key}&libraries=services"></script>

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
<title>${estate_trade_by_id.title}</title>



<body>
	<%@ include file="module/header.jsp"%>


	<div class="jumbotron">
		<div class="container">
			<a href="<c:url value="/estate" />" class="text-decoration-none" style="color:black;">
				<h1 class="display-5 text-center" style="margin: 30px" >부동산 거래 게시판</h1>
			</a>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-6 mx-auto">		
				<img src="<c:url value="/resources/images/${estate_trade_by_id.file_name}" />" class="img-thumbnail custom-img col-12" onclick="showOriginalImage('<c:url value="/resources/images/${estate_trade_by_id.file_name}"/>')" style="cursor: pointer;">
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
						<h3><strong>${estate_trade_by_id.sales_status} | ${estate_trade_by_id.title }</strong></h3><br>
						<table>
						    <tr>
						        <td style="padding: 10px 10px 10px 0px;">건물 종류</td>
						        <td style="padding: 10px;">${estate_trade_by_id.building_type}</td>
						    </tr>
						    <tr>
						        <td style="padding: 10px 10px 10px 0px;"></td>
						        <td style="padding: 10px;">${time_diff} 작성</td>
						    </tr>
						    <tr>
						        <td style="padding: 10px 10px 10px 0px;">입주 가능일</td>
						        <td style="padding: 10px;">${estate_trade_by_id.move_in} 이후</td>
						    </tr>
						</table>
						
						<br><br>
			
						<h3><strong>소개</strong></h3><br>
						<div style="max-width: 100%;">
							<pre style="font-family: SUIT-Regular, sans-serif; font-size: 16px; white-space: pre-wrap;"><p>${estate_trade_by_id.description }</p></pre>
						</div>
						
						<br><br><br>
						<h3>위치</h3><br>
					    <div id="map" style="height:300px;margin-top:10px;"></div>
						
	
	
						
						<br>
						<p style="font-size:13px;">조회수 : ${estate_trade_by_id.board_count}
					</div>
				</div>
			
				<div class="form-group row">
   					 <div class="col-sm-offset-2 col-sm-10">
						<c:if test="${is_owner }">
							<a
								href="<c:url value="/estate/update?id=${estate_trade_by_id.estate_trade_id}" />"
								class="btn btn-primary" role="button">수정</a>
							<form action="<c:url value='/estate/delete/${estate_trade_by_id.estate_trade_id}'/>" method="post" style="display: inline-block; margin-left:5px;">
								<input type="hidden" name="_method" value="delete"> 
								<input type="submit" value="삭제" class="btn btn-primary" onclick="return confirmDelete()" >
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>	
	</div>
	<br>

<script type="text/javascript" src="<c:url value="/resources/js/controllers.js"/>"></script>
<br><br><br><br><br>

<%@ include file="module/estate_trade_module.jsp"%>



	<%@ include file="module/footer.jsp"%>
</body>
	<!-- 지도표시 -->
	<script>
	    var mapContainer = document.getElementById('map'); // 지도를 표시할 div
	    var geocoder = new daum.maps.services.Geocoder();
	    var marker = new daum.maps.Marker();
	
	    // 주소를 좌표로 변환하여 지도에 표시
	    var address = "${estate_trade_by_id.address_api}";
	
	    geocoder.addressSearch(address, function (results, status) {
	        // 정상적으로 검색이 완료됐으면
	        if (status === daum.maps.services.Status.OK) {
	            var result = results[0]; // 첫번째 결과의 값을 활용
	
	            // 해당 주소에 대한 좌표를 받아서
	            var coords = new daum.maps.LatLng(result.y, result.x);
	
	            // 지도를 생성하고 보여준다.
	            var mapOption = {
	                center: coords,
	                level: 5
	            };
	            var map = new daum.maps.Map(mapContainer, mapOption);
	
	            // 마커를 생성하고 지도에 추가
	            marker.setPosition(coords);
	            marker.setMap(map);
	        } else {
	            console.error('Geocoder failed due to: ' + status);
	        }
	    });
	</script>
</html>