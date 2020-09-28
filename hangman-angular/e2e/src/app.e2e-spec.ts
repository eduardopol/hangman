import { AppPage } from './page/app.po';
import { browser, logging, WebDriver } from 'protractor';
import { HttpClient } from "protractor-http-client";

describe('Hangman App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display the home page', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('HangmanAngular');
  });

  it('should have new game elements', () => {
    expect(page.newGameText.isPresent()).toBeTruthy();
    expect(page.inputName.isPresent()).toBeTruthy();
    expect(page.newGameButton.isPresent()).toBeTruthy();
  });

  it('should require the player name', () => {
    page.newGameButton.click();
    expect(page.nameRequiredText.isDisplayed()).toBeTruthy();
  });

  it('should create a new game and navigate to game page', () => {
    page.inputName.sendKeys("Eduardo");
    page.newGameButton.click();
    browser.sleep(1500);
    expect(browser.getCurrentUrl()).toContain('/game/');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
