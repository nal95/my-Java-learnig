export interface Reservation {
  "id": number,
  "checkedIn": boolean,
  "numberOfBags": number,
  "passenger": Passenger,
  "flight": Flight
}

export interface Passenger {
  "id": number,
  "firstName": string,
  "lastName": string,
  "middleName": string,
  "email": string,
  "phone": string
}

export interface Flight {
  "id": number,
  "flightNumber": string,
  "operatingAirlines":string,
  "departureCity": string,
  "arrivalCity": string,
  "dateOfDeparture": string,
  "estimatedDepartureTime": string
}
