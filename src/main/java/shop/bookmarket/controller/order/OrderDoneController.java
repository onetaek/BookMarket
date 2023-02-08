package shop.bookmarket.controller.order;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.OrderDataDto;
import shop.bookmarket.model.dto.OrderInfoDto;
import shop.bookmarket.model.repository.CartRepository;
import shop.bookmarket.model.repository.OrderRepository;
import shop.bookmarket.util.OrderStep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@Slf4j
public class OrderDoneController implements shop.bookmarket.controller.Controller {
    OrderRepository orderRepository = OrderRepository.getInstance();
    CartRepository cartRepository = CartRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
        String orderNo = req.getSession().getId();

        //order_data에 있는 order_no 기준으로 장바구니에 있는 상품을 삭제
        log.info("orderRepository.findAllOrderDataByOrderNo(orderNo)");
        orderRepository.findAllOrderDataByOrderNo(orderNo);

        //상단에 출력할 장바구니 목록
        log.info("orderRepository.findAllOrderDataByOrderNo(orderNo)");
        List<OrderDataDto> datas = orderRepository.findAllOrderDataByOrderNo(orderNo);
        req.setAttribute("datas",datas);
        for (OrderDataDto data : datas) {
            log.info("cartRepository.deleteByOrderNoAndCartId(orderNo,data.getCartId())");
            cartRepository.deleteByOrderNoAndCartId(orderNo,data.getCartId());
        }
        //주문 정보 가져옴
        log.info("orderRepository.findAllOrderInfoByOrderNo(orderNo)");
        OrderInfoDto info = orderRepository.findAllOrderInfoByOrderNo(orderNo);
        log.info("info = {}",info);
        //주문 단계를 한글로
        OrderStep orderStep = OrderStep.valueOf(info.getOrderStep());
        info.setOrderStep(orderStep.getStep());
        req.setAttribute("info",info);
        return new View("/order/done");
    }

}
