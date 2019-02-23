import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of, merge } from 'rxjs';
import { mapTo, delay } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecsService {

 private baseUrl: string = "http://54.145.242.129:8080/Project2/rest/rec/submit";
 //private baseUrl: string = "http://localhost:8080/Project2/rest/rec/submit";

  constructor(private http: HttpClient) { }

  getGenres(): Observable<genres[]> {
    return this.http.get<genres[]>("https://api.themoviedb.org/3/genre/movie/list?api_key=78e263a07ddcb03810133fc82756418f&language=en-US");
  }
  getActor(actor: string): Observable<any[]>{
    //sample api path:
    //https://api.themoviedb.org/3/search/person?api_key=78e263a07ddcb03810133fc82756418f&query=Brad%20Pitt
    for (let i=0; i < actor.length; i++){
      actor = actor.replace(" ","%20");
      console.log(actor);
    }
    return this.http.get<any[]>("https://api.themoviedb.org/3/search/person?api_key=78e263a07ddcb03810133fc82756418f&query="+ actor +"&page=1&include_adult=false");
  }
  getReccomendation(appendToURL: string): Observable<any[]>{
   return this.http.get<any[]>("https://api.themoviedb.org/3/discover/movie?api_key=78e263a07ddcb03810133fc82756418f&sort_by=popularity.desc&include_adult=false&include_video=false&page=1" + appendToURL);
  }
  submitFeedback(userFeedback: UserResponse): Observable<UserResponse>{
    return this.http.post<UserResponse>(this.baseUrl, userFeedback);
  }
}

export interface genres {
  id: number;
  name: string;
}

export interface actors {
  id: number;
  name: string;
}

export class UserResponse {
  username: string;
  movieId: number;
  helpful: number;
}
