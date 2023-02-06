<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<script>
  function addToCart(){
    if(confirm("상품을 장바구니에 추가하시겠습니까?")){
      document.addForm.submit();
    }else{
      document.addForm.reset();
    }
  }
</script>
</head>
<body>
<jsp:include page="../layouts/menu.jsp"/>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">상품정보</h1>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-md-5">
      <img src="#"
           style="width:100%"/>
    </div>
    <div class="col-md-6">
      <h3>${book.name}</h3>
      <p>${book.description}</p>
      <p><b>상품 코드 : </b><span class="badge badge-danger">${book.bookId}</span></p>
      <p><b>제조사</b> : ${book.publisher}</p>
      <p><b>분류</b> : ${book.category}</p>
      <p><b>재고 수</b> : ${book.unitsInStock}</p>
      <h4>${book.unitPrice}>원</h4>
      <p>
      <form name="addForm" action="/cart/add" method="post">
        <input type="hidden" name="id" value="${book.id}">
        <c:if test="${sessionId !=null}">
          <a href="#" class="btn btn-info" onclick="addToCart()"> 상품 주문 &raquo;</a>
          <a href="/cart" class="btn btn-warning">장바구니 &raquo;</a>
        </c:if>
        <a href="/books" class="btn btn-secondary">상품 목록 &raquo;</a>
      </form>
      </p>
    </div>
  </div>
</div>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
