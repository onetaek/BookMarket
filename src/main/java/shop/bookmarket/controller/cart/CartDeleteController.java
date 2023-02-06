package shop.bookmarket.controller.cart;

import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.repository.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartDeleteController implements shop.bookmarket.controller.Controller {
    CartRepository cartRepository = CartRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        cartRepository.deleteByBookId(Long.parseLong(bookId));
        return new View("redirect:/cart");
    }
}
