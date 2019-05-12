package pl.coderslab.service;

import pl.coderslab.entity.Book;

import java.util.List;

public interface BookService {
    void deleteBook(long id);

    void updateBook(long id, Book book);

    void addBook(Book book);

    Book getBook(long id);

    List<Book> getList();

    void setList(List<Book> list);
}
