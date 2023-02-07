package shop.bookmarket.controller.main;

import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeController implements Controller {
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String greeting = "제품 웹 쇼핑몰!";
        String tagline = "Welcome to Web Market!";
        req.setAttribute("greeting",greeting);
        req.setAttribute("tagline",tagline);
        return new View("/main/welcome");
    }
}
