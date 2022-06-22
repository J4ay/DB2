package com.example.scrum;

public class Book {
    private int isbn;
    private String title;
    private String author;
    private int year;
    private String edition;
    private String publisher;
    private int number_of_books;
    private int number_borrowed_books;
    private int number_ratings;
    private float average_rating;
    private int rating_0;
    private int rating_1;
    private int rating_2;
    private int rating_3;
    private int rating_4;
    private int rating_5;

    // ****************************** Constructor ******************************
    /*public Book(int isbn, String title, String author, int year, String edition, String publisher, int number_of_books, int number_borrowed_books, int number_ratings, float average_rating, int rating_0, int rating_1, int rating_2, int rating_3, int rating_4, int rating_5) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.edition = edition;
        this.publisher = publisher;
        this.number_of_books = number_of_books;
        this.number_borrowed_books = number_borrowed_books;
        this.number_ratings = number_ratings;
        this.average_rating = average_rating;
        this.rating_0 = rating_0;
        this.rating_1 = rating_1;
        this.rating_2 = rating_2;
        this.rating_3 = rating_3;
        this.rating_4 = rating_4;
        this.rating_5 = rating_5;
    }*/


    // ****************************** get methods ******************************
    public int getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
    public String getEdition() {
        return edition;
    }
    public String getPublisher() {
        return publisher;
    }
    public int getNumber_of_books() {
        return number_of_books;
    }
    public int getNumber_borrowed_books() {
        return number_borrowed_books;
    }
    public int getNumber_ratings() {
        return number_ratings;
    }
    public float getAverage_rating() {
        return average_rating;
    }
    public int getRating_0() {
        return rating_0;
    }
    public int getRating_1() {
        return rating_1;
    }
    public int getRating_2() {
        return rating_2;
    }
    public int getRating_3() {
        return rating_3;
    }
    public int getRating_4() {
        return rating_4;
    }
    public int getRating_5() {
        return rating_5;
    }

    // ****************************** set methods ******************************
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setNumber_of_books(int number_of_books) {
        this.number_of_books = number_of_books;
    }
    public void setNumber_borrowed_books(int number_borrowed_books) {
        this.number_borrowed_books = number_borrowed_books;
    }
    public void setNumber_ratings(int number_ratings) {
        this.number_ratings = number_ratings;
    }
    public void setAverage_rating(float average_rating) {
        this.average_rating = average_rating;
    }
    public void setRating_0(int rating_0) {
        this.rating_0 = rating_0;
    }
    public void setRating_1(int rating_1) {
        this.rating_1 = rating_1;
    }
    public void setRating_2(int rating_2) {
        this.rating_2 = rating_2;
    }
    public void setRating_3(int rating_3) {
        this.rating_3 = rating_3;
    }
    public void setRating_4(int rating_4) {
        this.rating_4 = rating_4;
    }
    public void setRating_5(int rating_5) {
        this.rating_5 = rating_5;
    }

    // ****************************** methods ******************************
    public void clearBook() {           //fills book with empty values, makes it easier to spot wrongly constructed books
        isbn = 0;
        title = "---";
        author = "---";
        year = 0;
        edition = "---";
        publisher = "---";
        number_of_books = 0;
        number_borrowed_books = 0;
        number_ratings = 0;
        average_rating = 0;
        rating_0 = 0;
        rating_1 = 0;
        rating_2 = 0;
        rating_3 = 0;
        rating_4 = 0;
        rating_5 = 0;
    }

    public String getBookAsString() {       //returns the values of a book as a formated string, seperated by newlines
        String book_s = "ISBN: " + isbn + "\nAuthor: " + author +"\nYear: " + year + "\nEdition: " + edition + "\nPublisher: " + publisher + "\nNumber of Books: " + number_of_books + " (Currently Borrowed: " + number_borrowed_books + ")\nAverage rating: " + average_rating + " (from " + number_ratings + " ratings)";
        return book_s;
    }
}
