package shop.bookmarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
    private String orderNo;//주문 번호
    private String memberId;//주문자 아이디
    private String orderName;//주문자 이름
    private String orderTel;//주문자 전화번호
    private String orderEmail;//주문자 이메일

    private String receiveName;//받는이 이름
    private String receiveTel;//받는이 전화번호
    private String receiveAddress;//받는이 주소

    private int payAmount;//지불 금액 합계
    private String payMethod;//결제 방법 선택
    private String carryNo;//운반 번호
    private String orderStep;//주문 단계

    private String dateOrder;//주문 일
    private String datePay;//입금 일
    private String dateCarry;//배송 일
    private String dateDone;//배송 완료 일


}
