package shop.bookmarket.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDataDto {
    private Long id;
    private String orderNo;
    private Long cartId;
    private String productId;
    private String productName;
    private int unitPrice;
    private int cnt;
    private int sumPrice;//cart의 unitPrice와 cnt를 더한것
}
