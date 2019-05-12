package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MemoryBookService implements BookService {
    private List<Book> list;

    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    public void deleteBook(long id) {
        list.stream()
                .filter(n -> n.getId() == id)
                .findFirst().ifPresent(n -> list.remove(n));
    }

    public void updateBook(long id, Book book) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.set(i, book);
                break;
            }
        }
    }

    public void addBook(Book book) {
        book.setId(getNext());
        list.add(book);
    }

    public Book getBook(long id) {
        return list.stream()
                .filter(n -> n.getId() == id)
                .findFirst().orElse(null);
    }

    private long getNext() {
        return list.stream()
                .map(n -> n.getId())
                .max(Comparator.comparing(Long::valueOf))
                .orElse(0L) + 1;
    }
}
