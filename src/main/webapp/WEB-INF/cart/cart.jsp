<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../layouts/menu.jsp" />
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">장바구니</h1>
  </div>
</div>

<div class="container">
  <div class="row">
    <table width="100%">
      <tr>
        <td align="left"><span onclick="deleteCart();" class="btn btn-danger">삭제하기</span></td>
        <td align="right"><a href="/order" class="btn btn-success">주문하기</a></td>
      </tr>

    </table>
  </div>


  <div style="padding-top: 50px">
    <table class="table table-hover">
      <tr>
        <th>도서</th>
        <th>가격</th>
        <th>수량</th>
        <th>소계</th>
        <th>비고</th>
      </tr>
      <c:set var="sum" value="0" />
      <c:forEach var="cart" items="${carts}">
      <tr>
        <td>${cart.name}</td>
        <td>${cart.unitPrice}</td>
        <td>${cart.cnt}</td>
        <td>${cart.unitPrice * cart.cnt}</td>
        <td><span style="cursor:pointer"
                  onclick="removeCartById('${cart.bookId}')"
                  class="badge badge-danger">삭제</span></td>
      </tr>
        <c:set var="sum" value="${sum + cart.unitPrice * cart.cnt}"/>
      </c:forEach>
      <tr>
        <th></th>
        <th></th>
        <th>총액</th>
        <th>${sum}</th>
        <th></th>
      </tr>
    </table>
    <a href="/books" class="btn btn-secondary">&laquo; 쇼핑
      계속하기</a>
  </div>

  <form name="frmCart" method="post">
    <input type="hidden" name="id" />
  </form>
  <script>
    const frm = document.frmCart;
    let removeCartById = function(ID) {
      if (confirm("해당 상품을 장바구니에서 삭제하시겠습니까?")) {
        frm.id.value=ID;
        frm.action="cart/delete";
        frm.submit();
      }
    }

    let deleteCart = function() {
      if(confirm('삭제하시겠습니까?')){
        frm.action ="cart/deleteAll";
        frm.submit();
      }
    }
  </script>
  <hr>
</div>
<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
