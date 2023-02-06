package shop.bookmarket.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class CartDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private String orderNo;
    private String name;
    private int unitPrice;
    private int cnt;
    private Date insertDate;

    public static CartDto createCart(BookDto book, Long memberId, String orderNo){
        return CartDto.builder()
                .memberId(memberId)
                .bookId(book.getId())
                .orderNo(orderNo)
                .name(book.getName())
                .unitPrice(book.getUnitPrice())
                .cnt(1)
                .build();
    }
}
