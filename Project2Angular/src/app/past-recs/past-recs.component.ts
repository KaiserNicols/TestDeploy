import { Component, OnInit } from '@angular/core';
import { User, UserService } from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-past-recs',
  templateUrl: './past-recs.component.html',
  styleUrls: ['./past-recs.component.css']
})
export class PastRecsComponent implements OnInit {
  constructor(public userService: UserService,
    public user: User,private router: Router) { }


  title = "View Recommendations";
  username = this.user.username;
  password = this.user.password;


  ngOnInit() {
  }

}
