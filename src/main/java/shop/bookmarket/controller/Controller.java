package shop.bookmarket.controller;

import shop.bookmarket.controller.member.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
