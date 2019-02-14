import { Component, OnInit } from '@angular/core';
import { LoginService, User } from 'src/app/services/login-service/login-service.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  private user: User;

  constructor(private LoginService: LoginService){}

  ngOnInit() {
    this.LoginService.getUser().subscribe(
      //success
      data => this.user = data,
      //failure
      err => console.log(`Error: ${err}`))
  }

}
