<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<title>Thank you</title>
</head>
<body>
    <%@ include file="./module/header.jsp" %>
    <div class="jumbotron">
        <div class="container py-5">
            <h1 class="text-center display-6">대여 신청하기</h1>
        </div>
    </div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="alert alert-danger">
                    <h2>신청 내용을 확인해 주세요.</h2>
                    <p class="mb-0">신청을 하면 승인 대기 상태가 됩니다.</p>
                </div>
                <form action="${pageContext.request.contextPath}/shareitems/rental/request/${shareItem.share_item_id}" method="post">
                    <table class="table table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th class="align-middle">분류</th>
                                <th class="align-middle">물품</th>
                                <th class="align-middle">신청날짜</th>
                                <th class="align-middle">반납날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="align-middle">${shareItem.s_category}</td>
                                <td class="align-middle">${shareItem.name}</td>
                                <td class="align-middle">${today}</td>
                                <td class="align-middle">
                                    <input type="datetime-local" class="form-control" name="s_return_time" required>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-end gap-2">
                        <a href="<c:url value="/shareitems"/>" class="btn btn-secondary mb-4">돌아가기</a>
                        <input type="submit" value="신청" class="btn btn-primary mb-4">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="./module/footer.jsp" %>
</body>
</html>