<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
   src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.1/js/bootstrap.min.js"></script>
<title>프로젝트 관리사무소</title>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BuildingHelper</title>
<style>
    	a {
    		 color: inherit;
    		text-decoration : none;
    	}
        section {
            padding: 50px 0;
        }

        section p {
            font-size: 18px;
            color: #333;
        }

        section h1 {
            font-size: 36px;
            margin-top: 10px;
            margin-bottom: 20px;
            line-height: 1.3;
        }

        section ul {
            padding: 0;
            list-style: none;
        }

        section li {
            margin-bottom: 20px;
        }

        section .font-weight-bold {
            font-weight: bold;
        }

        section a.btn-secondary {
            margin-top: 20px;
        }

        @media (max-width: 767px) {
            section h1 {
                font-size: 28px;
            }
        }
        .couch{
        	width:650px;
        	height:550px;
        }
        .couch img{
        	width:100%;
        	height:100%;
        }
</style>
</head>
	

<body>
	<%@ include file="./module/header.jsp"%>
    <section style="background-color: #F6F6F6;">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <img src="resources/images/project-1.jpg" alt="우리동네 중고 직거래 사진" class="img-fluid">
                </div>
                <div class="col-md-6 d-flex align-items-center">
                    <div style="width: 100%; margin-left: 20px;">
                        <p>중고거래</p>
                        <h1>믿을만한<br>이웃 간 중고거래</h1>
                        <p>우리 아파트 주민들과 가깝고 따뜻한 거래를<br>지금 경험해보세요.</p>
                        <div class="mt-3">
                            <a class="btn btn-secondary" href="itemtrade">아파트 입주민 개인 거래</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section style="background-color: #FAECC5;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 d-flex align-items-center">
                    <div>
                        <p>커뮤니티</p>
                        <h1>우리 아파트에서</h1>
                        <ul>
                            <li>
                                <a href="<c:url value="/clubs"/>" >
	                                <div class="font-weight-bold mt-3 mb-2">입주민 동아리</div>
                                	<div>우리 이웃들과 동아리<br>활동을 해보세요.</div>
                                </a>
                            </li>
                       	    <li>	
                                <a href="<c:url value="/shareitems"/>" >
	                                <div class="font-weight-bold mt-3 mb-2">공유 물품 대여</div>
	                                <div>관리사무소에서 공유<br>물품 대여를 해보세요.</div>
                                </a>
                            </li>
                            <li>
                                <a href="<c:url value="/resident/message"/>" >                          
	                                <div class="font-weight-bold mt-3 mb-2">메시지 전송</div>
	                                <div>알릴 소식이 있다면 메시지<br> 전송 기능을 이용하세요.</div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6">
                    <img src="resources/images/project-2.jpg" alt="이웃과 함께하는 동네생활" class="img-fluid">
                </div>
            </div>
        </div>
    </section>
    <section style="background-color: #fffff2;">    
		    <div class="container">
		        <div class="row justify-content-center align-items-center"> 
		            <div class="col-lg-7">
		                    <img src="resources/images/couch.jpg" class="img-fluid">
		            </div>
		            <div class="col-lg-5" style="padding-left:40px;">
		                <div> 
                            <p>부동산거래</p>	              
		                    <h1>소파처럼 편안한</h1>
		                    <h1>부동산 직거래</h1>
		                    <p class="mb-4">나와 이웃이 살던 집, 편하게 직거래해보세요.</p>
		                </div>
		                <p><a href="estate" class="btn btn-secondary">부동산 거래 게시판</a></p>
		            </div>
		        </div>
		    </div>
    </section>
</body>
	<%@ include file="./module/footer.jsp"%>

</html>
