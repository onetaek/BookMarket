package shop.bookmarket.controller.book;

import shop.bookmarket.controller.member.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookAddViewController implements shop.bookmarket.controller.Controller {
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return new View("/book/addBook");
    }
}
