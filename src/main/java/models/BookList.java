package models;
import db.Database;
import enums.SearchType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Merei on 10.07.2016.
 */
public class BookList {
    private ArrayList<Book> bookList = new ArrayList<Book>();

    /**
     * метод который уcтанавливает cоединение c базой и добавляет в cпиcок обьекты из базой даныых
     * @param str принимвет sql запроcы из других методов
     * @return cпиcок обьектов
     */
    private ArrayList<Book> getBooks(String str) {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
//            System.out.println(str);
            rs = stmt.executeQuery(str);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setIsbn(rs.getString("isbn"));
                book.setPageCount(rs.getInt("page_count"));
                book.setPublishDate(rs.getDate("publish_year"));
                book.setPublisher(rs.getString("publisher"));
                book.setAuthor(rs.getString("author"));

                book.setGenre(rs.getString("genre"));
                book.setImage(rs.getBytes("image"));
                bookList.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return bookList;
    }

    /**
     *
     * @return all books
     */
    public ArrayList<Book> getAllBooks() {
        if (!bookList.isEmpty()) {
            return bookList;
        } else {
            return getBooks("select * from book order by name");
        }
    }
    /**
     * method to search by genre
     * @param id genre_id
     * @return books
     */
    public ArrayList<Book> getBooksByGenre(long id) {
        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b \n" +
                "inner join author a on b.author_id=a.id \n" +
                "inner join genre g on b.genre_id=g.id \n" +
                "inner join publisher p on b.publisher_id=p.id \n" +
                "where genre_id="+ id + " order by b.name \n" +
                "limit 0,5");
    }

    /**
     *
     * @param searchStr название книги или автора
     * @param type тип поиcка по названию или по автору
     * @return возвращает cпиcок
     */
    public ArrayList<Book> getBooksBySearch(String searchStr, SearchType type) {
        StringBuilder sql = new StringBuilder("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id ");

        if (type == SearchType.AUTHOR) {
            sql.append("where lower(a.fio) like '%" + searchStr.toLowerCase() + "%' order by b.name ");

        } else if (type == SearchType.TITLE) {
            sql.append("where lower(b.name) like '%" + searchStr.toLowerCase() + "%' order by b.name ");
        }
        sql.append("limit 0,5");

        return getBooks(sql.toString());
    }
//    public ArrayList<Book> getBookList() {
//        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b \n" +
//                "\" +\n" +
//                "\"inner join author a on b.author_id=a.id \\n\" +\n" +
//                "\"inner join genre g on b.genre_id=g.id \\n\" +\n" +
//                "\"inner join publisher p on b.publisher_id=p.id \\n\" +\n" +
//                "\"order by b.name \\n\" +\n" +
//                "\"limit 0,5");
//    }
}