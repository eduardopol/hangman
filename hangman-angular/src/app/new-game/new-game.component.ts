import { Component, OnInit, Input } from '@angular/core';
import { RestService, NewGame } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-new-game',
  templateUrl: './new-game.component.html',
  styleUrls: ['./new-game.component.css']
})
export class NewGameComponent implements OnInit {

  newGame: NewGame = {};
  showWarning: boolean;

  constructor(public rest: RestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.showWarning = false;
  }

  createGame(): void {
    if (this.newGame.playerName === undefined || this.newGame.playerName === null) {
      this.showWarning = true;
      return;
    }
    this.rest.newGame(this.newGame).subscribe((result) => {
      this.router.navigate(['/game/' + result.id]);
    }, (err) => {
      console.log(err);
    });
  }
}
