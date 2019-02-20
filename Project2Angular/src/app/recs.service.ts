import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecsService {

  constructor(private http: HttpClient) { }

  getGenres(): Observable<genres[]> {
    return this.http.get<genres[]>("https://api.themoviedb.org/3/genre/movie/list?api_key=78e263a07ddcb03810133fc82756418f&language=en-US");
  }
}

export interface genres {
  id: number;
  name: string;
}
