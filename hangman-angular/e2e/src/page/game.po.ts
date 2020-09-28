import { HttpHeaders } from '@angular/common/http';
import { browser, by, element } from 'protractor';
import { HttpClient } from 'protractor-http-client';
import { Observable } from 'rxjs';

export class GamePage {

  image = element(by.id('img'));
  wordGuessed = element(by.id('wordGuessed'));
  correctWord = element(by.id('correctWord'));
  twoChancesWarning = element(by.id('twoChancesWarning'));
  oneChanceWarning = element(by.id('oneChanceWarning'));
  lostText = element(by.id('lostText'));
  wonText = element(by.id('wonText'));
  gameControls = element(by.css('letter-button'));
  newGameButtons = element(by.id('newGameButtons'));
  newGame = element(by.id('newGame'));
  buttonA = element(by.id('buttonA'));
  buttonB = element(by.id('buttonB'));
  buttonC = element(by.id('buttonC'));
  buttonD = element(by.id('buttonD'));
  buttonE = element(by.id('buttonE'));
  buttonF = element(by.id('buttonF'));
  buttonG = element(by.id('buttonG'));
  buttonH = element(by.id('buttonH'));
  buttonI = element(by.id('buttonI'));
  buttonJ = element(by.id('buttonJ'));
  buttonK = element(by.id('buttonK'));
  buttonL = element(by.id('buttonL'));
  buttonM = element(by.id('buttonM'));
  buttonN = element(by.id('buttonN'));
  buttonO = element(by.id('buttonO'));
  buttonP = element(by.id('buttonP'));
  buttonQ = element(by.id('buttonQ'));
  buttonR = element(by.id('buttonR'));
  buttonS = element(by.id('buttonS'));
  buttonT = element(by.id('buttonT'));
  buttonU = element(by.id('buttonU'));
  buttonV = element(by.id('buttonV'));
  buttonW = element(by.id('buttonW'));
  buttonX = element(by.id('buttonX'));
  buttonY = element(by.id('buttonY'));
  buttonZ = element(by.id('buttonZ'));

  http: HttpClient;
  endpoint = 'http://localhost:8080/hangman/';
  httpOptions = {
    headers: new HttpHeaders({
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
      'Vary': 'Origin',
      'Content-Type':  'application/json'
    })
  }

  navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl) as Promise<unknown>;
  }

  isWordGuessedDisplayed(): boolean {
    this.wordGuessed.isDisplayed().then(function(visible) {
      return visible;
    });
    return false;
  }
}
