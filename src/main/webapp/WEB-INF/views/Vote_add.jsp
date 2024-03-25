<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>투표 추가</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <%@ include file="./module/header.jsp" %>
    <div class="jumbotron">
		<div class="container py-5">
        	<h1 class="text-center display-6">투표 등록하기</h1>
        </div>
    </div>
    <div class="container">
        <form action="<c:url value='/votes/add'/>" method="post" class="form-horizontal">
        	<fieldset>
	            <div class="form-group row mb-1">
					<label class="col-sm-2 control-label">제목</label>
					<div class="col-sm-4">
						<input name="v_title" class="form-control"/>
					</div>
				</div>
				<div class="form-group row mb-1">
					<label class="col-sm-2 control-label">종료 시간</label>
					<div class="col-sm-4">
						<input type="datetime-local" name="v_end_time" class="form-control"/>
					</div>
				</div>
	            <div id="options">
		            <div class="form-group row mb-1">
		                <label class="col-sm-2 control-label">옵션 1</label>
		                <div class="col-sm-4">
		                    <input type="text" class="form-control" name="v_option1" required>
		                </div>
		            </div>
		            <div class="form-group row mb-1">
		                <label class="col-sm-2 control-label">옵션 2</label>
		                <div class="col-sm-4">
		                    <input type="text" class="form-control" name="v_option2" required>
		                </div>
		            </div>
		        </div>
		        <div class="form-group row mt-4">
					<div class="d-flex gap-2">
						<button type="submit" class="btn btn-primary">등록하기</button>
			            <button id="addOption" type="button" class="btn btn-success">옵션 추가</button>
			            <a href="<c:url value="/votes"/>" class="btn btn-secondary">돌아가기</a>
		            </div>
	            </div>
            </fieldset>
        </form>
    </div>
    <%@ include file="./module/footer.jsp" %>

    <script> <%-- 옵션을 추가하는 자바스크립트 --%>
        $(document).ready(function() { <%-- 페이지가 준비되면 바로 실행 --%> 
            var optionCount = 2;

            $('#addOption').click(function() { <%-- 옵션 추가 버튼 클릭 시 실행되는 함수 --%>
                optionCount++;
                var optionDiv = $('<div class="form-group row mb-1"></div>'); <%-- 옵션을 감싸는 div 생성 --%>
                var optionLabel = $('<label class="col-sm-2 control-label">옵션 ' + optionCount + '</label>'); <%-- 옵션의 라벨 생성 --%>
                var optionInput = $('<input type="text" class="form-control" name="v_option' + optionCount + '" required>'); <%-- 옵션의 입력 필드 생성 --%>
                optionDiv.append(optionLabel);
                optionDiv.append($('<div class="col-sm-4"></div>').append(optionInput));
                $('#options').append(optionDiv);  <%-- 새로운 옵션을 기존의 옵션들에 추가 --%>

                if (optionCount >= 20) {
                    $('#addOption').hide();
                } <%-- 옵션 개수가 20개 이상이면 옵션 추가 버튼 숨김 --%>
            });
        });
    </script>
</body>
</html>
