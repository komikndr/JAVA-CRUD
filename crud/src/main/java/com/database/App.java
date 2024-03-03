package com.database;

public class App {
    private static final int LAST_OPTION_MENU = 7;
    public static void main(String[] args) {
        HandlerDrones handlerDrones = new HandlerDrones();
        int option;
        do {
            option = handlerDrones.showMenu();
            switch (option) {
                case 1 -> // create product
                        handlerDrones.createProduct();
                case 2 -> //list products
                        handlerDrones.showAll();
                case 3 -> //see single product
                        handlerDrones.showOne();
                case 4 -> //update product
                        handlerDrones.update();
                case 5 -> //delete product
                        handlerDrones.delete();
                case 6 -> //config database
                        handlerDrones.updateDatabase();
                case LAST_OPTION_MENU -> //exit
                        System.out.println("Exit");
                default -> System.out.println("Option " + option + " not found");
            }
        } while (option!=LAST_OPTION_MENU);
    }


}