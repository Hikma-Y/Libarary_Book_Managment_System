package com.example.library.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private int amount;

    public Book() {}

    public Book(int id, String title, String author, String category, int amount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.amount = amount;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getAmount() { return amount; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    public void setAmount(int amount) { this.amount = amount; }
}
