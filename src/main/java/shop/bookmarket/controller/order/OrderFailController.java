package shop.bookmarket.controller.order;

import org.json.simple.JSONObject;
import shop.bookmarket.frontcontroller.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Encoder;

public class OrderFailController implements shop.bookmarket.controller.Controller {
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return null;
    }
}
