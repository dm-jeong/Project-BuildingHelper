<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>투표하기</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <%@ include file="./module/header.jsp"%>
    <div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">투표하기</h1>
        </div>
    </div>
    <div class="container">
        <h1>${vote.v_title}</h1>
        <div class="d-flex gap-4 mb-4">
        	<p><b>작성자:</b> ${vote.v_creator_id}(${vote.v_category})</p>
	        <p><b>등록 시간:</b> ${vote.v_start_time}</p>
	        <p><b>종료 시간:</b> ${vote.v_end_time}</p>
        </div>
        <c:choose>
		    <c:when test="${duplicate_vote}"> <!-- 중복투표인 경우 차트를 표시 -->
		    	<canvas id="voteChart" class="mb-4" style="max-width: 900px; max-height: 500px;"></canvas>
		    </c:when>
		    <c:otherwise> <!-- 중복투표가 아닌 경우 옵션과 투표 표시 -->
		        <h3>옵션</h3>
				<form action="<c:url value='/votes/${vote.v_id}'/>" method="post">
			        <c:if test="${vote.v_option1 != null}">
			        	<div class="form-check">
				            <input type="radio" id="option1" name="voteOption" value="1" class="form-check-input">
				            <label for="option1" class="form-check-label">(1) ${vote.v_option1}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option2 != null}">
			        	<div class="form-check">
				            <input type="radio" id="option2" name="voteOption" value="2" class="form-check-input">
				            <label for="option2" class="form-check-label">(2) ${vote.v_option2}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option3 != null}">
			        	<div class="form-check">
				            <input type="radio" id="option3" name="voteOption" value="3" class="form-check-input">
				            <label for="option3" class="form-check-label">(3) ${vote.v_option3}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option4 != null}">
				        <div class="form-check">
				            <input type="radio" id="option4" name="voteOption" value="4" class="form-check-input">
				            <label for="option4" class="form-check-label">(4) ${vote.v_option4}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option5 != null}">
				        <div class="form-check">
				            <input type="radio" id="option5" name="voteOption" value="5" class="form-check-input">
				            <label for="option5" class="form-check-label">(5) ${vote.v_option5}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option6 != null}">
				        <div class="form-check">
				            <input type="radio" id="option6" name="voteOption" value="6" class="form-check-input">
				            <label for="option6" class="form-check-label">(6) ${vote.v_option6}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option7 != null}">
				        <div class="form-check">
				            <input type="radio" id="option7" name="voteOption" value="7" class="form-check-input">
				            <label for="option7" class="form-check-label">(7) ${vote.v_option7}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option8 != null}">
				        <div class="form-check">
				            <input type="radio" id="option8" name="voteOption" value="8" class="form-check-input">
				            <label for="option8" class="form-check-label">(8) ${vote.v_option8}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option9 != null}">
				        <div class="form-check">
				            <input type="radio" id="option9" name="voteOption" value="9" class="form-check-input">
				            <label for="option9" class="form-check-label">(9) ${vote.v_option9}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option10 != null}">
				        <div class="form-check">
				            <input type="radio" id="option10" name="voteOption" value="10" class="form-check-input">
				            <label for="option10" class="form-check-label">(10) ${vote.v_option10}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option11 != null}">
				        <div class="form-check">
				            <input type="radio" id="option11" name="voteOption" value="11" class="form-check-input">
				            <label for="option11" class="form-check-label">(11) ${vote.v_option11}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option12 != null}">
				        <div class="form-check">
				            <input type="radio" id="option12" name="voteOption" value="12" class="form-check-input">
				            <label for="option12" class="form-check-label">(12) ${vote.v_option12}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option13 != null}">
				        <div class="form-check">
				            <input type="radio" id="option13" name="voteOption" value="13" class="form-check-input">
				            <label for="option13" class="form-check-label">(13) ${vote.v_option13}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option14 != null}">
				        <div class="form-check">
				            <input type="radio" id="option14" name="voteOption" value="14" class="form-check-input">
				            <label for="option14" class="form-check-label">(14) ${vote.v_option14}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option15 != null}">
				        <div class="form-check">
				            <input type="radio" id="option15" name="voteOption" value="15" class="form-check-input">
				            <label for="option15" class="form-check-label">(15) ${vote.v_option15}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option16 != null}">
				        <div class="form-check">
				            <input type="radio" id="option16" name="voteOption" value="16" class="form-check-input">
				            <label for="option16" class="form-check-label">(16) ${vote.v_option16}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option17 != null}">
				        <div class="form-check">
				            <input type="radio" id="option17" name="voteOption" value="17" class="form-check-input">
				            <label for="option17" class="form-check-label">(17) ${vote.v_option17}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option18 != null}">
				        <div class="form-check">
				            <input type="radio" id="option18" name="voteOption" value="18" class="form-check-input">
				            <label for="option18" class="form-check-label">(18) ${vote.v_option18}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option19 != null}">
				        <div class="form-check">
				            <input type="radio" id="option19" name="voteOption" value="19" class="form-check-input">
				            <label for="option19" class="form-check-label">(19) ${vote.v_option19}</label><br>
			            </div>
			        </c:if>
			        <c:if test="${vote.v_option20 != null}">
				        <div class="form-check">
				            <input type="radio" id="option20" name="voteOption" value="20" class="form-check-input">
				            <label for="option20" class="form-check-label">(20) ${vote.v_option20}</label><br>
			            </div>
			        </c:if>
				    <input type="submit" value="투표하기" class="btn btn-primary mt-3 mb-3" role="button" onclick="return checkOptionSelected()">
				</form>
		    </c:otherwise>
		</c:choose>
		<c:if test="${duplicate_vote}">
        	<p><b>나의 투표 번호:</b> (${vote_participant.vp_result})</p>
        </c:if>
        <div class="d-flex gap-2">
		    <a href="<c:url value="/votes"/>" class="btn btn-secondary mb-3" role="button">돌아가기</a>
		    <c:if test="${sessionScope.resident.r_category eq '관리소' or sessionScope.resident.r_login_id eq vote.v_creator_id}">
		    <!-- 현재 사용자가 관리소이거나 투표 작성자인 경우 -->
		        <a href="javascript:delete_confirm('${vote.v_id}')" class="btn btn-danger" role="button" style="height: 38px;">삭제</a>
		    </c:if>
		</div>
    </div>
    <%@ include file="./module/footer.jsp"%>
    <script>
 		<%-- 투표 결과를 차트로 표시 --%>
	    var options = [];
	    var results = [];
	    <%-- 옵션과 결과를 배열에 저장 --%>
	    <c:forEach var="i" begin="1" end="20">
	    <c:choose>
	    <%-- 옵션과 결과가 존재하는 경우에만 배열에 추가 --%>
	        <c:when test="${i==1 && vote.v_option1 != null}">
	            options.push("(1) ${vote.v_option1}");
	            results.push(${vote.v_result1});
	        </c:when>
	        <c:when test="${i==2 && vote.v_option2 != null}">
	            options.push("(2) ${vote.v_option2}");
	            results.push(${vote.v_result2});
	        </c:when>
	        <c:when test="${i==3 && vote.v_option3 != null}">
	            options.push("(3) ${vote.v_option3}");
	            results.push(${vote.v_result3});
	        </c:when>
	        <c:when test="${i==4 && vote.v_option4 != null}">
	            options.push("(4) ${vote.v_option4}");
	            results.push(${vote.v_result4});
	        </c:when>
	        <c:when test="${i==5 && vote.v_option5 != null}">
	            options.push("(5) ${vote.v_option5}");
	            results.push(${vote.v_result5});
	        </c:when>
	        <c:when test="${i==6 && vote.v_option6 != null}">
	            options.push("(6) ${vote.v_option6}");
	            results.push(${vote.v_result6});
	        </c:when>
	        <c:when test="${i==7 && vote.v_option7 != null}">
	            options.push("(7) ${vote.v_option7}");
	            results.push(${vote.v_result7});
	        </c:when>
	        <c:when test="${i==8 && vote.v_option8 != null}">
	            options.push("(8) ${vote.v_option8}");
	            results.push(${vote.v_result8});
	        </c:when>
	        <c:when test="${i==9 && vote.v_option9 != null}">
	            options.push("(9) ${vote.v_option9}");
	            results.push(${vote.v_result9});
	        </c:when>
	        <c:when test="${i==10 && vote.v_option10 != null}">
	            options.push("(10) ${vote.v_option10}");
	            results.push(${vote.v_result10});
	        </c:when>
	        <c:when test="${i==11 && vote.v_option11 != null}">
	            options.push("(11) ${vote.v_option11}");
	            results.push(${vote.v_result11});
	        </c:when>
	        <c:when test="${i==12 && vote.v_option12 != null}">
	            options.push("(12) ${vote.v_option12}");
	            results.push(${vote.v_result12});
	        </c:when>
	        <c:when test="${i==13 && vote.v_option13 != null}">
	            options.push("(13) ${vote.v_option13}");
	            results.push(${vote.v_result13});
	        </c:when>
	        <c:when test="${i==14 && vote.v_option14 != null}">
	            options.push("(14) ${vote.v_option14}");
	            results.push(${vote.v_result14});
	        </c:when>
	        <c:when test="${i==15 && vote.v_option15 != null}">
	            options.push("(15) ${vote.v_option15}");
	            results.push(${vote.v_result15});
	        </c:when>
	        <c:when test="${i==16 && vote.v_option16 != null}">
	            options.push("(16) ${vote.v_option16}");
	            results.push(${vote.v_result16});
	        </c:when>
	        <c:when test="${i==17 && vote.v_option17 != null}">
	            options.push("(17) ${vote.v_option17}");
	            results.push(${vote.v_result17});
	        </c:when>
	        <c:when test="${i==18 && vote.v_option18 != null}">
	            options.push("(18) ${vote.v_option18}");
	            results.push(${vote.v_result18});
	        </c:when>
	        <c:when test="${i==19 && vote.v_option19 != null}">
	            options.push("(19) ${vote.v_option19}");
	            results.push(${vote.v_result19});
	        </c:when>
	        <c:when test="${i==20 && vote.v_option20 != null}">
	            options.push("(20) ${vote.v_option20}");
	            results.push(${vote.v_result20});
	        </c:when>
	    </c:choose>
	</c:forEach>
	<%-- 차트 디자인 설정 --%>
	var ctx = document.getElementById('voteChart').getContext('2d');
	var voteChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: options,
	        datasets: [{
	            label: '투표 결과',
	            data: results,
	            backgroundColor: 'rgba(138, 170, 229, 0.5)',
	            borderColor: 'rgba(138, 170, 229)',
	            borderWidth: 2
	        }]
	    },
	    options: {
	        scales: {
	            y: {
	                beginAtZero: true,
	                ticks: {
	                    <%-- 각 눈금의 값을 정수로 변환하고 볼드 처리 --%>
	                    callback: function(value) {
	                        if (value % 1 === 0) {
	                            return value;
	                        }
	                    },
	                    font: {
	                        weight: 'bold' <%-- y축 눈금 볼드 처리 --%>
	                    }
	                }
	            },
	            x: {
	                ticks: {
	                    font: {
	                        weight: 'bold' <%-- x축 눈금 볼드 처리 --%>
	                    }
	                }
	            }
	        }
	    }
	});


	    
	    function delete_confirm(v_id) <%-- 투표 삭제 확인 --%>
		{
			if(confirm("삭제하시겠습니까?") == true) location.href="/buildingHelper/votes/delete?v_id=" + v_id;
			else return;
		}
		
		<%-- 선택한 옵션이 없는데 '투표하기' 버튼을 눌렀을 때--%>
		function checkOptionSelected() {
	        const options = document.querySelectorAll('input[name="voteOption"]:checked');
	        if (options.length === 0) {
	            alert('옵션을 선택해주세요.');
	            return false; // 폼 제출 방지
	        }
	        return true; // 폼 제출 계속 진행
	    }

	</script>
</body>
</html>
