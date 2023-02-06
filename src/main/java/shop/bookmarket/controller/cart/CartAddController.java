package shop.bookmarket.controller.cart;

import lombok.extern.slf4j.Slf4j;
import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.dto.BookDto;
import shop.bookmarket.model.dto.CartDto;
import shop.bookmarket.model.repository.BookRepository;
import shop.bookmarket.model.repository.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class CartAddController implements shop.bookmarket.controller.Controller {
    BookRepository bookRepository = BookRepository.getInstance();
    CartRepository cartRepository = CartRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("id");
        log.info("bookId = {}",bookId);
        BookDto findBook = bookRepository.findById(Long.valueOf(bookId));


        //책이 없을 경우 예외
        if(findBook == null){
            return new View("redirect:/error/exceptionNoBookId");
        }
        //정상 로직
        Long memberId = Long.valueOf((String)req.getSession().getAttribute("sessionId"));

        String orderNo = req.getSession().getId();
        log.info("orderNo = {}, memberId = {}",orderNo, memberId);
        CartDto findCart = cartRepository.findByOrderNoAndMemberId(orderNo,Long.valueOf(bookId));
        System.out.println("findCart = " + findCart);
        if(findCart == null){//cart가 비어있다면 추가하고 cnt = 1
            cartRepository.save(CartDto.createCart(findBook, memberId, orderNo));
        }else{//cart가 존재한다면 cnt = cnt + 1
            cartRepository.update(findCart.getId());
        }
        return new View("redirect:/book?id="+bookId);
    }
}
