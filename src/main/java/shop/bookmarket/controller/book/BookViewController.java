package shop.bookmarket.controller.book;

import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.BookDto;
import shop.bookmarket.model.repository.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookViewController implements shop.bookmarket.controller.Controller {
    BookRepository bookRepository = BookRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null){
            //오류 페이지로 이동 후 현제는 없는 책이라고 안내문
            return null;
        }else{
            BookDto findBook = bookRepository.findById(Long.parseLong(id));
            req.setAttribute("book",findBook);
            return new View("/book/book");
        }
    }
}
