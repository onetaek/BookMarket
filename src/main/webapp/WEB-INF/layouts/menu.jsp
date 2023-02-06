<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand navbar-dark bg-dark">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="../index.jsp">Home</a>
            </div>
	         <div>
				<ul class="navbar-nav mr-auto">
					<c:choose>
					<c:when test="${empty sessionId}">
						<li class="nav-item"><a class="nav-link" href='/member/login'>로그인</a></li>
						<li class="nav-item"><a class="nav-link" href='/member/join'>회원 가입</a></li>
					</c:when>
					<c:otherwise>
						<li style="padding-top: 7px; color: white">[${sessionName}님]</li>
						<li class="nav-item"><a class="nav-link" href='/member/logout'>로그아웃</a> </li>
						<li class="nav-item"><a class="nav-link" href='/member/update'>회원 수정</a> </li>
					</c:otherwise>
					</c:choose>
					<li class="nav-item"><a class="nav-link" href="/books">상품 목록</a></li>
					<li class="nav-item"><a class="nav-link" href="/book/add">상품 등록</a></li>
					<li class="nav-item"><a class="nav-link" href="/cart">장바구니</a></li>
					<li class="nav-item"><a class="nav-link" href="#">게시판</a></li>
				</ul>
			</div>
        </div>
    </nav>


