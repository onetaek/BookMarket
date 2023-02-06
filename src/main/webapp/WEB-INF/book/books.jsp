<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layouts/menu.jsp"/>
<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">상품목록</h1>
    </div>
</div>
<div class="container">
    <div class="row" align="center">
        <c:forEach var="book" items="${books}">
        <div class="col-md-6">
            <img class="my_img" src="#" style="width:300px";/>
            <h3 class="text-left"><a href="/book?id=${book.id}">${book.category} ${book.name}</a></h3>
            <p class="text-left">${book.description}</p>
            <p class="text-left">${book.author} | ${book.publisher} | ${book.unitPrice}</p>
            <p><a href="/book?id=${book.id}"
                  class="btn btn-secondary" role="button"> 상세 정보 &raquo;</a></p>
        </div>
        </c:forEach>
    </div>
</div>
<hr>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
