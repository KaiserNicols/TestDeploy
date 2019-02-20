import {Component, OnInit} from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';


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
        console.log(this.username, this.password);
        console.log(response);
<<<<<<< HEAD
        //this.user.username = this.username;
        //this.user.password = this.password;
        console.log(this.username, this.password);
        console.log(this.user.username!=null);
        if (this.user.username!=null) {this.router.navigate(['/past-recs'])}  
=======
        if (this.user.username!=null) {this.router.navigate(['/past-recs']);}    
>>>>>>> 07d2f6cdb6771f5635095b9a8cef92b38de2b606
      },
      (err: any) => console.log(`Error: $(err)`)
    );

  }

  constructor(public userService: UserService,
              public user: User,
              public router: Router) { }

  ngOnInit() {
  }


}
