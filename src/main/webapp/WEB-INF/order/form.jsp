<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>배송 정보</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layouts/menu.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h1 class="display-3">배송 정보</h1>
    </div>
</div>

<div class="container">
    <table class="table table-hover">
        <tr>
            <th>상품</th>
            <th>가격</th>
            <th>수량</th>
            <th>소계</th>
        </tr>
        <c:forEach var="data" items="${datas}">
            <tr>
                <td>${data.productId} - ${data.productName}</td>
                <td>${data.unitPrice}</td>
                <td>${data.cnt}</td>
                <td>${data.sumPrice}</td>
            </tr>
        </c:forEach>
        <tr>
            <th></th>
            <th></th>
            <th>총액</th>
            <th>${totalPrice}</th>
            <th></th>
        </tr>
    </table>

    <%--    form 태그 전달될 값과 전달 될 위치 주소 입력 전달 방식과 방법 설정--%>
    <form action="/order/pay" class="form-horizontal" method="post">
        <%--        주문자 이름 입력창.--%>
        <div class="form-group row">
            <label class="col-sm-2">주문자 이름</label>
            <div class="col-sm-3">
                <input name="orderName" type="text" class="form-control">
            </div>
        </div>

        <%--        주문자 연락처 입력창--%>
        <div class="form-group row">
            <label class="col-sm-2">주문자 연락처</label>
            <div class="col-sm-3">
                <input type="text" name="orderTel" class="form-control">
            </div>
        </div>

        <%--        주문자 이메일 입력창--%>
        <div class="form-group row">
            <label class="col-sm-2">주문자 이메일</label>
            <div class="col-sm-3">
                <input type="text" name="orderEmail" class="form-control">
            </div>
        </div>

        <%--        받는 사람 이름 입력창--%>
        <div class="form-group row">
            <label class="col-sm-2">받는 사람 이름</label>
            <div class="col-sm-3">
                <input type="text" name="receiveName" class="form-control">
            </div>
        </div>

        <%--        받는 사람 연락처 입력창--%>
        <div class="form-group row">
            <label class="col-sm-2">받는 사람 연락처</label>
            <div class="col-sm-3">
                <input type="text" name="receiveTel" class="form-control">
            </div>
        </div>

        <%--        받는 사람 주소 입력창--%>
        <div class="form-group row">
            <label class="col-sm-2">받는 사람 주소</label>
            <div class="col-sm-3">
                <input type="text" name="receiveAddress" class="form-control">
            </div>
        </div>

        <%--        submit 버튼 부분.--%>
        <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="/cart" class="btn btn-secondary" role="button"> 이전 </a>
                <input type="submit" class="btn btn-primary" value="등록">
            </div>
        </div>
    </form>
</div>

<jsp:include page="../layouts/footer.jsp"/>

</body>
</html>