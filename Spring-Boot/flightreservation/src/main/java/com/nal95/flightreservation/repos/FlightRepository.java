package com.nal95.flightreservation.repos;

import com.nal95.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "FROM Flight  WHERE departureCity=:departureCity AND arrivalCity=:arrivalCity AND dateOfDeparture=:dateOfDeparture ")
    //"SELECT flight FROM Flight flight WHERE flight.departureCity=:departureCity AND flight.arrivalCity=:arrivalCity AND flight.dateOfDeparture=:dateOfDeparture "
    List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to,
                             @Param("dateOfDeparture") Date departureDate);
}
