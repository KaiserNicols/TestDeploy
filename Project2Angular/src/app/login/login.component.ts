import {Component, OnInit} from '@angular/core';
<<<<<<< HEAD
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';
=======
import {User, UserAttempt, UserService} from '../user.service';

>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit{

    username: string;
    password: string;

  login(): void {
    this.userService.loginUser(this.user).subscribe(
      response => {
<<<<<<< HEAD
        console.log(this.username, this.password);
        console.log(response);
        this.user.username = this.username;
        this.user.password = this.password;
        console.log(this.username, this.password);
        console.log(this.username, this.password);
        console.log(this.user.username!=null);
        if (this.user.username!=null) {this.router.navigate(['/past-recs']);}    
      },
=======
        // @ts-ignore
        this.user = response;
        },
>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514
      (err: any) => console.log(`Error: $(err)`)
    );

  }

<<<<<<< HEAD

  constructor(public userService: UserService,
              public user: User,
              public router: Router) { }
=======
  constructor(private userService: UserService) { }
>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514

  ngOnInit() {
  }


}
