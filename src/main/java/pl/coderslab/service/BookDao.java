package pl.coderslab.service;

import pl.coderslab.entity.Book;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private static final String FIND_ALL_BOOKS_QUERY = "SELECT * FROM Book";
    private static final String READ_BOOK_QUERY = "SELECT * from Book where id = ?";
    private static final String DELETE_BOOK_QUERY = "DELETE FROM Book where id = ?";


    public static List<Book> getBooksListDb() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BOOKS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Book bookToAdd = new Book();
                bookToAdd.setId(resultSet.getInt("id"));
                bookToAdd.setIsbn(resultSet.getString("isbn"));
                bookToAdd.setTitle(resultSet.getString("title"));
                bookToAdd.setAuthor(resultSet.getString("author"));
                bookToAdd.setIsbn(resultSet.getString("publisher"));
                bookToAdd.setIsbn(resultSet.getString("type"));
                bookList.add(bookToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;

    }

    public static Book getBookByIdDb(Integer bookId) {
        Book book = new Book();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_BOOK_QUERY);
        ) {
            statement.setInt(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    book.setId(resultSet.getInt("id"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setIsbn(resultSet.getString("publisher"));
                    book.setIsbn(resultSet.getString("type"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;

    }

    public static void deleteBookByIdDb(Integer bookId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_QUERY);) {
            statement.setInt(1, bookId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

