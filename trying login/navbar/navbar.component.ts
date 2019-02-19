import { Component, OnInit } from '@angular/core';
import {User, UserService} from '../user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  // user: User = {
  //   id: null,
  //   email: null,
  //   username: null,
  //   password: null,
  //   firstname: null,
  //   lastname: null
  // };

  // userAttempt: UserAttempt = {
  //   username: null,
  //   password: null
  // };



  // login(): User {
  //   this.userService.loginUser(this.userAttempt).subscribe(
  //     response => {
  //       console.log(this.userAttempt);
  //       // @ts-ignore
  //       this.user = response;
  //       console.log(response);

  //       console.log(this.user)
  //     },
  //     (err: any) => console.log(`Error: $(err)`)
  //   );
  //   return this.user;
  // }

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

}
