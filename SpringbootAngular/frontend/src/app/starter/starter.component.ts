import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-starter',
  templateUrl: './starter.component.html',
  styleUrls: ['./starter.component.css']
})
export class StarterComponent implements OnInit {

  constructor(public router: Router) {}

  ngOnInit() {
  }

  logOut() {
    // this.authService.logOut();
    // localStorage.removeItem('currentUser');
    localStorage.removeItem('currentUser');
    this.router.navigate(['/logout']);
  }
}
