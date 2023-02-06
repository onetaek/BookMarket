<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layouts/menu.jsp" />
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">로그인</h1>
    </div>
</div>
<div class="container" align="center">
    <div class="col-md-4 col-md-offset-4">
        <h3 class="form-signin-heading">Please sign in</h3>
        <c:if test="${error != null}">
            <div class="alert alert-danger">
                아이디 또는 비밀번호가 틀렸습니다.
            </div>
        </c:if>
        <form class="login" action="login" method="post">
            <div class="form-group">
                <label class="sr-only">User Name</label>
                <input type="text" class="form-control" placeholder="ID" name="id" required autofocus>
            </div>
            <div class="form-group">
                <label class="sr-only">Password</label>
                <input type="password" class="form-control" placeholder="Password" name="password" required>
            </div>
            <button class="btn btn btn-lg btn-success btn-block" type="submit">로그인</button>
        </form>
    </div>
</div>
<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
