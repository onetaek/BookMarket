package shop.bookmarket.controller.member;

import shop.bookmarket.controller.Controller;
import shop.bookmarket.controller.member.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginViewController implements Controller {
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        req.setAttribute("error",error);
        return new View("/member/login");
    }
}
