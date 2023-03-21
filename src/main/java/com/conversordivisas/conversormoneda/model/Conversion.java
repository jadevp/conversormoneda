package com.conversordivisas.conversormoneda.model;

public class Conversion {
    private double amount;
    private String from;
    private String to;
    private double result;

    // Constructor por defecto
    public Conversion() {
    }

    // Constructor con parámetros
    public Conversion(double amount, String from, String to ,double result) {
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.result = result;
    }

    // Getters y setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}