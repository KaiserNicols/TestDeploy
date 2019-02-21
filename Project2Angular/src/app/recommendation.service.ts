import { Injectable } from '@angular/core';
import { Recommendation } from './recommendation';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  private baseUrl: string = 'http://54.145.242.129:8080/Project2/rest/rec/'
  private getRecommendationsURL: string = 'all';  // URL to get all recommendations for a single user
  private getAllRecommendationsURL: string = 'entirelist';  // URL to get all recommendations

  constructor(private http: HttpClient) { }

  getRecommendations(): Observable<Recommendation[]> {
    return this.http.get<Recommendation[]>(this.baseUrl + this.getRecommendationsURL);
  }

  getAllRecommendations(): Observable<Recommendation[]> {
    return this.http.get<Recommendation[]>(this.baseUrl + this.getAllRecommendationsURL);
  }
}
