package shop.bookmarket.model.repository;

import shop.bookmarket.dbconnection.JdbcConnection;
import shop.bookmarket.model.dto.BookDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final BookRepository INSTANCE = new BookRepository();
    public static BookRepository getInstance() {
        return INSTANCE;
    }
    private BookRepository(){
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

    public BookDto findById(Long id){
        BookDto dto = null;
        String sql = "select * from `book_market`.book where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto = BookDto.builder()
                        .id(rs.getLong("id"))
                        .bookId(rs.getString("book_id"))
                        .name(rs.getString("name"))
                        .unitPrice(rs.getInt("unit_price"))
                        .author(rs.getString("author"))
                        .description(rs.getString("description"))
                        .publisher(rs.getString("publisher"))
                        .category(rs.getString("category"))
                        .unitsInStock(rs.getLong("units_in_stock"))
                        .totalPages(rs.getLong("total_pages"))
                        .releaseDate(rs.getString("release_date"))
                        .condition(rs.getString("condition"))
                        .filename(rs.getString("filename"))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public List<BookDto> findAll(){
        List<BookDto> dtos = new ArrayList<>();
        String sql = "select * from `book_market`.book";
        try{
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BookDto dto = BookDto.builder()
                        .id(rs.getLong("id"))
                        .bookId(rs.getString("book_id"))
                        .name(rs.getString("name"))
                        .unitPrice(rs.getInt("unit_price"))
                        .author(rs.getString("author"))
                        .description(rs.getString("description"))
                        .publisher(rs.getString("publisher"))
                        .category(rs.getString("category"))
                        .unitsInStock(rs.getLong("units_in_stock"))
                        .totalPages(rs.getLong("total_pages"))
                        .releaseDate(rs.getString("release_date"))
                        .condition(rs.getString("condition"))
                        .filename(rs.getString("filename"))
                        .build();
                dtos.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dtos;
    }

    public void save(BookDto book){
        String sql = "insert into book(`book_id`,`name`,`unit_price`,`author`,`description`,`publisher`,`category`," +
                "`units_in_stock`,`total_pages`,`release_date`,`condition`,`filename`) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,book.getBookId());
            pstmt.setString(2,book.getName());
            pstmt.setInt(3,book.getUnitPrice());
            pstmt.setString(4,book.getAuthor());
            pstmt.setString(5,book.getDescription());
            pstmt.setString(6,book.getPublisher());
            pstmt.setString(7,book.getCategory());
            pstmt.setLong(8,book.getUnitsInStock());
            pstmt.setLong(9,book.getTotalPages());
            pstmt.setString(10,book.getReleaseDate());
            pstmt.setString(11,book.getCondition());
            pstmt.setString(12,book.getFilename());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
