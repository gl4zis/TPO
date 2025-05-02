import {MainPage} from "../pages/MainPage";

const mainPage = new MainPage();

describe('Search tests', () => {
    beforeEach(() => {
        Cypress.on('uncaught:exception', () => { return false });
        cy.allure().epic("E2E Cypress").feature("Search");

        cy.allure().startStep("Visit Multitran");
        mainPage.visit();
        cy.allure().endStep();
    });

    it('searchWord', () => {
        cy.allure().story('Search word "dog"');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "dog" into search');
        mainPage.typeSearch('dog');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "dog"');
        mainPage.resultHasLink('dog');
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "собака"');
        mainPage.resultHasLink('собака');
        cy.allure().endStep();
    });

    it('emptySearch', () => {
        cy.allure().story('Empty search');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Submit empty search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Verify search input still here and empty');
        mainPage.isInputEmpty();
        cy.allure().endStep();
    });

    it('searchPhrase', () => {
        cy.allure().story('Search phrase "How are you?"');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "How are you?" into search');
        mainPage.typeSearch('How are you?');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "как дела?"');
        mainPage.resultHasLink('как дела?');
        cy.allure().endStep();
    });

    it('searchInvalidPhrase', () => {
        cy.allure().story('Search invalid phrase');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "How a ypu?" into search');
        mainPage.typeSearch('How a ypu?');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "how"');
        mainPage.resultHasLink('how');
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "only individual words found" message');
        mainPage.pageHasText('only individual words found');
        cy.allure().endStep();
    });

    it('searchOutputLangPhrase', () => {
        cy.allure().story('Search output language phrase');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "Как дела?" into search');
        mainPage.typeSearch('Как дела?');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Check input language is Russian now');
        mainPage.formHasText('Russian');
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "как дела?"');
        mainPage.resultHasLink('как дела?');
        cy.allure().endStep();
    });

    it('searchOtherLangPhrase', () => {
        cy.allure().story('Search other language phrase');

        cy.allure().startStep("Set up German-Russian mode");
        mainPage.setupGerRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "How are you?" into search');
        mainPage.typeSearch('How are you?');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Check input has no phrase');
        mainPage.resultHasNoLink('how are you?');
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "translation from other languages" message');
        mainPage.pageHasText('translation from other languages');
        cy.allure().endStep();
    });

    it('swapTranslationLanguages', () => {
        cy.allure().story('Swap translation languages');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "How are you?" into search');
        mainPage.typeSearch('How are you?');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Swap languages');
        mainPage.swapLanguages();
        cy.allure().endStep();

        cy.allure().startStep('Check search input is empty');
        mainPage.isInputEmpty();
        cy.allure().endStep();

        cy.allure().startStep('Check input language is Russian now');
        mainPage.formHasText('Russian');
        cy.allure().endStep();
    });

    it('goToFoundPhraseTranslation', () => {
        cy.allure().story('Go to found phrase translation');

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();

        cy.allure().startStep('Type "How are you?" into search');
        mainPage.typeSearch('How are you?');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Go to link "как дела?"')
        mainPage.goToResultLink('как дела?')
        cy.allure().endStep();

        cy.allure().startStep('Check input has value "как дела?"');
        mainPage.inputHasValue('как дела?');
        cy.allure().endStep();

        cy.allure().startStep('Check input language is Russian now');
        mainPage.formHasText('Russian');
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "how are you?"');
        mainPage.resultHasLink('how are you?');
        cy.allure().endStep();
    });

});