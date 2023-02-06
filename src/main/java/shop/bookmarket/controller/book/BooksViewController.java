package shop.bookmarket.controller.book;

import shop.bookmarket.controller.member.frontcontroller.View;
import shop.bookmarket.model.dto.BookDto;
import shop.bookmarket.model.repository.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BooksViewController implements shop.bookmarket.controller.Controller {
    BookRepository bookRepository = BookRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDto> books = bookRepository.findAll();
        req.setAttribute("books",books);
        return new View("/book/books");
    }
}
