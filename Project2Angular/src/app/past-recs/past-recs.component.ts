import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../user.service';
import {Router} from '@angular/router';
import { NavbarService } from '../navbar.service';

@Component({
  selector: 'app-past-recs',
  templateUrl: './past-recs.component.html',
  styleUrls: ['./past-recs.component.css']
})
export class PastRecsComponent implements OnInit {
  constructor(public userService: UserService,
    public user: User,private router: Router, public nav: NavbarService) { }


  title = "View Recommendations";
  username = this.user.username;
  password = this.user.password;
  

  ngOnInit() {
  this.nav.show();}

}
