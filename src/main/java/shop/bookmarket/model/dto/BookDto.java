package shop.bookmarket.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private Long id;
    private String bookId;
    private String name;
    private Integer unitPrice;
    private String author;
    private String description;
    private String publisher;
    private String category;
    private long unitsInStock;
    private long totalPages;
    private String releaseDate;
    private String condition;
    private String filename; //이미지 파일

}
