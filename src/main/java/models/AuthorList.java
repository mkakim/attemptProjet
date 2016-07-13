package models;
import db.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Merei on 05.07.2016.
 */
public class AuthorList {
    private ArrayList<Author> authorList = new ArrayList<Author>();
    private ArrayList<Author> getAuthors() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Database.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM library.author ORDER BY fio");
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getLong("id"));
                author.setName(rs.getString("fio"));
                authorList.add(author);
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            try {
                if (rs != null){rs.close();}
                if (stmt != null){stmt.close();}
                if (conn != null){conn.close();}
            } catch (SQLException e) {
                Logger.getLogger(AuthorList.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return authorList;
    }
    public ArrayList<Author> getAuthorList() {
        if (!authorList.isEmpty()) {
            return authorList;
        }else {
            return getAuthors();
        }
    }
}
