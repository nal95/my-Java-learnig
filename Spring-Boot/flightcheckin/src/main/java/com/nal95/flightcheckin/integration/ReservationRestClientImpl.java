package com.nal95.flightcheckin.integration;

import com.nal95.flightcheckin.integration.dto.Reservation;
import com.nal95.flightcheckin.integration.dto.ReservationRequest;
import org.springframework.web.client.RestTemplate;

public class ReservationRestClientImpl implements ReservationRestClient{

    private  static final String RESERVATION_REST_URL = "http://localhost:8080/flightreservation/reservations/";
    @Override
    public Reservation findReservation(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(RESERVATION_REST_URL + id,Reservation.class);

    }

    @Override
    public Reservation updateReservation(ReservationRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(RESERVATION_REST_URL,request,Reservation.class);

    }

}
