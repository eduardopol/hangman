import { Component, HostListener, OnInit} from '@angular/core';
import { RestService, Game, Guess, NewGame } from '../rest.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  public game: Game;
  public guess: Guess = {};
  public buttonLetters: string[];
  public images: string[];
  public letters: string[];
  public usedLetters: string[];
  
  constructor(
    public rest: RestService, 
    private route: ActivatedRoute, 
    private router: Router) { }

  ngOnInit(): void {
    this.images = ['../../assets/galge.png', '../../assets/forkert1.png',
                   '../../assets/forkert2.png', '../../assets/forkert3.png', '../../assets/forkert4.png',
                   '../../assets/forkert5.png', '../../assets/forkert6.png'];

    this.letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                   'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    this.rest.getGame(this.route.snapshot.params.id).subscribe(
      (data: Game) => {
        this.game = { ...data };
        this.usedLetters = this.game === undefined ? [''] : this.game.guesses.map(guess => guess.guess);
      }, (err) => {
        this.router.navigate(['/new-game/']);
      }
    );

    this.buttonLetters = this.letters;
  }

  makeGuess(letter: string): void {    
    this.guess.guess = letter;
    
    this.rest.makeGuess(this.guess, this.game.id).subscribe((result) => {
      this.game = result;
      this.guess = {};
      this.usedLetters = this.game === undefined ? [''] : this.game.guesses.map(guess => guess.guess);
    }, (err) => {
      console.log(err);
    });
    
  }
  
  onLetterClick(letter: string) {
    this.makeGuess(letter);
  }

  newPlayer() {
    this.router.navigate(['/new-game/']);
  }

  createGame(): void {
    let newGame: NewGame = { playerName: this.game.playerName };

    this.rest.newGame(newGame).subscribe((result) => {
      console.log(result);
      this.router.navigate(['/game/' + result.id]).then(page => { window.location.reload(); });
    }, (err) => {
      console.log(err);
    });
  }
  
  keyPressed(bLetter) {
    return !(this.usedLetters.indexOf(bLetter) >= 0) && (this.letters.indexOf(bLetter) >= 0);
  }

  @HostListener('window:keyup', ['$event'])
  keyEvent(event: KeyboardEvent) {
    if (this.keyPressed(event.key.toUpperCase())) {
      this.makeGuess(event.key);
    }
  }
}
