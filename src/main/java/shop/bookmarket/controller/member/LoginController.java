package shop.bookmarket.controller.member;

import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.MemberDto;
import shop.bookmarket.model.repository.MemberRepository;
import shop.bookmarket.util.Error;
import shop.bookmarket.util.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements Controller {
    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        MemberDto member = memberRepository.findMemberByUserIdAndPassword(id, password);

        if(member != null){
            return new View("redirect:/member/resultMember?msg="+ Message.MEMBER_LOGIN+"&id="+member.getId());
        }else {
            return new View("redirect:/member/login?error="+ Error.LOGIN_FAILURE);
        }
    }
}
