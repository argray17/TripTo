package com.redhawkride.view;


import java.util.Scanner;

public class RedHawkRideUI {

    Scanner in = new Scanner(System.in);

    public void mainMenu() {

        System.out.println("Enter a number from the menu to continue. \n");
        System.out.println("\t(1) Register as new user. \n\t(2) Continue as Rider. \n\t(3) Continue as Driver. " +
                "\n\t(4) Quit\nChoice: ");

        int choice = in.nextInt();

        switch (choice){
            case 1:
                newUser();
                break;

            case 2:
                rider();
                break;
            case 3:
                driver();
                break;
            case 4:
                System.out.println("You have quit the RedhawkRide System.");
                break;
        }
    }

    public void newUser(){

        System.out.println("Enter your id: ");
        String studentID = in.nextLine();

        System.out.println("Enter your Bank account number: ");
        String accountNumber = in.nextLine();

        System.out.println("Enter your routing number: ");
        String routingNumber = in.nextLine();

        addStudent();
    }
    public void rider(){
        System.out.println("\t(1) Provide your start and end location as (latitude, longitude) pairs to receive estimated cost for the ride" +
                " \n\t(2) Request a ride. \n\t(3) View ride history. \n\t(4) Return to Main Menu. \nChoice: ");

        int choice = in.nextInt();

        do {
            switch (choice) {
                case 1:
                    System.out.println("Enter your start location latitude: \n");
                    //read input

                    System.out.println("Enter your start location longitude: \n");
                    //read input

                    System.out.println("Enter your end location latitude: \n");
                    //read input

                    System.out.println("Enter your end location longitude: \n");
                    //read input

                    //estimate trip cost and display cost
                    estimateTripCost();
                    break;

                case 2:
                    requestTrip();
                    break;
                case 3:
                    //no clue where history is;
                    break;
                case 4:
                    mainMenu();
                    break;
            }
        }while(choice != 4);
    }

    public void driver(){
        System.out.println("\t(1) Start driving or End driving \n\t(2) Start ride or finish ride\n\t(3) Update location with latitude, longitude pair. " +
                "\n\t(4) Return to Main Menu.\nChoice: ");

        int choice = in.nextInt();

        do {
            switch (choice) {
                case 1:
                    System.out.println("Enter S for start driving. Enter E for end driving.\n");
                    char drivingOption = in.next().charAt(0);

                    if(drivingOption == 'S' || drivingOption == 's')
                        isAvailable();

                    if(drivingOption == 'E' || drivingOption == 'e')
                        notAvailable();
                    break;

                case 2:
                    System.out.println("Enter S or start ride. Enter F for finish ride.\n");
                    char rideOption = in.next().charAt(0);

                    if(rideOption == 'S' || rideOption == 's')
                        startride;

                    if(rideOption == 'F' || rideOption == 'f')
                        endride;
                    break;
                case 3:
                    System.out.println("Enter your location latitude: \n");
                    //read input

                    System.out.println("Enter your location longitude: \n");
                    //read input
                    break;
                case 4:
                    mainMenu();
                    break;
            }
        }while(choice != 4);
    }
}
