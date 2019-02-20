import { Component, OnInit } from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';
import { RecsService, genres } from '../recs.service';
import { NavbarService } from '../navbar.service';

@Component({
  selector: 'app-new-rec',
  templateUrl: './new-rec.component.html',
  styleUrls: ['./new-rec.component.css']
})
export class NewRecComponent implements OnInit {

  title = "Get a Recommendation";
  private genreList: genres[];
  constructor(public userService: UserService,
    public user: User,
    public router: Router,
    private recsService: RecsService,
    public nav: NavbarService) { }
  
  ngOnInit() {
    this.nav.show();
    this.recsService.getGenres().subscribe(
      data => (this.genreList = data,
            console.log(data)),
      err => console.log(`Error: ${err}`)
    )
  }

}
