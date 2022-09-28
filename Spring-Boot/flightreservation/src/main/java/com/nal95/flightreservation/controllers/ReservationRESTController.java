package com.nal95.flightreservation.controllers;

import com.nal95.flightreservation.dto.ReservationUpdateRequest;
import com.nal95.flightreservation.entities.Reservation;
import com.nal95.flightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationRESTController {

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        return reservationRepository.findById(id).orElseThrow();

    }

    @RequestMapping("reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
        Reservation reservation = reservationRepository.findById(request.getId()).orElseThrow();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.isCheckedIn());
        return reservationRepository.save(reservation);

    }
}
