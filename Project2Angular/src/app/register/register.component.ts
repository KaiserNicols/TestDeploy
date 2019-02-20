import {Component, OnInit} from '@angular/core';
import {User, UserService} from '../user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;
  firstname: string;
  lastname: string;
  email: string;

  register(): void{
    this.user.username = this.username;
    this.user.password = this.password;
    this.user.firstName = this.firstname;
    this.user.lastName = this.lastname;
    this.user.email = this.email;
    this.userService.registerUser(this.user).subscribe(
      response => {
        //console.log(this.user);
        //console.log(response);
        if (this.user!=null) {this.router.navigate(['/login']);}  
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
