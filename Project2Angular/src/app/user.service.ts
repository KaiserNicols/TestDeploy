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
  // private baseUrl: string = 'http://localhost:8080/Project2/rest/';

  private baseUrl: string = 'http://54.198.236.2:8080/Project2/rest/'
currentUser: UserAttempt;

  loginUser(userAttempt: UserAttempt): Observable<UserAttempt> {
    this.currentUser = userAttempt;
    return this.http.post<User>(this.baseUrl + "user", userAttempt);
  }

  // loginUser(user: User): Observable<User> {
  //   return this.http.post<User>(this.baseUrl + "user", user)
  // }

  registerUser(userAttempt: User): Observable<User> {
    return this.http.put<User>(this.baseUrl + "user/register", userAttempt);
  }

  getCurrentUser() : UserAttempt {
    return this.currentUser;
  }

}

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
}

export class UserAttempt {
  username: string;
  password: string
}
