package com.movie;

import com.movie.service.MovieService;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        MovieService service = new MovieService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1.Add Movie");
            System.out.println("2.Add Theater");
            System.out.println("3.Map Movie to Theater");
            System.out.println("4.Book Ticket");
            System.out.println("5.Fetch Tickets by Movie");
            System.out.println("6.Display Available Seats");
            System.out.println("7.Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    sc.nextLine();
                    service.addMovie(sc.nextLine());
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Theater name: ");
                    String name = sc.nextLine();
                    System.out.print("Total seats: ");
                    int seats = sc.nextInt();
                    service.addTheater(name, seats);
                    break;

                case 3:
                    service.mapMovieToTheater(sc.nextInt(), sc.nextInt());
                    break;

                case 4:
                    System.out.print("Movie ID: ");
                    int mid = sc.nextInt();
                    System.out.print("Theater ID: ");
                    int tid = sc.nextInt();
                    System.out.print("Seat Number: ");
                    int seat = sc.nextInt();
                    service.bookTicket(mid, tid, seat);
                    break;

                case 5:
                    service.fetchTicketsByMovie(sc.nextInt());
                    break;

                case 6:
                    service.displayAvailableSeats(sc.nextInt());
                    break;

                case 7:
                    System.exit(0);
            }
        }
    }
}