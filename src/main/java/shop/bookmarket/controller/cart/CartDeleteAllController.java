package shop.bookmarket.controller.cart;

import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.repository.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartDeleteAllController implements shop.bookmarket.controller.Controller {
    CartRepository cartRepository = CartRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNo = req.getSession().getId();
        cartRepository.deleteByOrderNo(orderNo);
        return new View("redirect:/cart");
    }
}
