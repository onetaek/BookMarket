package shop.bookmarket.controller.order;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.dto.MemberDto;
import shop.bookmarket.model.dto.OrderDataDto;
import shop.bookmarket.model.dto.OrderInfoDto;
import shop.bookmarket.model.repository.MemberRepository;
import shop.bookmarket.model.repository.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class OrderPayController implements shop.bookmarket.controller.Controller {
    OrderRepository orderRepository = OrderRepository.getInstance();
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNo = req.getSession().getId();
        Long sessionId = Long.parseLong((String) req.getSession().getAttribute("sessionId"));//member테이블의 id컬럼

        //주문 정보 저장
        //1. 중복을 막기 위해 주문번호를 저장된 데이터 삭제
        log.info("orderRepository.deleteOrderInfoByOrderNo(orderNo)");
        orderRepository.deleteOrderInfoByOrderNo(orderNo);

        //2.request은 값을 dto에 저장하서 dao에 전달
        log.info("memberRepository.findById(sessionId)");
        MemberDto findMember = memberRepository.findById(sessionId);//memberId를 가져오기 위함
        OrderInfoDto orderInfoDto = OrderInfoDto.builder()
                .orderNo(orderNo)
                .memberId(findMember.getUserId())
                .orderName(req.getParameter("orderName"))
                .orderTel(req.getParameter("orderTel"))
                .orderEmail(req.getParameter("orderEmail"))
                .receiveName(req.getParameter("receiveName"))
                .receiveTel(req.getParameter("receiveTel"))
                .receiveAddress(req.getParameter("receiveAddress"))
                .payAmount(orderRepository.getTotalPriceByOrderNo(orderNo))
                .build();
        orderRepository.saveOrderInfo(orderInfoDto);


        //장바구니 합계 금액
        log.info("orderRepository.getTotalPriceByOrderNo(orderNo)");
        int totalPrice = orderRepository.getTotalPriceByOrderNo(orderNo);
        req.setAttribute("totalPrice",totalPrice);

        OrderInfoDto info = orderRepository.findAllOrderInfoByOrderNo(orderNo);
        req.setAttribute("info",info);

        //주문서 정보 가져옴(ex : 상품이름 외 1건...)
        log.info("orderRepository.getOrderProductNameByOrderNo(orderNo)");
        String orderProductName = orderRepository.getOrderProductNameByOrderNo(orderNo);
        req.setAttribute("orderProductName",orderProductName);

        return new View("/order/pay");
    }
}
