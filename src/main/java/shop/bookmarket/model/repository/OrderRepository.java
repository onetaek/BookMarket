package shop.bookmarket.model.repository;


import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.dbconnection.JdbcConnection;
import shop.bookmarket.model.dto.OrderDataDto;
import shop.bookmarket.model.dto.OrderInfoDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderRepository {
    private static final OrderRepository INSTANCE = new OrderRepository();
    public static OrderRepository getInstance() {
        return INSTANCE;
    }
    private OrderRepository(){
        connect();
    }
    PreparedStatement pstmt = null;
    Connection conn = null;
    ResultSet rs = null;
    private void connect(){
        try{
            conn = JdbcConnection.getConnection();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List<OrderDataDto> findAllByOrderNo(String orderNo) {
        List<OrderDataDto> dtos = new ArrayList<>();
        String sql = "select * from `book_market`.order_data where order_no = ?";
        try{
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1,orderNo);
               rs = pstmt.executeQuery();
               while(rs.next()){
                   OrderDataDto dto = OrderDataDto.builder()
                           .id(rs.getLong("id"))
                           .orderNo(rs.getString("order_no"))
                           .cartId(rs.getLong("cart_id"))
                           .productId(rs.getString("book_id"))
                           .productName(rs.getString("book_name"))
                           .unitPrice(rs.getInt("unit_price"))
                           .cnt(rs.getInt("cnt"))
                           .sumPrice(rs.getInt("sum_price"))
                           .build();
                   dtos.add(dto);
               }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public void deleteByOrderNo(String orderNo) {
        String sql = "delete from `book_market`.order_info where order_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderNo);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save(OrderDataDto orderData) {
        String sql = "insert into `book_market`.order_data (order_no,cart_id,book_id,book_name," +
                "unit_price,cnt,sum_price) values (?,?,?,?,?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderData.getOrderNo());
            pstmt.setLong(2,orderData.getCartId());
            pstmt.setString(3,orderData.getProductId());
            pstmt.setString(4,orderData.getProductName());
            pstmt.setInt(5,orderData.getUnitPrice());
            pstmt.setInt(6,orderData.getCnt());
            pstmt.setInt(7,orderData.getSumPrice());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getTotalPriceByOrderNo(String orderNo) {
        int total = 0;
        String sql = "select sum(`sum_price`) as 'total' from `book_market`.order_data where `order_no` = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            rs = pstmt.executeQuery();
            if(rs.next()){
                total = rs.getInt("total");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return total;
    }


    public void deleteOrderInfoByOrderNo(String orderNo) {
        String sql = "delete from `book_market`.order_info where order_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveOrderInfo(OrderInfoDto order) {
        //중복 제거 삭제가 싫을 경우 읽어온 값이 존재하면 update아니면 그냥 insert로 쿼리 작성 가능하다.
        int status = 0;
        String sql = "insert into `book_market`.`order_info` (order_no,member_id,order_name," +
                "order_tel,order_email,receive_name,receive_tel,receive_address,pay_amount,pay_method," +
                "carry_no,order_step,date_order,date_pay,date_carry,date_done) " +
                " values (?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?, now(),?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderNo());
            pstmt.setString(2, order.getMemberId());
            pstmt.setString(3, order.getOrderName());
            pstmt.setString(4, order.getOrderTel());
            pstmt.setString(5, order.getOrderEmail());
            pstmt.setString(6, order.getReceiveName());
            pstmt.setString(7, order.getReceiveTel());
            pstmt.setString(8, order.getReceiveAddress());
            pstmt.setInt(9, order.getPayAmount());
            pstmt.setString(10, order.getPayMethod());
            pstmt.setString(11, order.getCarryNo());
            pstmt.setString(12, "orderFail");
            pstmt.setDate(13, (Date) order.getDatePay());
            pstmt.setDate(14, (Date) order.getDateCarry());
            pstmt.setDate(15, (Date) order.getDateDone());
            pstmt.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getOrderProductNameByOrderNo(String orderNo) {
        String orderProductName = null;
        int orderProductCnt = 0;
        String sql = "select * from `book_market`.order_data where order_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderNo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (orderProductCnt == 0) {
                    orderProductName = rs.getString("book_name");
                }
                orderProductCnt++;
                log.info("orderProductCnt = {}",orderProductCnt);

            }
            orderProductName += " 외 " + (orderProductCnt - 1) + " 건";
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderProductName;
    }

    public OrderInfoDto findAllOrderInfoByOrderNo(String orderNo) {
        OrderInfoDto dto = null;
        String sql = "select * from `book_market`.order_info where order_no = ?";
        try{
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,orderNo);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = OrderInfoDto.builder()
                        .orderNo(rs.getString("order_no"))
                        .memberId(rs.getString("member_id"))
                        .orderName(rs.getString("order_name"))
                        .orderTel(rs.getString("order_tel"))
                        .orderEmail(rs.getString("order_email"))
                        .receiveName(rs.getString("receive_name"))
                        .receiveTel(rs.getString("receive_tel"))
                        .receiveAddress(rs.getString("receive_address"))
                        .payAmount(rs.getInt("pay_amount"))
                        .payMethod(rs.getString("pay_method"))
                        .carryNo(rs.getString("carry_no"))
                        .orderStep(rs.getString("order_step"))
                        .dateOrder(rs.getDate("date_order"))
                        .datePay(rs.getDate("date_pay"))
                        .dateCarry(rs.getDate("date_carry"))
                        .dateDone(rs.getDate("date_done"))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }
}
