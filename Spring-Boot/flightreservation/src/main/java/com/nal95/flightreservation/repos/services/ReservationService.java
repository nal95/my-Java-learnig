package com.nal95.flightreservation.repos.services;

import com.nal95.flightreservation.dto.ReservationRequest;
import com.nal95.flightreservation.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request);
}
