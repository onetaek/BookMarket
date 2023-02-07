package shop.bookmarket.controller.member;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.Controller;
import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.MemberDto;
import shop.bookmarket.model.repository.CartRepository;
import shop.bookmarket.model.repository.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Slf4j
public class resultMemberViewController implements Controller {
    MemberRepository memberRepository = MemberRepository.getInstance();
    CartRepository cartRepository = CartRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("msg");
        String id = req.getParameter("id");
        String name = null;
        if(id != null){
            MemberDto findMember = memberRepository.findById(Long.parseLong(id));
            HttpSession session = req.getSession();
            cartRepository.updateOrderNoByMemberId(session.getId(),findMember.getId());
            session.setAttribute("sessionId",id);
            session.setAttribute("sessionName",findMember.getName());
        }
        log.info("msg = {}",msg);
        req.setAttribute("msg",msg);
        return new View("/member/resultMember");
    }
}
