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
    <h1 class="display-3">도서 등록</h1>
  </div>
</div>
<div class="container">
  <form name="newProduct" action="/book/add" class="form-horizontal"  method="post"
        enctype="multipart/form-data">
    <div class="form-group row">
      <label class="col-sm-2">도서 코드</label>
      <div class="col-sm-3">
        <input type="text" id="bookId" name="bookId" class="form-control"/>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">도서명</label>
      <div class="col-sm-3">
        <input type="text" id="name" name="name" class="form-control"/>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">가격</label>
      <div class="col-sm-3">
        <input type="text" id="unitPrice" name="unitPrice" class="form-control"/>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">저자</label>
      <div class="col-sm-5">
        <input type="text" name="author" class="form-control"/>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">도서정보</label>
      <div class="col-sm-5">
					<textarea name="description" cols="50" rows="2"
                              class="form-control"></textarea>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">출판사</label>
      <div class="col-sm-3">
        <input type="text" name="publisher" class="form-control"/>
      </div>
    </div>
    <div class="form-group row">
      <label class="col-sm-2">카테고리</label>
      <div class="col-sm-3">
        <input type="text" name="category" class="form-control"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">도서 수</label>
      <div class="col-sm-3">
        <input type="text" id="unitsInStock" name="unitsInStock" class="form-control"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">총페이지</label>
      <div class="col-sm-3">
        <input type="text" name="totalPages" class="form-control"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">출판일</label>
      <div class="col-sm-3">
        <input type="date" name="releaseDate" class="form-control"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">상태</label>
      <div class="col-sm-5">
        <input type="radio" name="condition" value="New"/> 신규 제품
        <input type="radio" name="condition" value="Old"/> 중고 제품
        <input type="radio" name="condition" value="Refurbished"/> 재생 제품
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">이미지</label>
      <div class="col-sm-5">
        <input type="file" name="bookImage" class="form-control"/>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-sm-offset-2 col-sm-10">
        <input onclick="checkAddBook()" type="button" class="btn btn-primary" value="등록"/>
      </div>
    </div>
  </form>
</div>  1023
<script>

  function checkAddBook(){
    console.log("checkAddProduct실행")
    const bookId = document.getElementById("bookId")
    const name = document.getElementById("name");
    const unitPrice = document.getElementById("unitPrice");
    const unitsInStock = document.getElementById("unitsInStock");

    // 상품아이디 체크
    if (!check(/^P[0-9]{4,11}$/, bookId,
            "[상품 코드]\nP와 숫자를 조합하여 5~12자까지 입력하세요\n첫 글자는 반드시 P로 시작하세요"))
      return false;
    // 상품명 체크
    if (name.value.length < 4 || name.value.length > 12) {
      alert("[상품명]\n최소 4자에서 최대 12자까지 입력하세요");
      name.select();
      name.focus();
      return false;
    }
    // 상품 가격 체크
    if (unitPrice.value.length == 0 || isNaN(unitPrice.value)) {
      alert("[가격]\n숫자만 입력하세요");
      unitPrice.select();
      unitPrice.focus();
      return false;
    }
    if (unitPrice.value < 0) {
      alert("[가격]\n음수를 입력할 수 없습니다.");
      unitPrice.select();
      unitPrice.focus();
      return false;
    } else if (!check(/^\d+(?:[.]?[\d]?[\d])?$/, unitPrice, "[가격]\n소수점 둘째 자리까지만 입력하세요"))
      return false;

    // 재고 수 체크
    if (isNaN(unitsInStock.value)) {
      alert("[재고 수]\n숫자만 입력하세요");
      unitsInStock.select();
      unitsInStock.focus();
      return false;
    }

    function check(regExp, e, msg) {

      if (regExp.test(e.value)){
        return true;
      }
      alert(msg);
      e.select();
      e.focus();
      return false;
    }

    document.newProduct.submit()

  }

</script>
</body>
</html>
