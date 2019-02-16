import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-rec',
  templateUrl: './new-rec.component.html',
  styleUrls: ['./new-rec.component.css']
})
export class NewRecComponent implements OnInit {

  title = "Get a Recommendation";
  constructor() { }

  ngOnInit() {
  }

}
