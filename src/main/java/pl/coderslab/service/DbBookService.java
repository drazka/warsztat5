package pl.coderslab.service;

import pl.coderslab.entity.Book;

import java.util.List;

public class DbBookService implements BookService {

    public List<Book> getList() {
        List<Book> list = BookDao.getBooksListDb();
        return list;
    };

    public Book getBook(long id) {
        int id2 = Math.toIntExact(id);
        Book book = BookDao.getBookByIdDb(id2);
        return book;
    }

    public void deleteBook(long id) {
        int id2 = Math.toIntExact(id);
        BookDao.deleteBookByIdDb(id2);
    }

    @Override
    public void updateBook(long id, Book book) {

    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void setList(List<Book> list) {

    }
}
