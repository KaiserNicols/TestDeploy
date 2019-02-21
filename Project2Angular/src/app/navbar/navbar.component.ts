import { Component, OnInit, DoCheck } from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';
import { NavbarService } from '../navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  constructor(public userService: UserService,
    public user: User,private router: Router, public nav: NavbarService) { }

  ngOnInit() {}  

  username = this.user.username
  password = this.user.password
  
  
  
}
