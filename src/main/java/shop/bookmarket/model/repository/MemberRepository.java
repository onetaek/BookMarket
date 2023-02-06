package shop.bookmarket.model.repository;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.dbconnection.JdbcConnection;
import shop.bookmarket.model.dto.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Slf4j
public class MemberRepository {
    private static final MemberRepository INSTANCE = new MemberRepository();
    public static MemberRepository getInstance() {
        return INSTANCE;
    }
    private MemberRepository(){
        connect();
    }
    PreparedStatement pstmt = null;
    Connection conn = null;
    ResultSet rs = null;
    private void connect(){
        try{
            conn = JdbcConnection.getConnection();
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public MemberDto findMemberByUserIdAndPassword(String userId, String password){
        MemberDto dto = null;
        String sql = "select * from `book_market`.member where user_id = ? and password = ?";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = MemberDto.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getString("user_id"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .gender(rs.getString("gender"))
                        .birth(rs.getString("birth"))
                        .mail(rs.getString("mail"))
                        .phone(rs.getString("phone"))
                        .address(rs.getString("address"))
                        .registDay(rs.getString("regist_day"))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public void save(MemberDto member) {
        String sql = "insert into `book_market`.member " +
                "(user_id,password,name,gender,birth,mail,phone,address,regist_day) " +
                "values (?,?,?,?,?,?,?,?,now())";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,member.getUserId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3,member.getName());
            pstmt.setString(4, member.getGender());
            pstmt.setString(5, member.getBirth());
            pstmt.setString(6, member.getMail());
            pstmt.setString(7, member.getPhone());
            pstmt.setString(8, member.getAddress());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public MemberDto findById(Long id){
        String sql = "select * from `book_market`.`member` where `id` = ?";
        MemberDto dto = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                dto = MemberDto.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getString("user_id"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .gender(rs.getString("gender"))
                        .birth(rs.getString("birth"))
                        .mail(rs.getString("mail"))
                        .phone(rs.getString("phone"))
                        .address(rs.getString("address"))
                        .registDay(rs.getString("regist_day"))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }
}
