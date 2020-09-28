import { GamePage } from './page/game.po';
import { browser, by, element, logging, WebDriver } from 'protractor';
import { HttpClient } from "protractor-http-client";
import { HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { stringify } from 'querystring';
import { JsonPromise, ResponsePromise } from 'protractor-http-client/dist/promisewrappers';
import { protractor } from 'protractor/built/ptor';

describe('Game', () => {
  let page: GamePage;
  let word: string;
  let EC = protractor.ExpectedConditions;

  const httpOptions = {
    headers: new HttpHeaders({
      'Accept': '*/*',
      'Access-Control-Allow-Origin': '*',
      'Content-Type':  '*/*'
    })
  }
  
  const httpGame = new HttpClient("http://localhost:8080/hangman/games");

  beforeEach(() => {
    page = new GamePage();
  });
  
  it('should have a image and the word to be guessed', () => {
    expect(page.image.isPresent()).toBeTruthy();    
    expect(page.wordGuessed.isPresent()).toBeTruthy();
  });

  it('should make a guess and disable the button', () => {
    page.buttonA.click();
    expect(page.buttonA.isEnabled()).toBeFalsy();
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
