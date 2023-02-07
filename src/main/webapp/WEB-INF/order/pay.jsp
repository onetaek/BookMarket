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
    <h1 class="display-3">결제 정보</h1>
  </div>
</div>
<div class="container">
  <section>
    <h1>구매하기</h1>
    <h3>구매 상품 : ${orderProductName}</h3>
    <span>${totalPrice}원</span>
    ${info.orderName}
    <p>-------------------------</p>
    <div><label><input type="radio" name="method" value="카드" checked/>신용카드</label></div>
    <div><label><input type="radio" name="method" value="가상계좌"/>가상계좌</label></div>
    <p>-------------------------</p>
    <button id="payment-button">결제하기</button>
  </section>
  <script src="https://js.tosspayments.com/v1"></script>
  <script>
    var tossPayments = TossPayments("test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq");
    var button = document.getElementById("payment-button");

    // var orderId = new Date().getTime();
    //
    button.addEventListener("click", function () {
      var method = document.querySelector('input[name=method]:checked').value; // "카드" 혹은 "가상계좌"

      var paymentData = {
        amount: ${totalPrice},
        orderId: '${info.orderNo}',
        orderName: '${orderProductName}',
        customerName: '${info.orderName}',
        successUrl: window.location.origin + "/order/success", // 성공시 리턴될  주소
        failUrl: window.location.origin + "/order/fail",  // 실패시 리턴될 주소
      };

      if (method === '가상계좌') {//입금이 되는 시점에 호출이됨
        paymentData.virtualAccountCallbackUrl = window.location.origin + '/order/virtualAccountCallback'
      }

      tossPayments.requestPayment(method, paymentData);
    });
  </script>
</div>
</body>
</html>
