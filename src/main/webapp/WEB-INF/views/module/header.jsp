<%@ page import="com.buildingHelper.domain.Resident"%>
<%@ page import="com.buildingHelper.domain.House"%>
<%@ page import="com.buildingHelper.domain.Address"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
@media (min-width: 992px) {
    .navbar-nav .nav-item.dropdown .dropdown-menu {
        display: none;
    }
    .navbar-nav .nav-item.dropdown:hover .dropdown-menu {
        display: block;
    }
}

@font-face {
   font-family: 'SUIT-Regular';
   src:	
      url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2')
      format('woff2');
   font-weight: 100;
   font-style: normal;
}

body {
   font-family: 'SUIT-Regular', sans-serif;
}

</style>



<div class="container-fluid custom-bg-sky-blue">
   <div class="container">
      <nav class="navbar navbar-dark navbar-expand-lg py-0">
         <c:choose>
             <c:when test="${empty sessionScope.resident}">
                 <!-- 세션이 없을 때의 버튼 -->
                 <c:url var="homeURL" value="/"/>
                 <a href="${homeURL}" class="navbar-brand">
                     <h1 class="text-white fw-bold d-block">Building Helper</h1>
                 </a>
             </c:when>
             <c:otherwise>
                 <!-- 세션이 있을 때의 버튼 -->
                 <c:url var="loggedInURL" value="/home"/>
                 <a href="${loggedInURL}" class="navbar-brand">
                     <h1 class="text-white fw-bold d-block">Building Helper</h1>
                 </a>
             </c:otherwise>
         </c:choose>
         
         <button type="button" class="navbar-toggler me-0" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse bg-transparent"
            id="navbarCollapse">
            <div class="navbar-nav ms-auto p-0 text-white fw-bold ">

               <a href="<c:url value="/estate"/>" class="nav-item nav-link">부동산
                  거래</a> 
				
				<div class="nav-item dropdown">
                  <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">관리비 조회</a>
                  <div class="dropdown-menu rounded">
                     <a href="<c:url value="javascript:checkLogin7()"/>" class="dropdown-item">이번달 관리비 조회</a> 
                     <a href="<c:url value="javascript:checkLogin6()"/>" class="dropdown-item">관리비 납부 조회</a>
                  </div>
               </div>


               <div class="nav-item dropdown">
                  <a href="#" class="nav-link dropdown-toggle"
                     data-bs-toggle="dropdown">커뮤니티</a>
                  <div class="dropdown-menu rounded">
                     <a href="<c:url value="javascript:checkLogin5()"/>" class="dropdown-item">아파트 입주민 거래</a> 
                     <a href="<c:url value="javascript:checkLogin4()"/>" class="dropdown-item">아파트 입주민 거래 등록</a> 
                     <a href="<c:url value="javascript:checkLogin3()"/>" class="dropdown-item">입주민 동아리</a>
                     <a href="<c:url value="javascript:checkLogin2()"/>" class="dropdown-item">공유 물건 대여</a>
                     <a href="<c:url value="javascript:checkLogin()"/>" class="dropdown-item">투표 게시판</a>
                     <a href="<c:url value="javascript:checkLogin8()"/>" class="dropdown-item">메시지 전송</a>
                  </div>
               </div>

               <% if(session.getAttribute("resident") == null) { %>
               <!-- 비로그인 상태 -->
               <a class="nav-item nav-link" href="<c:url value="/login"/>">로그인</a>
               <% } else { %>
               <!-- 로그인 상태 -->
               <% Resident resident = (Resident) session.getAttribute("resident"); 
                   if(resident.getR_category().equals("관리소")) { %>
               <div class="nav-item dropdown">
                  <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">관리사무소</a>
                  <div class="dropdown-menu rounded">
                     <a href="<c:url value="/resident/account_management"/>" class="dropdown-item">개인 정보 수정</a> 
                     <a href="<c:url value="/management_office/management_office_register_link"/>" class="dropdown-item">회원 가입 링크</a>
                     <a href="<c:url value="/management_office/management_office_read_resident"/>" class="dropdown-item">모든 세대원 조회</a>
                     <a href="<c:url value="/resident/account_management"/>"
                           class="dropdown-item">개인 정보 수정</a> 
                     <a href="<c:url value="/management_office/management_office_register_link"/>"
                           class="dropdown-item">회원 가입 링크</a>
                     <a href="<c:url value="/management_office/management_office_read_resident"/>"
                           class="dropdown-item">모든 세대원 조회</a>                          
                  </div>
               </div>
                  <% } else if(resident.getR_category().equals("세대원") || resident.getR_category().equals("동대표")) { %>
                  <div class="nav-item dropdown">
                     <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${resident.getR_nickname()}님</a>
                     <div class="dropdown-menu rounded">
                        <a href="<c:url value="/resident/account_management"/>" class="dropdown-item">개인 정보 수정</a> 
                        <a href="<c:url value="/resident/my_house_all_resident"/>" class="dropdown-item">세대원 정보 조회</a>
                     </div>
                  </div>
                  <% } %>

                  <a class="nav-item nav-link" href="<c:url value="/logout"/>">
                     로그아웃 </a>

                  <% } %>
               </div>
            </div>
      </nav>
   </div>
</div>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
<script>
	function checkLogin()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/votes";
	    }
	}
	
	function checkLogin2()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/shareitems";
	    }
	}
	
	function checkLogin3()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/clubs";
	    }
	}
	
	function checkLogin4()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/itemtrade/add";
	    }
	}
	
	function checkLogin5()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/itemtrade";
	    }
	}
	
	function checkLogin6()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/expense/list";
	    }
	}
	
	function checkLogin7()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/expense";
	    }
	}
	
	function checkLogin8()
	{
		var notlogin = <%= session.getAttribute("resident") == null %>;
		
		if (notlogin) {
            alert("로그인이 필요합니다.");
            window.location.href = "<c:url value='/login'/>";
        }
		else {
	        location.href="/buildingHelper/resident/message";
	    }
	}
</script>
