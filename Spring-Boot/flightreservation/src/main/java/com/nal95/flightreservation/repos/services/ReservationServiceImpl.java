package com.nal95.flightreservation.repos.services;

import com.nal95.flightreservation.dto.ReservationRequest;
import com.nal95.flightreservation.entities.Flight;
import com.nal95.flightreservation.entities.Passenger;
import com.nal95.flightreservation.entities.Reservation;
import com.nal95.flightreservation.repos.FlightRepository;
import com.nal95.flightreservation.repos.PassengerRepository;
import com.nal95.flightreservation.repos.ReservationRepository;
import com.nal95.flightreservation.util.EmailUtil;
import com.nal95.flightreservation.util.PDFGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Override
    public Reservation bookFlight(ReservationRequest request){

        LOGGER.info("Inside bookFlight()");
        Long flightId = request.getFlightId();
        LOGGER.info("Fetching flight for flight id: "+flightId);
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

        LOGGER.info("Saving the reservation: "+reservation);
        Reservation savedReservation = reservationRepository.save(reservation);
        LOGGER.info("Generating the itinerary");
        String filePath = "C:/Users/nguepi-Jiometio/Documents/my-Java-learnig/Spring-Boot/reservations"+savedReservation.getId()+".pdf";
        pdfGenerator.generateItinerary(savedReservation,filePath);
        LOGGER.info("Email itinerary sending ");
        emailUtil.sendItinerary(passenger.getEmail(),filePath);
        return  savedReservation;
    }
}
