package shop.bookmarket.model.repository;

import jdk.internal.net.http.common.Log;
import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.dbconnection.JdbcConnection;
import shop.bookmarket.model.dto.CartDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class CartRepository {
    private static final CartRepository INSTANCE = new CartRepository();
    public static CartRepository getInstance() {
        return INSTANCE;
    }
    private CartRepository(){
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

    public CartDto findByOrderNoAndMemberId(String orderNo, Long bookId){
        CartDto dto = null;
        String sql ="select * from `book_market`.cart where order_no = ? and book_id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,orderNo);
            pstmt.setLong(2,bookId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = CartDto.builder()
                        .id(rs.getLong("id"))
                        .memberId(rs.getLong("member_id"))
                        .bookId(rs.getLong("book_id"))
                        .orderNo(rs.getString("order_no"))
                        .name(rs.getString("name"))
                        .unitPrice(rs.getInt("unit_price"))
                        .cnt(rs.getInt("cnt"))
                        .insertDate(rs.getDate("insert_date"))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public void save(CartDto cart){
        String sql = "insert into `book_market`.cart (`member_id`,`book_id`,`order_no`," +
                "`name`,`unit_price`,`cnt`,`insert_date`)" +
                " values (?,?,?,?,?,?,now())";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,cart.getMemberId());
            pstmt.setLong(2,cart.getBookId());
            pstmt.setString(3,cart.getOrderNo());
            pstmt.setString(4,cart.getName());
            pstmt.setInt(5,cart.getUnitPrice());
            pstmt.setInt(6, cart.getCnt());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Long cartId){
        String sql = "update `book_market`.cart set cnt = cnt + 1 where id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,cartId);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<CartDto> findByOrderNo(String orderNo){
        log.info("orderNo = {}",orderNo);
        List<CartDto> dtos = new ArrayList<>();
        String sql = "select * from `book_market`.cart where order_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                CartDto dto = CartDto.builder()
                        .id(rs.getLong("id"))
                        .memberId(rs.getLong("member_id"))
                        .bookId(rs.getLong("book_id"))
                        .orderNo(rs.getString("order_no"))
                        .name(rs.getString("name"))
                        .unitPrice(rs.getInt("unit_price"))
                        .cnt(rs.getInt("cnt"))
                        .insertDate(rs.getDate("insert_date"))
                        .build();
                dtos.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public void updateOrderNoByMemberId(String orderNo,Long memberId) {
        String sql = "update `book_market`.cart set order_no = ? where member_id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            pstmt.setLong(2, memberId);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteByBookId(Long bookId) {
        String sql = "delete from `book_market`.cart where book_id = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,bookId);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteByOrderNo(String orderNo) {
        log.info("orderNo = {}",orderNo);
        String sql = "delete from `book_market`.cart where order_no = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderNo);
            pstmt.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
