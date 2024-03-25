<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>

<!-- chart -->
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/expense_check.css"/>" rel="stylesheet"> 
<script src="https://unpkg.com/@adminkit/core@latest/dist/js/app.js"></script>


<!-- <link rel="stylesheet" href="https://unpkg.com/@adminkit/core@latest/dist/css/app.css"> -->
<title>관리비 조회</title>
</head>


<%@ include file="module/header.jsp"%>
<body>

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-5 text-center" style="margin: 30px" >관리비 조회</h1>
		</div>
	</div>

	<div class="container">
		<div class="row">
   			<div class="col-12 col-lg-6 mx-auto">				
               		<a href="<c:url value="/expense/list"/>" style="text-decoration:none;">
						<h1 class="h3 mb-3"><strong>${address.house_name }</strong> ${house.house_dong } ${house.house_hosu }</h1>
					</a>
					
					<div class="col-12 d-flex">
						<div class="w-100">
							<div class="row">
								<div class="col-sm-12">
									<div class="card">
										<div class="card-body">
											<div class="row">
												<div class="col">
													<h5 class="card-title">${expense_check.expense_year}년 ${expense_check.expense_month}월분</h5>
												</div>

												<div class="col-auto">
													<div class="stat text-primary">
														${expense_check.expense_state }
													</div>
												</div>
											</div>
											<h1 class="mt-1 mb-3">${expense_check.expense_price }원</h1>
											<c:choose>
											    <c:when test="${not empty expense_check_last_month}">
											        <div class="mb-0">
											            <span class="text-muted"><strong>저번 달</strong> 보다</span>
											            <span class="${message == '증가했어요!' ? 'text-danger' : 'text-primary'}">${percent}%</span>
											            <span class="text-muted">${message}</span>
											        </div>
											    </c:when>
											    <c:otherwise>
											        <div class="mb-0">
											            <span class="text-muted">입주를 환영합니다!</span>
											        </div>
											    </c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				<div class="col-12 d-flex">
					<div class="card flex-fill w-100">
						<div class="card-header">
							
							<h5 class="card-title mb-0">당월 관리비 세부 내역</h5>
							
						</div>
						<div class="card-body d-flex">	
							<div class="align-self-center w-100">
								<div class="py-3">
									<div class="chart chart-xs">
										<canvas id="chartjs-dashboard-pie"></canvas>
									</div>
								</div>

								<table class="table mb-0" style="background-color: white; font-weight: bold;">
									<tbody>
										<tr>
											<td style="color: #007bff; ">전기세</td>
											<td class="text-end">${expense_check.e_charge } 원</td>
										</tr>
										<tr>
											<td style="color: #fd7e14;">수도세</td>
											<td class="text-end">${expense_check.water_charge } 원</td>
										</tr>
										<tr>
											<td style="color: #dc3545;">공용관리비</td>
											<td class="text-end">${expense_check.p_charge } 원</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				    <div class="row">
				        <div class="col-12">
				            <div class="card mx-auto ">
				                <div class="card-header">
				                    <h5 class="card-title">전년도와 관리비 비교</h5>
				                </div>
				                <div class="card-body">
				                    <div class="chart w-100">
				                        <canvas id="chartjs-bar"></canvas>
				                    </div>
				                </div>
				            </div>
				        </div>
				    </div>
				</div>		
		</div>
	</div>
		<br>
</body>
<%@ include file="module/footer.jsp"%>

<!-- Pie chart -->
<script>
	document.addEventListener("DOMContentLoaded", function() {
		new Chart(document.getElementById("chartjs-dashboard-pie"), {
			type: "pie",
			data: {
				labels: ["전기세", "수도세", "공용관리비"],
				datasets: [{
					data: [${expense_check.e_charge }, ${expense_check.water_charge }, ${expense_check.p_charge }],
					backgroundColor: [
						window.theme.primary,
						window.theme.warning,
						window.theme.danger
					],
					borderWidth: 5
				}]
			},
			options: {
				responsive: !window.MSInputMethodContext,
				maintainAspectRatio: false,
				legend: {
					display: true
				},
				cutoutPercentage: 75
			}
		});
	});
</script>

<!-- bar chart -->
<script>
    document.addEventListener("DOMContentLoaded", function() {

        
        // Bar chart
        new Chart(document.getElementById("chartjs-bar"), {
            type: "bar",
            data: {
                labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
                datasets: [{
                    label: "전년도",
                    backgroundColor: "#dee2e6",
                    borderColor: "#dee2e6",
                    hoverBackgroundColor: "#dee2e6",
                    hoverBorderColor: "#dee2e6",
                    
                    data: [
                        <c:forEach begin="1" end="12" var="month">
                            <c:set var="found" value="false"/>
                            <c:forEach items="${expense_last_year}" var="last_year">
                                <c:if test="${last_year.expense_month == month}">
                                    ${last_year.expense_price}
                                    <c:set var="found" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${not found}">0</c:if>
                            <c:if test="${month != 12}">,</c:if>
                        </c:forEach>
                    ],

                    barPercentage: .75,
                    categoryPercentage: .5
                }, {
                    label: "올해",
                    backgroundColor: window.theme.primary,
                    borderColor: window.theme.primary,
                    hoverBackgroundColor: window.theme.primary,
                    hoverBorderColor: window.theme.primary,
                    data: [
                        <c:forEach begin="1" end="12" var="month">
                            <c:set var="found" value="false"/>
                            <c:forEach items="${expense_year}" var="year">
                                <c:if test="${year.expense_month == month}">
                                    ${year.expense_price}
                                    <c:set var="found" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${not found}">0</c:if>
                            <c:if test="${month != 12}">,</c:if>
                        </c:forEach>
                    ],

                    barPercentage: .75,
                    categoryPercentage: .5
                }]
            },
            options: {
                maintainAspectRatio: false,
                legend: {
                    display: true
                },
                scales: {
                    yAxes: [{
                        gridLines: {
                            display: false
                        },
                        stacked: false,
                        ticks: {
                            stepSize: 50000,
                            callback: function(value, index, values) {
                                return value.toLocaleString() + "원";
                            }
                        }
                    }],
                    xAxes: [{
                        stacked: false,
                        gridLines: {
                            color: "transparent"
                        },
                    }]
                },
                tooltips: {
                    callbacks: {
                        label: function(tooltipItem, data) {
                            var datasetLabel = data.datasets[tooltipItem.datasetIndex].label || '';
                            return datasetLabel + ": " + tooltipItem.yLabel.toLocaleString() + "원";
                        }
                    }
                }
            }
        });
    });
</script>


</html>