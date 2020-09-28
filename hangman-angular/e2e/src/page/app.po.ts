import { browser, by, element } from 'protractor';

export class AppPage {

  newGameText = element(by.id('new-game-text'));
  inputName = element(by.id('input-name'));
  newGameButton = element(by.id('new-game-button'));
  nameRequiredText = element(by.id('name-required'));

  navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl) as Promise<unknown>;
  }

  getTitleText(): Promise<string> {
    return browser.getTitle() as Promise<string>;
  }
  
  getTextNewGame(): Promise<string> {
    return browser.getPageSource() as Promise<string>;
  }
}
