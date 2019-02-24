/*
import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../user.service';
import {Router} from '@angular/router';
import { NavbarService } from '../navbar.service';
import { RecommendationService } from '../recommendation.service';
import { Recommendation } from '../recommendation';
import { RecsService, MovieDetails } from '../recs.service';

@Component({
  selector: 'app-past-recs',
  templateUrl: './past-recs.component.html',
  styleUrls: ['./past-recs.component.css']
})
export class PastRecsComponent implements OnInit {
  constructor(public userService: UserService, public recommendationService: RecommendationService,
    public user: User,private router: Router, public recsService: RecsService, 
    public nav: NavbarService) { }

    userRecommendations: Recommendation[] = [];
    movieId: any;
    movieIds: number[] = [];
    movieDetails: MovieDetails[] = [];

  getRecommendations(): void {
    this.recommendationService.getRecommendations(this.user)
      .subscribe(recommendations => 
        {
          this.userRecommendations = recommendations;
          console.log(recommendations[0].movieId);
          this.getMovieIds(recommendations)
           
        }, (err: any) => console.log(`Error: $(err)`));
  }

  //https://api.themoviedb.org/3/movie/522681?api_key=577da0848495397cdb9a1b657cfc5861&language=en-US
  

  

getMovieIds(userRecommendations) {
    for (let i=0; i<this.userRecommendations.length; i++) {
      this.movieIds.push(this.userRecommendations[i].movieId);
    }
    console.log(this.movieIds)
    for (let i=0; i<this.movieIds.length; i++) {
     this.recsService.sendMovieIds(this.movieIds[i])
      .subscribe(response=> this.movieDetails.push(response), 
      error => console.log(error));
      }
      console.log(this.movieDetails)
      console.log(this.movieDetails[0].title)
  }
  
  


  
  
  
  title = "View Recommendations";
  username = this.user.username;
  password = this.user.password;
  recs = this.userRecommendations;
  

  ngOnInit() {
  this.nav.show();
  this.getRecommendations();}

}

*/
