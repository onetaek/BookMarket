package shop.bookmarket.controller.cart;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.dto.CartDto;
import shop.bookmarket.model.repository.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Slf4j
public class CartViewController implements shop.bookmarket.controller.Controller {
    CartRepository cartRepository = CartRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNo = req.getSession().getId();
        List<CartDto> findCart = cartRepository.findByOrderNo(orderNo);
        req.setAttribute("carts",findCart);
        return new View("/cart/cart");
    }
}
