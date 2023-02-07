package shop.bookmarket.controller.book;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import shop.bookmarket.frontcontroller.View;
import shop.bookmarket.model.dto.BookDto;
import shop.bookmarket.model.repository.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class BookAddController implements shop.bookmarket.controller.Controller {
    BookRepository bookRepository = BookRepository.getInstance();
    @Override
    public View process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String realPath = req.getServletContext().getRealPath("resources/images");
        File dir = new File(realPath);
        if(!dir.exists()){//dir경로에 디렉터리가 없다면
            dir.mkdirs();//make 해줘라
        }

        String filename = "";
        String encType = "utf-8";//인코딩 타입
        int maxSize = 5 * 1024 * 1024; //최대 업로드될 파일의 크기 5MB

        //파일을 받아야하기 때문에 MultipartRequest라는 클래스를 사용해서 받음
        MultipartRequest multi = new MultipartRequest(req,realPath,maxSize,encType,new DefaultFileRenamePolicy());

        String bookId = multi.getParameter("bookId");
        String name = multi.getParameter("name");
        String unitPrice = multi.getParameter("unitPrice");
        String author = multi.getParameter("author");
        String description = multi.getParameter("description");
        String publisher = multi.getParameter("publisher");
        String category = multi.getParameter("category");
        String unitsInStock = multi.getParameter("unitsInStock");
        String totalPages = multi.getParameter("totalPages");
        String condition = multi.getParameter("condition");
        String releaseDate = multi.getParameter("releaseDate");

        Integer price;

        if(unitPrice.isEmpty()){
            price = 0;
        }else{
            price = Integer.valueOf(unitPrice);
        }
        long stock;
        if(unitsInStock.isEmpty()){
            stock = 0;
        }else{
            stock = Long.valueOf(unitsInStock);
        }
        long totalPages_long;

        if(totalPages.isEmpty()){
            totalPages_long = 0;
        }else{
            totalPages_long = Long.valueOf(totalPages);
        }
        Enumeration files = multi.getFileNames();
        String fname = (String)files.nextElement();
        String fileName = multi.getFilesystemName(fname);

        BookDto newBook = BookDto.builder()
                .bookId(bookId)
                .name(name)
                .unitPrice(price)
                .author(author)
                .description(description)
                .publisher(publisher)
                .category(category)
                .unitsInStock(stock)
                .totalPages(totalPages_long)
                .releaseDate(releaseDate)
                .condition(condition)
                .filename(fileName)
                .build();
        bookRepository.save(newBook);

        return new View("redirect:/books");
    }
}
