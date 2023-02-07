package shop.bookmarket.controller.member;

import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinViewController implements Controller {
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return new View("/member/join");
    }
}
