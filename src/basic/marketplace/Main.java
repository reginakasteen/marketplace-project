package basic.marketplace;

import model.Buyer;
import model.Product;

import java.util.Scanner;
import java.util.ArrayList;


    public class Main {
        private static Scanner scanner = new Scanner(System.in);

        static ArrayList<Buyer> buyers = new ArrayList<>();
        static ArrayList<Product> products = new ArrayList<>();

        public static void options() {
            System.out.println("\t1. Print the list of products");
            System.out.println("\t2. Print the list of buyers");
            System.out.println("\t3. Remove a product");
            System.out.println("\t4. Remove a buyer");
            System.out.println("\t5. Buy a product");
            System.out.println("\t6. Quit the application");
        }
        public static long randomNumber() {
            int max = 9999999;
            int min = 1000000;
            int range = max - min + 1;
            long rand = (long) (Math.random() * range) + min;
            return rand;
        }
        public static void initData() {
            buyers.add(new Buyer(randomNumber(), "Andrew", "Smith", 120.5f));
            buyers.add(new Buyer(randomNumber(), "John", "Miller", 500f));
            buyers.add(new Buyer(randomNumber(), "Mary", "McCartney", 1460f));

            products.add(new Product(randomNumber(), "cactus", 70f));
            products.add(new Product(randomNumber(), "book", 230.70f));
            products.add(new Product(randomNumber(), "headphones", 690.90f));


        }
        public static void main(String[] args) {

            boolean quit = false;
            initData();
            System.out.println("\tWelcome to our marketplace!");
            System.out.println("\tPlease, make your choise:");

            while (!quit) {
                boolean quitMain = false;
                    options();
                    switch (scanner.nextInt()) {
                        case 1:
                            getProductList ();
                            break;
                        case 2:
                            getBuyersList();
                            break;
                        case 3:
                            System.out.println("Enter product id:");
                            long productId = scanner.nextLong();
                            removeProductByID(productId);
                            System.out.println("You removed the product.");
                            break;
                        case 4:
                            System.out.println("Enter user id:");
                            long buyerId = scanner.nextLong();
                            removeBuyerByID(buyerId);
                            System.out.println("You removed the user.");
                            break;
                        case 5:
                            System.out.println("Enter buyer id:");
                            buyerId = scanner.nextLong();
                            System.out.println("Enter product id:");
                            productId = scanner.nextLong();
                            buyTheProduct (buyerId, productId);
                            break;
                        case 6:
                            System.out.println("Do you really want to quit?\n1. Yes\n2. No");
                            if (scanner.nextInt() == 1) {
                                quit = true;
                                break;
                            } else if (scanner.nextInt() == 2) {
                                break;
                            } else {
                                System.out.println("Invalid input. Please, try again");
                                break;
                            }
                            default:
                                System.out.println("Invalid input. Please, try again");
                                break;
                                }
                            }
        }

        public static Buyer getBuyerByID (long id) {
            Buyer current = null;
            for (Buyer buyer: buyers) {
                if(buyer.getId() == id) {
                    current = buyer;
                    break;
                }
            }
            return current;
        }
        public static void buyTheProduct (long buyerId, long productId) {
            Product item = getProductByID(productId);
            Buyer user = getBuyerByID(buyerId);
            if (getProductByID(productId) == null) {
                System.out.println("Wrong product id. Please, try again.");
            } else if (getBuyerByID(buyerId) == null) {
                System.out.println("Wrong buyer id. Please, try again.");
            } else {
                float money = user.getMoney();
                float price = item.getPrice();
                if (money >= price) {
                    float count = money - price;
                    System.out.println("Thank you for purchase! Your account status: " + count + " UAH");
                } else {
                    System.out.println("You don't have enough money.");
                }
            }
        }
        public static Product getProductByID (long id) {
            Product current = null;
            for (Product product: products) {
                if(product.getId() == id) {
                    current = product;
                    break;
                }
            }
            return current;
        }
        public static void removeProductByID (long id) {
            Product current = null;
            for (Product product: products) {
                if(product.getId() == id) {
                    current = product;
                    break;
                }
            }
            products.remove(current);
        }
        public static void removeBuyerByID (long id) {
            Buyer current = null;
            for (Buyer buyer: buyers) {
                if(buyer.getId() == id) {
                    current = buyer;
                    break;
                }
            }
            products.remove(current);
        }
        public static void getProductList () {
            for (Product product: products) {
                System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice() + "UAH");
                }
            }
        public static void getBuyersList () {
            for (Buyer buyer: buyers) {
                System.out.println(buyer.getId() + " " + buyer.getFirstName() + " " + buyer.getLastName() + " " + buyer.getMoney() + "UAH");
            }
        }
    }

