<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layouts/menu.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">회원정보</h1>
    </div>
</div>
<div class="container" align="center">
    <c:if test="${msg == 'MEMBER_UPDATE'}">
        <h2 class="alert alert-info">회원정보가 수정되었습니다.</h2>
    </c:if>
    <c:if test="${msg == 'MEMBER_JOIN'}">
        <h2 class="alert alert-success">회원가입을 축하 드립니다.</h2>
    </c:if>
    <c:if test="${msg == 'MEMBER_LOGIN'}">
        <h2 class="alert alert-success">${sessionName}님 환영합니다.</h2>
    </c:if>
    <c:if test="${msg == 'MEMBER_DELETE'}">
        <h2 class="alert alert-danger">회원정보가 삭제되었습니다.</h2>
    </c:if>
</div>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
