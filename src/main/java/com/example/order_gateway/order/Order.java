package com.example.order_gateway.order;

public class Order {
    private final String orderId;
    private String status;
    private String clOrdID;
    private char side;
    private double price;
    private int orderQty;
    private String symbol;

    public Order(String orderId, String status, OrderCreateRequestDto request) {
        this.orderId = orderId;
        this.status = status;
        this.clOrdID = request.getClOrdID();
        this.side = request.getSide();
        this.price = request.getPrice();
        this.orderQty = request.getOrderQty();
        this.symbol = request.getSymbol();
    }

    // Getters and setters
    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
