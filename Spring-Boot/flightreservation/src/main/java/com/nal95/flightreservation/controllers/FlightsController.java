package com.nal95.flightreservation.controllers;

import com.nal95.flightreservation.entities.Flight;
import com.nal95.flightreservation.repos.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightsController {

    @Autowired
    FlightRepository flightRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsController.class);
    @RequestMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
                              @RequestParam("departureDate")
                              @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, ModelMap modelMap){
        LOGGER.info("Inside findFlight() from: " + from + " to: "+to +" departure Date: "+ departureDate);
        List<Flight> flights =  flightRepository.findFlights(from,to,departureDate);
        modelMap.addAttribute("flights",flights);
        LOGGER.info("Fund Flight are: "+ flights);
        return "displayFlights";
    }
    @RequestMapping("/admin/showAddFlight")
    public String showAddFlight(){
        LOGGER.info("Inside showAddFlight() ADMIN");
        return "addFlight";
    }
}
