	<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<script>
    document.addEventListener("DOMContentLoaded", function () {
        var totalItems = ${estate_trade.size()}; // JSP에서 Java 코드를 사용하여 리스트 크기 가져오기

        // 배열 섞기 함수
        function shuffle(array) {
            for (var i = array.length - 1; i > 0; i--) {
                var j = Math.floor(Math.random() * (i + 1));
                var temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            return array;
        }

        // 모든 아이템을 숨기고, 랜덤으로 섞은 후 처음 6개만 보이도록 변경
        var elements = document.querySelectorAll('.col.module');
        for (var i = 0; i < elements.length; i++) {
            elements[i].style.display = 'none';
        }

        var shuffledElements = shuffle(Array.from(elements));
        for (var i = 0; i < 6 && i < shuffledElements.length; i++) {
            shuffledElements[i].style.display = 'block';
        }
    });
</script>
<div class="container">
	<div class="col-12 col-lg-6 mx-auto">		
		<div class="jumbotron">
		    <div class="container">
		        <div class="row">
		            <div class="col-9">
		                <h2 class="display-6" style="margin:10px">다른 부동산 직거래</h2>
		            </div>
		            <div class="col-3 text-right">
		                <a href="<c:url value='/estate' />" style="text-decoration: none; color: #FFA500; float:right; margin: 20px;">더보기</a>
		            </div>
		        </div>
		    </div>
		</div>
		<br><br>

		<div class="container">		
			<div class="row row-cols-1 g-4">
			    <c:forEach items="${estate_trade}" var="estate_trade">
			        <div class="col module">
		                <a href="trade?id=${estate_trade.estate_trade_id }" class="text-decoration-none text-dark">
			            	<div class="card h-100">
			            		<div class="row">
				                    <!-- 왼쪽에 이미지 -->
				                    <div class="col-5">
			                        	<img src="resources/images/${estate_trade.file_name}" class="card-img estate-trade-card-img object-fit-cover">				                    	
				                    </div>
				                    <!-- 오른쪽에 텍스트 -->
				                    <div class="col-7">
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
	</div>
</div>

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