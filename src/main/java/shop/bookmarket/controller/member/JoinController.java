package shop.bookmarket.controller.member;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.MemberDto;
import shop.bookmarket.model.repository.MemberRepository;
import shop.bookmarket.util.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class JoinController implements Controller {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDto member = MemberDto.builder()
                .userId(req.getParameter("id"))
                .password(req.getParameter("password"))
                .name(req.getParameter("name"))
                .gender(req.getParameter("gender"))
                .birth(createBirth(req))
                .mail(createMail(req))
                .phone(req.getParameter("phone"))
                .address(req.getParameter("address"))
                .build();
        memberRepository.save(member);
        log.info("회원가입 성공");
        return new View("redirect:/member/resultMember?msg="+ Message.MEMBER_JOIN);
    }

    private static String createMail(HttpServletRequest req) {
        String mail1 = req.getParameter("mail1");
        String mail2 = req.getParameter("mail2");
        return mail1 + "@" + mail2;
    }

    private String createBirth(HttpServletRequest req) {
        String month = req.getParameterValues("birthmm")[0];
        String day = req.getParameter("birthdd");
        return req.getParameter("birthyy") + "/" + month + "/" + day;
    }


}
