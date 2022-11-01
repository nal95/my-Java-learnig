export interface Users {
  firstName: string,
  lastName: string,
  email: string,
  title: Title,
  encryptedPassword: string
}

export enum Title {
  'Dr',
  'Med',
  'Passiv'
}

export interface User {
  email: string,
  encryptedPassword: string
}

export interface UserResponse {
  userId: string,
  firstName: string,
  lastName: string,
  email: string,
  title: Title
}
