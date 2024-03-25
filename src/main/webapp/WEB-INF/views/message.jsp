<%@ page import="com.buildingHelper.domain.Resident"%>
<%@ page import="com.buildingHelper.domain.House"%>
<%@ page import="com.buildingHelper.domain.Address"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">

<title>메시지 전송 페이지</title>
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
	                    <img src="<c:url value="/resources/images/two_doors.jpg"/>" alt="문 2개" class="img-fluid">
	                </div>
	                <div class="col-md-6 d-flex align-items-center">
	                    <div style="width: 100%; margin-left: 20px;">
	                        <p>메시지 전송</p>
	                        <h1>이웃에게<br>메시지 전송</h1>
	                        <p>전화번호를 몰라도<br>메시지를 전송할 수 있습니다.</p>
	                        <div class="mt-3">
	                            <a class="btn btn-secondary" href="./message/send">이웃에게 메시지 전송하기</a>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
   		</section>
   		
    <section style="background-color: #FAECC5;">
        <div class="container">
            <div class="row">
                <div class="col-md-6 d-flex align-items-center mb-4">
                    <div>
						<p>메시지 확인</p>
	                        <h1>이웃에게<br>받은 메시지 확인</h1>
	                        <p>이웃에게 받은 메시지를<br>확인해보세요!</p>
	                        <div class="mt-3">
	                            <a class="btn btn-secondary" href="./message/read">메시지 읽기</a>
	                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <img src="<c:url value="/resources/images/mailbox.jpg"/>" alt="우체통" class="img-fluid">
                </div>
            </div>
        </div>
    </section>
    
    <section style="background-color: #fffff2;">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <img src="<c:url value="/resources/images/billboard.jpg"/>" alt="빌보드" class="img-fluid">
                </div>
                <div class="col-md-6 d-flex align-items-center mb-4">
                    <div>
						<p>단체 메시지 확인</p>
	                        <h1>관리소나 동대표에서<br>보낸 메시지 확인</h1>
	                        <p>받은 단체 메시지를<br>확인해보세요!</p>
	                        <div class="mt-3">
	                            <a class="btn btn-secondary" href="./message/read_group_message">메시지 읽기</a>
	                        </div>
                    </div>
                </div>           
            </div>
        </div>
    </section>
   <% 
	try {
	    Resident resident = (Resident) session.getAttribute("resident");
	    if((resident.getR_category().equals("관리소") || resident.getR_category().equals("동대표"))) {
	%>  
    <section style="background-color: #F6F6F6;">
        <div class="container">
            <div class="row">            
                <div class="col-md-6 d-flex align-items-center">
                    <div>
                        <p>단체 메시지 보내기</p>
                        <% if(resident.getR_category().equals("관리소")) { %>
                            <h1>모든 세대원에게<br> 메시지 보내기</h1>
                            <p>중요한 전달사항<br>한 번에 알리세요!</p> 
                        <% } else if(resident.getR_category().equals("동대표")) { %>
                            <h1>같은 동 세대원에게<br> 메시지 보내기</h1>
                            <p>중요한 전달사항<br>한 번에 알리세요!</p> 
                        <% } %>                                    
                        <div class="mt-3">
                            <a class="btn btn-secondary" href="./message/send_group_message">
                            <% if(resident.getR_category().equals("관리소")) { %>
                                관리소 단체 메시지 보내기 
                            <% } else if(resident.getR_category().equals("동대표")) { %>
                                동대표 단체 메시지 보내기
                            <% } %>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <img src="<c:url value="/resources/images/megaphone.jpg"/>" alt="메가폰" class="img-fluid">
                </div>                    
            </div>
        </div>
    </section>
		<% 
		    } else {		        
		%>

		<% 
		    }
		} catch(Exception e) { %>
			<!-- resident가 null이거나 카테고리가 관리소 또는 동대표가 아닌 경우에 대한 처리-->
		    <script>
			alert("로그인이 필요합니다.");
	        // 로그인 페이지로 리다이렉트
	        window.location.href = "<c:url value='/login'/>";
			</script>		
	
		<%
			}
		%>

	<%@ include file="./module/footer.jsp"%>
</body>
</html>