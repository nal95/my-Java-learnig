export interface Reservation {
  "id": number,
  "checkedIn": boolean,
  "numberOfBags": number,
  "passenger": {
    "id": number,
    "firstName": string,
    "lastName": string,
    "middleName": string,
    "email": string,
    "phone": string
  },
  "flight": {
    "id": number,
    "flightNumber": string,
    "operatingAirlines":string,
    "departureCity": string,
    "arrivalCity": string,
    "dateOfDeparture": string,
    "estimatedDepartureTime": string
  }

}
