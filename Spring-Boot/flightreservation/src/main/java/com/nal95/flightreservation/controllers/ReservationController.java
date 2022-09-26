package com.nal95.flightreservation.controllers;

import com.nal95.flightreservation.dto.ReservationRequest;
import com.nal95.flightreservation.entities.Flight;
import com.nal95.flightreservation.entities.Reservation;
import com.nal95.flightreservation.repos.FlightRepository;
import com.nal95.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
        Flight flight = flightRepository.findById(flightId).orElseThrow();
        modelMap.addAttribute("flight",flight);
        return  "completeReservation";
    }

    @RequestMapping("/completeReservation")
    public String completeReservation(ReservationRequest request, ModelMap modelMap){
        //reservationService.bookFlight()
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg","Reservation Created successfully and the id is :" + reservation.getId());


        return  "reservationConfirmation";

    }
}
