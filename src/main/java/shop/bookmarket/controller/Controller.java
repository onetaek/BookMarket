package shop.bookmarket.controller;

import org.json.simple.parser.ParseException;
import shop.bookmarket.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException;
}
