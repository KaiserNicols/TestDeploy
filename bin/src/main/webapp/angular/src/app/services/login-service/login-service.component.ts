import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class LoginService {
  constructor(private http: HttpClient) { }

  // getUser(): Observable<User>{
  //   return this.http.get<User>("--insert URL to GET user from servlet--");
  // }
}

export interface User{
  id: number,
  email: string,
  username: string,
  firstname: string,
  lastname: string
  //might not need all of these for User object
}
