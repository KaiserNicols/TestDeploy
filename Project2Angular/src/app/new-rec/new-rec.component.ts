import { Component, OnInit } from '@angular/core';
import {User, UserService, UserAttempt} from '../user.service';
import {Router} from '@angular/router';
import { RecsService, genres, actors, UserResponse } from '../recs.service';
import { NavbarService } from '../navbar.service';

@Component({
  selector: 'app-new-rec',
  templateUrl: './new-rec.component.html',
  styleUrls: ['./new-rec.component.css']
})
export class NewRecComponent implements OnInit {

  title = "Get a Recommendation";
  releaseLower: number;
  releaseGreater: number;
  releaseYear: number;
  ratingLower: number;
  ratingGreater: number;
  selectActor: string;
  selectGenre: string;
  selectDirector: string;
  dateOptions: string;
  getUserName: UserAttempt;
  
  recTitle: string;
  recPosterURL: string;
  recId: number;
  recIsHidden: boolean = false;
  badQuery: boolean = false;

  public actor: actors;
  public actors: actors[];
  public actorId: number;
  public directorId:number;
  public genre: genres;
  public genres: genres[];
  public getAppend: string;
  public tempData: any;
  public recommendation: any[];
x
  constructor(public userService: UserService,
    public user: User,
    public router: Router,
    public recsService: RecsService,
    public nav: NavbarService,
    public submitResponse: UserResponse) { }
  
  submitGoodRec(): void{
    console.log(this.user.username);
    this.submitResponse.username = this.user.username;
    this.submitResponse.movieId = this.recId;
    this.submitResponse.helpful = 1;
    this.recsService.submitFeedback(this.submitResponse).subscribe(
      data => {
        console.log(data);
        if (data!=null) {this.router.navigate(['/past-recs']);} 
      },
      (err: any) => console.log(`Error: $(err)`)
    );
  }
  submitBadRec(): void{
    this.getUserName = this.userService.getCurrentUser();
    this.submitResponse.username = this.getUserName.username;
    this.submitResponse.movieId = this.recId;
    this.submitResponse.helpful = 0;
    this.recsService.submitFeedback(this.submitResponse).subscribe(
      data => {
        console.log(data);
        if (data!=null) {this.router.navigate(['/past-recs']);} 
      },
      (err: any) => console.log(`Error: $(err)`)
    );
  }
  
  getRec(): void{
    this.getAppend = "";
    this.badQuery = false;
    console.log(this.releaseLower);
    console.log(this.releaseGreater);
    console.log(this.releaseYear);
    console.log(this.ratingLower);
    console.log(this.ratingGreater);
    console.log(this.selectActor);
    console.log(this.selectDirector);
    console.log(this.selectGenre);
    /*
    * api get request order
    * https://api.themoviedb.org/3/discover/movie?api_key=78e263a07ddcb03810133fc82756418f&sort_by=popularity.desc&include_adult=false&include_video=false&page=1
    * &release_date.gte=123 - release date greater than 123
    * &release_date.lte=123 - release date less than 123
    * &vote_average.gte=123 - vote average greater than 123
    * &vote_average.lte=123 - vote average less than 123
    * &with_genres=123 - with genre #123
    * &with_people=123 - with actor #123
    */
    // if (this.release!=null){
    //   this.getAppend = this.getAppend + "&release_date.gte=" + this.release;
    // }
    // if (this.rating!=null){
    //   this.getAppend = this.getAppend + "&vote_average.gte=" + this.rating;
    // }
    if (this.selectGenre!=null){
      for (let i = 0; i < this.genres.length; i++)
        if (this.selectGenre === this.genres[i].name){
          this.getAppend = this.getAppend + "&with_genres=" + this.genres[i].id}
    }
    // if (this.selectActor!=null){
    //   this.recsService.getActor(this.selectActor).subscribe(
    //     data => { console.log(data);
    //     this.tempData = data["results"];
    //     this.tempData = this.tempData[0];
    //     this.actorId = this.tempData["id"];
    //     this.getAppend = this.getAppend + "&with_cast=" + this.actorId;
    //     }
    //     ,err => console.log(`Error: ${err}`)
    //   )}

    //director: "&with_cast=" + this.directorId;
    console.log(this.getAppend);
    this.recsService.getReccomendation(this.getAppend).subscribe(
      data => {this.recommendation = data["results"];
                console.log(data);
                console.log(this.recommendation);
                this.tempData = this.recommendation[Math.floor(Math.random() * this.recommendation.length+1)]
                this.recTitle = this.tempData["original_title"];
                this.recId = this.tempData["id"];
                // this.recPosterURL ="http://image.tmdb.org/t/p/w185" + this.tempData["poster_path"];
                this.recPosterURL ="http://image.tmdb.org/t/p/w342" + this.tempData["poster_path"];
                console.log(this.recPosterURL);
                if (this.recTitle = undefined){
                  this.recIsHidden = false;
                  this.badQuery = true;}
                else{
                  this.recIsHidden = true;}
              }
              ,err => console.log(`Error: ${err}`)
    )
  }
  
  ngOnInit() {
    this.nav.show();
    
    this.recsService.getGenres().subscribe(
      data => { this.genres = data["genres"];
           this.genre = this.genres[0];
            console.log(this.genre["name"]);
            console.log(this.genres);
            
          }
          ,err => console.log(`Error: ${err}`)
    )
  }
}
