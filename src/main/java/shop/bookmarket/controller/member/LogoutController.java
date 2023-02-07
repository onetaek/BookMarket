package shop.bookmarket.controller.member;

import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController implements Controller {
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        return new View("redirect:/member/login");
    }
}
