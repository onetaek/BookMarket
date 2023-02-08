package shop.bookmarket.controller.order;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.OrderInfoDto;
import shop.bookmarket.model.repository.OrderRepository;
import shop.bookmarket.util.OrderStep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
public class OrderSuccessController implements Controller {
    /**
     * 결제가 정상적으로 끝난 경우 호출
     * 결제 시도 페이지의 paymentData 객체의 successUrl 속성으로 접근
     * 접근시에 orderId, paymentKey, amount 파마미터 만 으로 접근(주의 : 접근 url에는 성공여부등의 정보는 포함되어 있지 않음)
     * 1. 파라미터 정리
     * paymentKey : 결제의 키 값
     * orderId : 주문 ID입니다. 결제창을 열 때 requestPayment()에 담아 보낸 값
     * amount : 실제로 결제된 금액.
     */
    OrderRepository orderRepository = OrderRepository.getInstance();
    @SneakyThrows
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        String paymentKey = req.getParameter("paymentKey");
        String amount = req.getParameter("amount");
        log.info("orderId = {}, paymentKey = {}, amount = {}",orderId,paymentKey,amount);

        String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R" + ":";

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
        String authorizations = "Basic "+ new String(encodedBytes, 0, encodedBytes.length);

        URL url = new URL("https://api.tosspayments.com/v1/payments/" + paymentKey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code >= 200 && code < 300 ? true : false;
        InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();

        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();
        //============토스 샘플코드 끝 아래는 받은 결과에 따라 우리의 프로세스에 맞추어 처리========
        if(isSuccess){
            String method = (String) jsonObject.get("method");
            log.info("method = {}",method);
            OrderInfoDto dto = new OrderInfoDto();
            dto.setOrderNo((String)jsonObject.get("orderId"));
            dto.setPayMethod(method);

            if(method.equals("가상계좌")){
                dto.setOrderStep(String.valueOf(OrderStep.orderReceive));//주문 접수
            }else {
                dto.setOrderStep(String.valueOf(OrderStep.payReceive));//입금 확인
                dto.setDatePay((String)jsonObject.get("approvedAt"));
            }

            //가상계좌가 아닐경우 받은 값으로 변경해줘야함(2개의 값 변경, method,orderStep)
            log.info("orderRepository.updateMethodOrderStopOrderNoByOrderNo(dto)");
            orderRepository.updateMethodOrderStopOrderNoByOrderNo(dto);
        }



        //================================토스 작업 끝======================================

        return new View("redirect:/order/done");
    }
}
