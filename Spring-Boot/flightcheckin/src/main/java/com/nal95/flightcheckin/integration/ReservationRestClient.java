package com.nal95.flightcheckin.integration;

import com.nal95.flightcheckin.integration.dto.Reservation;
import com.nal95.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

    public Reservation findReservation(Long id);
    public void updateReservation(ReservationUpdateRequest request);

}
