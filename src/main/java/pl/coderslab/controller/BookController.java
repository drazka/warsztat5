package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.service.BookService;
import pl.coderslab.service.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addBook(@RequestBody Book book) {
        System.out.println(book.toString());
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Book getBook(@PathVariable(required = true) long id) {
        return bookService.getBook(id);
    }

    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Book> getBooks() {
        return bookService.getList();
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book(1L,"9788324631766","Thinking in Java",
                "Bruce Eckel","Helion","programming");
    }

    @RequestMapping("/hello")
    @ResponseStatus(value = HttpStatus.OK)
    public String hello() {
        return "{hello: World}";
    }
}
