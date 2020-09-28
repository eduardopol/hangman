import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/internal/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

const endpoint = 'http://localhost:8080/hangman/';

const httpOptions = {
  headers: new HttpHeaders({
    'Accept': '*/*',
    'Access-Control-Allow-Origin': '*',
    'Vary': 'Origin'
  })
}

export interface Game {
  id: number;
  word: string;
  playerName: string;
  wordGuessed: string;
  status: string;
  incorrectGuesses: number;
  inProgress: boolean;
  guesses: Guess[];
}

export interface Guess {
  guess?: string;
}

export interface NewGame {
  playerName?: string;
}

export interface Guess {
  guess?: string;
}

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

  getGame(id: number): Observable<any> {
    return this.http.get<Game>(endpoint + 'games/' + id, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  newGame(newGameRequest: NewGame): Observable<any> {
    return this.http.post(endpoint + 'games', newGameRequest, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  makeGuess(guess: Guess, id: number): Observable<any> {
    return this.http.post<Game>(endpoint + 'games/' + id + '/guess', guess, httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): any {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        'Backend returned code ${error.status}, ' +
        'body was: ${error.error}');
    }
    return throwError(
      'Something bad happened; please try again later.');
  }
}
