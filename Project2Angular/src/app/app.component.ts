import { Component } from '@angular/core';
import { User } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Recommend me a movie!';
  constructor(){ }

  ngOnInit(){
    
  }
  
}
