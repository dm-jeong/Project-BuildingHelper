<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

<!-- 지도api -->
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${api_key}&libraries=services"></script>

<title>부동산 판매 게시글 등록</title>
</head>
<style>
	.box-shadow-light-gray {
		box-shadow : 4px 8px 16px #d7d7d7;
	}
</style>
<body>
<%@ include file="./module/header.jsp"%>
<br>
<br>
<div class="container">
	<div class="col-12 col-xxl-6 mx-auto box-shadow-light-gray" style="padding: 3rem;">
		<div class="jumbotron">
				<h1 class="display-5 text-center" style="margin: 30px;">부동산 판매
					게시글 등록</h1>
		</div>
		<div class="row">
				<form:form modelAttribute="estate_trade"
					action="add?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data">
					<fieldset>
						<div class="form-floating mb-3">
							<form:input path="building_type" class="form-control" id="floatingInput" placeholder="원룸"/>
							<label for="floatingInput">건물 종류</label>
						</div>
						<div class="form-floating mb-3">
							<form:input path="title" class="form-control" id="floatingInput" placeholder="원룸"/>
							<label for="floatingInput">제목</label>
						</div>

						<div class="form-group mb-3">
							<label  class="col-sm-2 control-label mb-3">입주가능일</label>
							<form:input path="move_in" class="form-control" type="date" />
						</div>
						
						<div class="form-group mb-3">
							<label class="col-sm-2 control-label mb-3">설명 </label>
							<form:textarea path="description" rows="10" class="form-control" />
						</div>


						<div class="form-group mb-3">
							<label class="col-6 control-label mb-3">주소</label> 
							<input type="text" name="address_api" class="form-control mb-3" id="sample5_address"> 
							<input type="button" class="btn btn-primary" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
							<!-- 지도api -->
							<div id="map" style="width: 300px; height: 300px; margin-top: 10px; display: none"></div>
						</div>


						<div class="form-group mb-3">
							<label class="col-sm-2 control-label mb-3">사진</label> 
							<input type="file" id="img_name" name="img_name" class="form-control"
								accept="image/*" multiple="multiple">
							<!-- 사진 미리보기 -->
							<div id="preview"></div>
						</div>

						<div class="form-group mb-3">
							<div class="col-sm-offset-2 col-6">
								<input type="submit" class="btn btn-primary" value="등록" />
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
	<hr>


	<%@ include file="module/footer.jsp"%>

</body>


<!-- 설명 양식 -->
<script>
	document
			.addEventListener(
					"DOMContentLoaded",
					function() {
						var descriptionTextarea = document
								.getElementById("description");
						descriptionTextarea.value = "※ 면적, 방/욕실 수, 층, 주차, 엘리베이터, 내부시설 등 을 입력해 주세요 ※"
					});
</script>

<!-- 주소 검색창 + 예시 -->
<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
		level : 5
	// 지도의 확대 레벨
	};

	//지도를 미리 생성
	var map = new daum.maps.Map(mapContainer, mapOption);
	//주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();
	//마커를 미리 생성
	var marker = new daum.maps.Marker({
		position : new daum.maps.LatLng(37.537187, 127.005476),
		map : map
	});

	function sample5_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = data.address; // 최종 주소 변수

				// 주소 정보를 해당 필드에 넣는다.
				document.getElementById("sample5_address").value = addr;
				// 주소로 상세 정보를 검색
				geocoder.addressSearch(data.address, function(results, status) {
					// 정상적으로 검색이 완료됐으면
					if (status === daum.maps.services.Status.OK) {

						var result = results[0]; //첫번째 결과의 값을 활용

						// 해당 주소에 대한 좌표를 받아서
						var coords = new daum.maps.LatLng(result.y, result.x);
						// 지도를 보여준다.
						mapContainer.style.display = "block";
						map.relayout();
						// 지도 중심을 변경한다.
						map.setCenter(coords);
						// 마커를 결과값으로 받은 위치로 옮긴다.
						marker.setPosition(coords)
					}
				});
			}
		}).open();
	}
</script>

<script src="<c:url value="/resources/js/controllers.js"/>"></script>
</html>