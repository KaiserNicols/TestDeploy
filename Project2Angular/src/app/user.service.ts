import { Injectable, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

/*
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};
<<<<<<< HEAD
=======
*/

>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514

@Injectable({
  providedIn: 'root',
})

@Injectable()
export class UserService {
<<<<<<< HEAD
=======
  constructor(private http: HttpClient) { }
  //private baseUrl: string = 'http://localhost:8080/Project2/rest/';
   private baseUrl: string = 'http://54.145.242.129:8080/Project2/rest/'
>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514

  constructor(private http: HttpClient) { }
  private baseUrl: string = 'http://localhost:8080/Project2/rest/';
  //private baseUrl: string = 'http://54.145.242.129:8080/Project2/rest/'

<<<<<<< HEAD
  loginUser(userAttempt: User): Observable<User> {
    return this.http.post<User>(this.baseUrl + "user", userAttempt, httpOptions);
=======
  loginUser(userAttempt: UserAttempt): Observable<UserAttempt> {
    return this.http.post<User>(this.baseUrl + "user", userAttempt);
>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514
  }
}

@Injectable()
export class User {
  id: number;
  email: string;
  username: string;
  password: string;
  firstname: string;
  lastname: string;
}

