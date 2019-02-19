import { Injectable, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root',
})

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }
  private baseUrl: string = 'http://localhost:8080/Project2/rest/';
  //private baseUrl: string = 'http://54.145.242.129:8080/Project2/rest/'

  loginUser(userAttempt: User): Observable<User> {
    return this.http.post<User>(this.baseUrl + "user", userAttempt, httpOptions);
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

