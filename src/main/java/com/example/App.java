package com.example;

public class App {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   Starting Java Microservice inside Docker...   ");
        System.out.println("=================================================");
        App app = new App();
        System.out.println("Service status: " + app.getStatus());
        System.out.println("Calculation test (5 + 10): " + app.add(5, 10));
    }

    public String getStatus() {
        return "UP";
    }

    public int add(int a, int b) {
        return a + b;
    }
}
