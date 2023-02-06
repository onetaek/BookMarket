package shop.bookmarket.model.dto;

import lombok.*;


@Data
@Builder
public class MemberDto {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String gender;
    private String birth;
    private String mail;
    private String phone;
    private String address;
    private String registDay;
}
