<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-02-07
  Time: 오후 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layouts/menu.jsp" />
<div class="container">
  <div class="jumbotron">
    <div class="container">
      <h1 class="display-3">결제 완료</h1>
    </div>
  </div>
  <div class="form-group row">
    <label class="col-sm-2">주문 번호</label>
    <div class="col-sm-3">
      ${info.orderNo}
    </div>
  </div>
  <div class="form-group row">
    <label class="col-sm-2">주문자</label>
    <div class="col-sm-3">
      ${info.orderName}
    </div>
  </div>
  <div class="form-group row">
    <label class="col-sm-2">주문자 연락처</label>
    <div class="col-sm-3">
      ${info.orderTel}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">주문자 이메일</label>
    <div class="col-sm-3">
      ${info.orderEmail}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">수령자</label>
    <div class="col-sm-3">
      ${info.receiveName}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">수령자 연락처</label>
    <div class="col-sm-3">
      ${info.receiveTel}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">수령자 주소</label>
    <div class="col-sm-3">
      ${info.receiveAddress}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">총 결제 금액</label>
    <div class="col-sm-3">
      ${info.payAmount}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">결제 방법</label>
    <div class="col-sm-3">
      ${info.payMethod}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">결제 단계</label>
    <div class="col-sm-3">
      ${info.orderStep}
    </div>
  </div>

  <div class="form-group row">
    <label class="col-sm-2">주문일시</label>
    <div class="col-sm-3">
      ${info.dateOrder}
    </div>
  </div>
  <div class="form-group row">
    <label class="col-sm-2">결제 완료 일시</label>
    <div class="col-sm-3">
      ${info.datePay}
    </div>
  </div>

</div>

</body>
</html>
