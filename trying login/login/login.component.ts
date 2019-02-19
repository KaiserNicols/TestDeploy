import {Component, OnInit} from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

// class UserAttempt {
//   userAttempt:{
//     username: string;
//     password: string;
//   }
// }

export class LoginComponent implements OnInit{

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

  // set username(name: string){
  //   this.user.firstname = name;
  // }

  // set password(pass: string){
  //   this.user.password = pass;
  // }

    userAttempt:{
      id: number;
      email: string;
      username: string;
      password: string;
      firstname: string;
      lastname: string;
    }


  login(): User {
    this.userService.loginUser(this.user).subscribe(
      response => {
        console.log(this.userAttempt);
        // @ts-ignore
        // this.user = response;
        
        console.log(response);
        this.user.setUsername(this.userAttempt.username);
        this.user.setPassword(this.userAttempt.password);
        console.log(this.user);
      
      },
      (err: any) => console.log(`Error: $(err)`)
    );
    return this.user;
   // if (this.user.id>-1) {this.router.navigate(['/past-recs']);}
  }


  constructor(public userService: UserService,
              public user: User,
              public router: Router) { }

  ngOnInit() {
    // this.userService.getUser().subscribe(
    //   //success
    //   data => this.user = data,
    //   //failure
    //   err => console.log(`Error: ${err}`))

    // this.userService.loginUser(this.user).subscribe(
    //   data => this.user = data,
    //   err => console.log(`Error: $(err)`)
    // )
  }


}
