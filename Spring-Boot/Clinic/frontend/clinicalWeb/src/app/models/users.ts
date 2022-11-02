export interface Users {
  firstName: string,
  lastName: string,
  username:string,
  email: string,
  password: string,
  title: string
}

export enum Title {
  'Dr',
  'Med',
  'Passive'
}

export interface User {
  email: string,
  password: string
}

export interface UserResponse {
  access_token: string,
  refresh_token: string,
  UUID: string
}
