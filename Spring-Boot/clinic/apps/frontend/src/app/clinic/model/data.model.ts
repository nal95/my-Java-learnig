export interface Patient {
  "id": number,
  "firstName": string,
  "lastName": string,
  "age": number,
  "email": string,
  "created": string,
  "enabled": boolean,
  "clinicalData": ClinicalData [],
}

export interface ClinicalData {
  "id": number,
  "componentName": string,
  "componentValue": string,
  "measuredDateTime": string,
}

export interface NewPatient {
  "firstName": string,
  "lastName": string,
  "age": number,
  "email": string,
}
export interface NewPatientResponse {
  "firstName": string,
  "lastName": string,
}
