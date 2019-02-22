import { Component, OnInit } from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';
import { RecsService, genres, actors } from '../recs.service';
import { NavbarService } from '../navbar.service';
import { release } from 'os';

@Component({
  selector: 'app-new-rec',
  templateUrl: './new-rec.component.html',
  styleUrls: ['./new-rec.component.css']
})
export class NewRecComponent implements OnInit {

  title = "Get a Recommendation";
  release: number;
  rating: number;
  selectActor: string;
  selectGenre: string;

  public actor: actors;
  public actors: actors[];
  public actorId: number;
  public genre: genres;
  public genres: genres[];
  public getAppend: string;
  public tempData: any;
  public recommendation: any[];

  constructor(public userService: UserService,
    public user: User,
    public router: Router,
    public recsService: RecsService,
    public nav: NavbarService) { }
  
  getRec(){
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
        if (this.selectGenre = this.genres[i].name){
          this.getAppend = this.getAppend + "&with_genres=" + this.genres[i].id}
    }
    // if (this.selectActor!=null){
    //   this.recsService.getActor(this.selectActor).subscribe(
    //     data => { console.log(data);
    //     this.tempData = data["results"];
    //     this.tempData = this.tempData[0];
    //     this.actorId = this.tempData["id"];
    //     this.getAppend = this.getAppend + "&with_people=" + this.actorId;
    //     }
    //     ,err => console.log(`Error: ${err}`)
    //   )}
    this.recsService.getReccomendation(this.getAppend).subscribe(
      data => {this.recommendation = data["results"];
                console.log(data);
                console.log(this.recommendation);
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
