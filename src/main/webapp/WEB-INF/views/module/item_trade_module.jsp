<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

   



    


<div class="container">
	<div class="jumbotron mb-5 mt-5">
        <div class="row">
            <div class="col-md-9">
                <h1>아파트 입주민 거래 게시판</h1>
            </div>
            <div class="col-md-3 text-right">
				<a href="<c:url value='/itemtrade'/>" style="text-decoration: none; color: #FFA500; float:right; margin:20px;">더보기</a>
            </div>
        </div>
    </div>
	<div class="row row-cols-3 row-cols-xl-6 g-4">
		<c:forEach items="${item_trade}" var="item">
			<a href="itemtrade/trade?id=${item.item_trade_id }" class="text-decoration-none">
				<div class="card h-100">
               	 	<img class="card-img-top object-fit-cover" src="resources/images/${item.file_name}" style="height:200px;"  />
   	                <div class="card-body" style="height:10%;">
						<h5 class="card-title">${item.title}</h5>
			            <div class="d-flex justify-content-between align-items-center" style="font-size: 0.8rem;">
			                <p class="card-text mb-0">가격: ${item.price}원</p>
			                <p class="card-text">${item.reg_date_time_diff}</p>
			            </div>
					</div>
				</div>
			</a>
		</c:forEach>
	</div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {
    var totalItems = ${item_trade.size()};
    
    function shuffle(array) {
        for (var i = array.length - 1; i > 0; i--) {
            var j = Math.floor(Math.random() * (i + 1));
            var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
    
    var elements = document.querySelectorAll('.card.h-100');
    for (var i = 0; i < elements.length; i++) {
        var parentAnchor = elements[i].closest('a');
        if (parentAnchor) {
            parentAnchor.style.display = 'none';
        }
        elements[i].style.display = 'none';
    }
    
    var shuffledElements = shuffle(Array.from(elements));
    for (var i = 0; i < 12 && i < shuffledElements.length; i++) {
        var parentAnchor = shuffledElements[i].closest('a');
        if (parentAnchor) {
            parentAnchor.style.display = 'block';
        }
        shuffledElements[i].style.display = 'block';
    }
});
</script>
