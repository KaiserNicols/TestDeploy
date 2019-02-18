import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
/*
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    //'Authorization': 'my-auth-token',
    //observe: 'response'
  })
};
*/




@Injectable({
  providedIn: 'root'
})

export class UserService {
  constructor(private http: HttpClient) { }
  //private baseUrl: string = 'http://localhost:8080/Project2/rest/';
  private baseUrl: string = 'http://54.145.242.129:8080/Project2/rest/'

  private userObservable: Observable<UserAttempt>;
  //private userObservable: Observable<any>;

  loginUser(userAttempt: UserAttempt): Observable<UserAttempt> {
    return this.http.post<User>(this.baseUrl + "user", userAttempt);
  }

  // getUser(){
  //   this.userObservable = this.http.get(this.baseUrl + '/user');
  // }
}

export class User {
  id: number;
  email: string;
  username: string;
  password: string;
  firstname: string;
  lastname: string;
}

export class UserAttempt {
  username: string;
  password: string
}
