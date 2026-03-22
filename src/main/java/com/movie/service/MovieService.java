package com.movie.service;

import com.movie.entity.*;
import com.movie.util.JPAUtil;

import jakarta.persistence.EntityManager;
import java.util.List;

public class MovieService {

    public void addMovie(String title) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Movie m = new Movie();
        m.setTitle(title);

        em.persist(m);
        em.getTransaction().commit();
        em.close();
    }

    public void addTheater(String name, int seats) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Theater t = new Theater();
        t.setName(name);
        t.setTotalSeats(seats);

        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public void mapMovieToTheater(int movieId, int theaterId) {

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Movie m = em.find(Movie.class, movieId);
        Theater t = em.find(Theater.class, theaterId);

        m.getTheaters().add(t);
        t.getMovies().add(m);

        em.getTransaction().commit();
        em.close();
    }

    public void bookTicket(int movieId, int theaterId, int seatNo) {

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Movie m = em.find(Movie.class, movieId);
        Theater t = em.find(Theater.class, theaterId);

        Ticket ticket = new Ticket();
        ticket.setMovie(m);
        ticket.setTheater(t);
        ticket.setSeatNumber(seatNo);

        em.persist(ticket);

        em.getTransaction().commit();
        em.close();
    }

    public void fetchTicketsByMovie(int movieId) {

        EntityManager em = JPAUtil.getEntityManager();

        List<Ticket> list =
            em.createQuery(
                "from Ticket where movie.movieId = :id",
                Ticket.class)
            .setParameter("id", movieId)
            .getResultList();

        for (Ticket t : list) {
            System.out.println(
                "Ticket: " + t.getTicketId()
              + " Seat: " + t.getSeatNumber()
              + " Theater: " + t.getTheater().getName());
        }

        em.close();
    }

    public void displayAvailableSeats(int theaterId) {

        EntityManager em = JPAUtil.getEntityManager();

        Theater t = em.find(Theater.class, theaterId);

        int booked = t.getTickets().size();
        int available = t.getTotalSeats() - booked;

        System.out.println("Available seats: " + available);

        em.close();
    }
}