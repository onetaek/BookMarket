package shop.bookmarket.controller.member.frontcontroller;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.Controller;
import shop.bookmarket.controller.book.BookAddController;
import shop.bookmarket.controller.book.BookAddViewController;
import shop.bookmarket.controller.book.BookViewController;
import shop.bookmarket.controller.book.BooksViewController;
import shop.bookmarket.controller.cart.CartAddController;
import shop.bookmarket.controller.cart.CartDeleteAllController;
import shop.bookmarket.controller.cart.CartDeleteController;
import shop.bookmarket.controller.cart.CartViewController;
import shop.bookmarket.controller.member.LogoutController;
import shop.bookmarket.controller.member.MemberUpdateViewController;
import shop.bookmarket.controller.member.JoinController;
import shop.bookmarket.controller.member.JoinViewController;
import shop.bookmarket.controller.member.LoginController;
import shop.bookmarket.controller.member.LoginViewController;
import shop.bookmarket.controller.main.WelcomeController;
import shop.bookmarket.controller.member.resultMemberViewController;
import shop.bookmarket.controller.order.OrderPayController;
import shop.bookmarket.controller.order.OrderViewController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@WebServlet(name = "frontController", urlPatterns = "/")
public class FrontController extends HttpServlet {
    private Map<String, Controller> getMapping = new HashMap<>();
    private Map<String, Controller> postMapping = new HashMap<>();

    public FrontController(){
        //GET
        getMapping.put("/main/welcome", new WelcomeController());//index 페이지 이동
        getMapping.put("/member/login", new LoginViewController());//로그인 페이지 이동
        getMapping.put("/member/join", new JoinViewController());//회원가입 페이지 이동
        getMapping.put("/member/resultMember", new resultMemberViewController());//Member 결과 페이지 이동
        getMapping.put("/member/logout",new LogoutController());//로그아웃 처리
        getMapping.put("/member/update",new MemberUpdateViewController());//회원 수정 페이지 이동
        getMapping.put("/books",new BooksViewController());//전체 책 리스트 출력 페이지 이동
        getMapping.put("/book",new BookViewController());//하나의 책 리스트 출력 페이지 이동
        getMapping.put("/book/add",new BookAddViewController());//책 등록 페이지 이동
        getMapping.put("/cart", new CartViewController());//장바구니로 이동
        getMapping.put("/order",new OrderViewController());//주문서 페이지 출력-> 수업 form.do

        //POST
        postMapping.put("/member/login", new LoginController());//로그인 처리
        postMapping.put("/member/join",new JoinController());//회원가입 처리
        postMapping.put("/book/add",new BookAddController());//책 등록 처리
        postMapping.put("/cart/add",new CartAddController());//장바구니 추가 처리
        postMapping.put("/cart/delete",new CartDeleteController());//장바구니 한 항목 제거
        postMapping.put("/cart/deleteAll",new CartDeleteAllController());//장바구니 전체 제거
        postMapping.put("/order/pay",new OrderPayController());//주문서 정보 저장 및 결제 수단 출력 -> 수업 pay.do
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        log.info("method = {}, requestURI = {}",req.getMethod(),requestURI);
        Controller controller = null;
        if(method.equals("GET")){
            controller = getMapping.get(requestURI);
        }else if(method.equals("POST")){
            controller = postMapping.get(requestURI);
        }
        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        View view = controller.process(req, resp);
        if(view != null) view.render(req, resp);
    }
}
