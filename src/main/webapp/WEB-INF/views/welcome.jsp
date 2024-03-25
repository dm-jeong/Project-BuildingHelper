<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/js/bootstrap.min.js"></script>

<script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>

<title>프로젝트 관리사무소</title>
<script type="text/javascript" src="<c:url value="/resources/js/controllers.js"/>"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${api_key}&libraries=services"></script>
</head>
	
<body>
<%@ include file="./module/header.jsp"%>
<!-- 카카오지도(고정된 좌표에 마커 추가) -->

	<div class="container">

		<br>
		<p>${resident.r_nickname }님 환영합니다!
		<br> 
		<h5>${address.address}</h5>
		<h3>${address.house_name }</h3>
		<h5>${house.house_dong }동 ${house.house_hosu }호</h5>
		<br>
		<div class="jumbotron mb-5 mt-5">
			<h1 style="margin-bottom:20px;">우리 아파트 주변</h1>
		</div>
		<div id="map" style="height:400px;"></div>
		

	</div>
	
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		
		<!-- 중고거래 미리보기 -->
   		<%@ include file="module/item_trade_module.jsp"%>   


	
	<%@ include file="module/footer.jsp"%>
</body>
<!-- 주소 검색창 + 예시 -->
<script>
    var mapContainer = document.getElementById('map'); // 지도를 표시할 div
    var mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

    // 지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    // 주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    // 마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });

    // 페이지 로드 시 주소 정보로 지도를 표시하는 함수
    function showAddressOnMap() {
        var address = "${address.address}";

        // 주소로 상세 정보를 검색
        geocoder.addressSearch(address, function(results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {
                var result = results[0]; // 첫번째 결과의 값을 활용

                // 해당 주소에 대한 좌표를 받아서
                var coords = new daum.maps.LatLng(result.y, result.x);
                // 지도를 보여줍니다.
                mapContainer.style.display = "block";
                map.relayout();
                // 지도 중심을 변경합니다.
                map.setCenter(coords);
                // 마커를 결과값으로 받은 위치로 옮깁니다.
                marker.setPosition(coords);
            }
        });
    }

    // 페이지 로드 시 실행
    window.onload = function() {
        showAddressOnMap();
    };
</script>

</html>