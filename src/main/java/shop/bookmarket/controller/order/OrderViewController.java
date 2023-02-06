package shop.bookmarket.controller.order;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.dto.*;
import shop.bookmarket.model.repository.BookRepository;
import shop.bookmarket.model.repository.CartRepository;
import shop.bookmarket.model.repository.MemberRepository;
import shop.bookmarket.model.repository.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderViewController implements shop.bookmarket.controller.Controller {
    OrderRepository orderRepository = OrderRepository.getInstance();
    CartRepository cartRepository = CartRepository.getInstance();
    MemberRepository memberRepository = MemberRepository.getInstance();
    BookRepository bookRepository = BookRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 장바구니에 있는 상품을 주문 데이터에 복사
         * 결제 금액을 장바구니가 아니라 주문데이터 기준으로 계산
         * */
        //주문 번호 가져오기
        String orderNo = req.getSession().getId();

        //1. 중복을 막기 위해 주문 번호로 저장된 데이터 삭제
        log.info("orderRepository.deleteByOrderNo(orderNo)");
        orderRepository.deleteByOrderNo(orderNo);

        //2. 주문번호 기준을 장바구니에 있는 상품을 가지고 옴
        log.info("List<CartDto> carts = cartRepository.findByOrderNo(orderNo)");
        List<CartDto> carts = cartRepository.findByOrderNo(orderNo);

        //3. CartList를 OrderData List를 변경
        log.info("List<OrderDataDto> orderDatas = new ArrayList<>()");
        List<OrderDataDto> orderDatas = new ArrayList<>();
        for(CartDto cart: carts){
            MemberDto findMember = memberRepository.findById(cart.getMemberId());
            BookDto findBook = bookRepository.findById(cart.getBookId());
            log.info("cart = {}",cart);
            OrderDataDto dto = OrderDataDto.builder()
                    .orderNo(orderNo)
                    .cartId(cart.getId())
                    .productId(findBook.getBookId())
                    .productName(findBook.getName())
                    .unitPrice(cart.getUnitPrice())
                    .cnt(cart.getCnt())
                    .sumPrice(cart.getUnitPrice() * cart.getCnt())
                    .build();
            orderDatas.add(dto);
        }

        //4. OrderData List를 데이터 베이스에 저장
        log.info("orderRepository.save(orderData) -> forEach문");
        for (OrderDataDto orderData : orderDatas) {
            orderRepository.save(orderData);
        }

        log.info("orderRepository.getTotalPriceByOrderNo(orderNo)");
        List<OrderDataDto> datas = orderRepository.findAllByOrderNo(orderNo);
        int totalPrice = orderRepository.getTotalPriceByOrderNo(orderNo);


        req.setAttribute("datas", datas);
        req.setAttribute("totalPrice",totalPrice);

        return new View("/order/form");
    }
}
