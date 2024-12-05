package com.ExpenseTracker.modelos;

import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Expense {
//atributos
    int id;
    String description;
    double amount;
    LocalDate date;
    String category;

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
 //constructor
    public Expense(int id, String descripcion, double amount, LocalDate date) {
        this.id = id;
        this.description = descripcion;
        this.amount = amount;
        this.date = date;
        this.category = category;


    }

    @Override
    public String toString() {
        return "Expense" +
                "id=" + id +
                ", descripcion=" + description +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category
                ;
    }



}
