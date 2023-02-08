<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>결제 성공</title>
    <meta charset="UTF8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
</head>
<body>
<section>
    <c:if test="${isSuccess}">
        <h1>결제 성공</h1>
        <h3>상품명: 토스 티셔츠</h3>
        <h3>결과 데이터: ${jsonObjectToString}</h3>
    </c:if>
    <c:if test="${!isSuccess}">
        <h1>결제 실패</h1>
        <p>HTTP error code: ${code} </p>
        <p>${jsonObjectGetMessage}</p>
        <span>에러코드: ${jsonObjectGetCode}</span>
    </c:if>
</section>
</body>
</html>