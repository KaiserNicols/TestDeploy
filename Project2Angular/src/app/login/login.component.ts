import {Component, OnInit} from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';
import { NavbarService } from '../navbar.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit{

    username: string;
    password: string;

  login(): void {
    this.user.username = this.username;
    this.user.password = this.password;
    this.userService.loginUser(this.user).subscribe(
      response => {
        //console.log(this.username, this.password);
        console.log(response);
        console.log("this.user: " + this.user)
        if (response.username!=null) {this.router.navigate(['/past-recs']);}
      },
      (err: any) => console.log(`Error: $(err)`)
    );

  }

  constructor(public userService: UserService,
              public user: User,
              public acceptedUser: User,
              public router: Router, 
              public nav: NavbarService) { }

  ngOnInit() {
    this.nav.hide();
  }


}
