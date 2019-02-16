import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })    
};

@Injectable()
export class LoginService {
  constructor(private http: HttpClient) { }
  baseUrl: String = "http://54.145.242.129:8080/Project2/rest/";
//http://54.145.242.129:8080/Project2/rest/user

  private userObservable: Observable<any>;

  getUser(){
    this.userObservable = this.http.get(this.baseUrl + '/user');
  };
}

export class User{
  id: number;
  email: string;
  username: string;
  firstname: string;
  lastname: string;
  //might not need all of these for User object
}
