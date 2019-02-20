import { Component, OnInit } from '@angular/core';
import { NavbarService } from '../navbar.service';

@Component({
  selector: 'app-new-rec',
  templateUrl: './new-rec.component.html',
  styleUrls: ['./new-rec.component.css']
})
export class NewRecComponent implements OnInit {

  title = "Get a Recommendation";
  constructor(public nav: NavbarService) { }

  ngOnInit() {
  this.nav.show();}

}
