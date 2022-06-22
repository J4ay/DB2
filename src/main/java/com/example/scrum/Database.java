package com.example.scrum;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    String url;

    public Database() {
        url = "jdbc:sqlserver://itnt0005:1433;databaseName=SWB_DB2_Projekt;user=swb4;password=swb4;encrypt=true;trustServerCertificate=true";
    }

    public ArrayList<Book> getBooksByName (String search_title) {           //returns up to 20 results as a String array containing the results matching the search words
        ArrayList<Book> books = new ArrayList<Book>();
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Imort_Library WHERE title LIKE '%" + search_title + "%';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);            //all rows with fitting ISBN are stored in the ResultSet
            while(rs.next()) {              //for every row of the ResultSet => for every fetched row from the database
                Book book = new Book();
                book.setIsbn(Integer.parseInt(rs.getString("isbn")));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(Integer.parseInt(rs.getString("year")));
                book.setEdition(rs.getString("edition"));
                book.setPublisher(rs.getString("publisher"));
                book.setNumber_of_books(Integer.parseInt(rs.getString("number_of_books")));
                book.setNumber_borrowed_books(Integer.parseInt(rs.getString("number_borrowed_books")));
                book.setNumber_ratings(Integer.parseInt(rs.getString("number_ratings")));
                book.setAverage_rating(Float.parseFloat(rs.getString("average_rating")));
                book.setRating_0(Integer.parseInt(rs.getString("rating_0")));
                book.setRating_1(Integer.parseInt(rs.getString("rating_1")));
                book.setRating_2(Integer.parseInt(rs.getString("rating_2")));
                book.setRating_3(Integer.parseInt(rs.getString("rating_3")));
                book.setRating_4(Integer.parseInt(rs.getString("rating_4")));
                book.setRating_5(Integer.parseInt(rs.getString("rating_5")));
                books.add(book);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        HelloApplication.last_search = books;
        HelloApplication.last_search_text = search_title;
        HelloApplication.last_search_isbn = 0;
        return books;
    }

    public Book getBookByIsbn (int isbn) {             //returns the book to the searched isbn with every information in the same line
        Book book = new Book();
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Imort_Library WHERE isbn='" + isbn + "';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);            //all rows with fitting ISBN are stored in the ResultSet
            if(rs.next()) {
                book.setIsbn(Integer.parseInt(rs.getString("isbn")));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(Integer.parseInt(rs.getString("year")));
                book.setEdition(rs.getString("edition"));
                book.setPublisher(rs.getString("publisher"));
                book.setNumber_of_books(Integer.parseInt(rs.getString("number_of_books")));
                book.setNumber_borrowed_books(Integer.parseInt(rs.getString("number_borrowed_books")));
                book.setNumber_ratings(Integer.parseInt(rs.getString("number_ratings")));
                book.setAverage_rating(Float.parseFloat(rs.getString("average_rating")));
                book.setRating_0(Integer.parseInt(rs.getString("rating_0")));
                book.setRating_1(Integer.parseInt(rs.getString("rating_1")));
                book.setRating_2(Integer.parseInt(rs.getString("rating_2")));
                book.setRating_3(Integer.parseInt(rs.getString("rating_3")));
                book.setRating_4(Integer.parseInt(rs.getString("rating_4")));
                book.setRating_5(Integer.parseInt(rs.getString("rating_5")));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return book;
    }

    public ArrayList<Book> getBooksByIsbn (int isbn) {           //returns up to 20 results as a String array containing the results matching the search words
        ArrayList<Book> books = new ArrayList<Book>();
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM Imort_Library WHERE isbn LIKE '" + isbn + "%';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);            //all rows with fitting ISBN are stored in the ResultSet
            while(rs.next()) {              //for every row of the ResultSet => for every fetched row from the database
                Book book = new Book();
                book.setIsbn(Integer.parseInt(rs.getString("isbn")));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(Integer.parseInt(rs.getString("year")));
                book.setEdition(rs.getString("edition"));
                book.setPublisher(rs.getString("publisher"));
                book.setNumber_of_books(Integer.parseInt(rs.getString("number_of_books")));
                book.setNumber_borrowed_books(Integer.parseInt(rs.getString("number_borrowed_books")));
                book.setNumber_ratings(Integer.parseInt(rs.getString("number_ratings")));
                book.setAverage_rating(Float.parseFloat(rs.getString("average_rating")));
                book.setRating_0(Integer.parseInt(rs.getString("rating_0")));
                book.setRating_1(Integer.parseInt(rs.getString("rating_1")));
                book.setRating_2(Integer.parseInt(rs.getString("rating_2")));
                book.setRating_3(Integer.parseInt(rs.getString("rating_3")));
                book.setRating_4(Integer.parseInt(rs.getString("rating_4")));
                book.setRating_5(Integer.parseInt(rs.getString("rating_5")));
                books.add(book);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        HelloApplication.last_search = books;
        HelloApplication.last_search_isbn = isbn;
        HelloApplication.last_search_text = null;
        return books;
    }

    public void borrowBook() {              //updates number_borrowed_books in the database and in the current_book
        int isbn = HelloApplication.current_book.getIsbn();
        int borrowedBooks = HelloApplication.current_book.getNumber_borrowed_books();

        borrowedBooks = borrowedBooks + 1;

        HelloApplication.current_book.setNumber_borrowed_books(borrowedBooks);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET number_borrowed_books = " + borrowedBooks + " WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void returnBook() {              //updates number_borrowed_books in the database and in the current_book
        int isbn = HelloApplication.current_book.getIsbn();
        int borrowedBooks = HelloApplication.current_book.getNumber_borrowed_books();

        borrowedBooks = borrowedBooks - 1;

        HelloApplication.current_book.setNumber_borrowed_books(borrowedBooks);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET number_borrowed_books = " + borrowedBooks + " WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void rateBook(int rating) {              //updates number_borrowed_books in the database and in the current_book
        int isbn = HelloApplication.current_book.getIsbn();

        try (Connection connection = DriverManager.getConnection(url)) {
            String query;
            Statement stmt = connection.createStatement();
            int rating_0 = HelloApplication.current_book.getRating_0();
            int rating_1 = HelloApplication.current_book.getRating_1();
            int rating_2 = HelloApplication.current_book.getRating_2();
            int rating_3 = HelloApplication.current_book.getRating_3();
            int rating_4 = HelloApplication.current_book.getRating_4();
            int rating_5 = HelloApplication.current_book.getRating_5();

            switch (rating) {
                case 0:
                    rating_0++;
                    HelloApplication.current_book.setRating_0(rating_0);
                    query = "UPDATE Imort_Library SET rating_0 = " + rating_0 + " WHERE isbn = " + isbn + ";";
                    stmt.execute(query);
                    break;
                case 1:
                    rating_1++;
                    HelloApplication.current_book.setRating_1(rating_1);
                    query = "UPDATE Imort_Library SET rating_1 = " + rating_1 + " WHERE isbn = " + isbn + ";";
                    stmt.execute(query);
                    break;
                case 2:
                    rating_2++;
                    HelloApplication.current_book.setRating_2(rating_2);
                    query = "UPDATE Imort_Library SET rating_2 = " + rating_2 + " WHERE isbn = " + isbn + ";";
                    stmt.execute(query);
                    break;
                case 3:
                    rating_3++;
                    HelloApplication.current_book.setRating_3(rating_3);
                    query = "UPDATE Imort_Library SET rating_3 = " + rating_3 + " WHERE isbn = " + isbn + ";";
                    stmt.execute(query);
                    break;
                case 4:
                    rating_4++;
                    HelloApplication.current_book.setRating_4(rating_4);
                    query = "UPDATE Imort_Library SET rating_4 = " + rating_4 + " WHERE isbn = " + isbn + ";";
                    stmt.execute(query);
                    break;
                case 5:
                    rating_5++;
                    HelloApplication.current_book.setRating_5(rating_5);
                    query = "UPDATE Imort_Library SET rating_5 = " + rating_5 + " WHERE isbn = " + isbn + ";";
                    stmt.execute(query);
                    break;
            }
            updateRatingAverageAndTotalRating();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void addBook(int isbn, String title, String author, int year, String edition, String publisher, int number) {
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "INSERT INTO `dbo.Imort_Library`(`isbn`,`title`,`author`,`year`,`edition`,`publisher`," +
                    "`number_of_books`,`number_borrowed_books`,`number_ratings`,`average_rating`,`rating_0`," +
                    "`rating_1`,`rating_2`,`rating_3`,`rating_4`,`rating_5`) VALUES (" + isbn + ",'" + title + "','" +
                    author + "'," + year + ",'" + edition + "','" + publisher + "'," + number + ", 0, 0, 0, 0, 0, 0, 0, 0, 0);";

            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void deleteBook() {
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "DELETE FROM Imort_Library WHERE isbn=" + HelloApplication.current_book.getIsbn() + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private void updateRatingAverageAndTotalRating() {
        int number_ratings = HelloApplication.current_book.getNumber_ratings();
        int isbn = HelloApplication.current_book.getIsbn();

        number_ratings = number_ratings + 1;

        HelloApplication.current_book.setNumber_ratings(number_ratings);

        int rating_1 = HelloApplication.current_book.getRating_1();
        int rating_2 = HelloApplication.current_book.getRating_2();
        int rating_3 = HelloApplication.current_book.getRating_3();
        int rating_4 = HelloApplication.current_book.getRating_4();
        int rating_5 = HelloApplication.current_book.getRating_5();

        float average_rating = HelloApplication.current_book.getAverage_rating();


        try (Connection connection = DriverManager.getConnection(url)) {
            String query;
            Statement stmt = connection.createStatement();

            query = "UPDATE Imort_Library SET number_ratings = " + number_ratings + " WHERE isbn = " + isbn + ";";
            stmt.execute(query);
            average_rating = (float) (rating_1 + rating_2 * 2 + rating_3 * 3 + rating_4 * 4 + rating_5 * 5) / number_ratings;

            // ********** rounding the average rating to 2 decimals **********

            int pow = 10;
            for (int i = 1; i < 2; i++)
                pow *= 10;
            float tmp = average_rating * pow;
            average_rating = ( (float) ( (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) ) ) / pow;

            HelloApplication.current_book.setAverage_rating(average_rating);
            query = "UPDATE Imort_Library SET average_rating = " + average_rating + " WHERE isbn = " + isbn + ";";
            stmt.execute(query);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    // Functions to edit single values of a book

    public void updateIsbn (int isbn) {
        int isbn_old = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setIsbn(isbn);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET isbn = " + isbn + " WHERE isbn = " + isbn_old + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateTitle (String title) {
        int isbn = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setTitle(title);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET title = '" + title + "' WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateAuthor (String author) {
        int isbn = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setAuthor(author);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET author = '" + author + "' WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateYear (int year) {
        int isbn = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setYear(year);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET year = " + year + " WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateEdition (String edition) {
        int isbn = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setEdition(edition);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET edition = '" + edition + "' WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updatePublisher (String publisher) {
        int isbn = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setPublisher(publisher);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET publisher = '" + publisher + "' WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void updateNumber (int number) {
        int isbn = HelloApplication.current_book.getIsbn();
        HelloApplication.current_book.setNumber_of_books(number);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET number_of_books = " + number + " WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void resetRatings() {
        int isbn = HelloApplication.current_book.getIsbn();

        HelloApplication.current_book.setNumber_ratings(0);
        HelloApplication.current_book.setAverage_rating(0);
        HelloApplication.current_book.setRating_0(0);
        HelloApplication.current_book.setRating_1(0);
        HelloApplication.current_book.setRating_2(0);
        HelloApplication.current_book.setRating_3(0);
        HelloApplication.current_book.setRating_4(0);
        HelloApplication.current_book.setRating_5(0);

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE Imort_Library SET number_ratings = 0, average_rating = 0, rating_0 = 0, rating_1 = 0, rating_2 = 0, rating_3 = 0, rating_4 = 0, rating_5 = 0 WHERE isbn = " + isbn + ";";
            Statement stmt = connection.createStatement();
            stmt.execute(query);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

}
