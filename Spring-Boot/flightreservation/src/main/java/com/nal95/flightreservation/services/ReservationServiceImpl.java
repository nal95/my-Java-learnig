package com.nal95.flightreservation.services;

import com.nal95.flightreservation.dto.ReservationRequest;
import com.nal95.flightreservation.entities.Flight;
import com.nal95.flightreservation.entities.Passenger;
import com.nal95.flightreservation.entities.Reservation;
import com.nal95.flightreservation.repos.FlightRepository;
import com.nal95.flightreservation.repos.PassengerRepository;
import com.nal95.flightreservation.repos.ReservationRepository;
import com.nal95.flightreservation.util.EmailUtil;
import com.nal95.flightreservation.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    PDFGenerator pdfGenerator;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public Reservation bookFlight(ReservationRequest request){
        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).orElseThrow();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setPhone(request.getPassengerPhone());
        passenger.setEmail(request.getPassengerEmail());

        Passenger savedPassenger = passengerRepository.save(passenger);
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        Reservation savedReservation = reservationRepository.save(reservation);
        String filePath = "C:/Users/nguepi-Jiometio/Documents/my-Java-learnig/Spring-Boot/reservations"+savedReservation.getId()+".pdf";
        pdfGenerator.generateItinerary(savedReservation,filePath);
        emailUtil.sendItinerary(passenger.getEmail(),filePath);
        return  savedReservation;
    }
}
