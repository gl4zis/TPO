import {MainPage} from "../pages/MainPage";

const mainPage = new MainPage();

describe('Search tests', () => {
    beforeEach(() => {
        Cypress.on('uncaught:exception', () => { return false });
        cy.allure().startStep("Visit Multitran");
        mainPage.visit();
        cy.allure().endStep();

        cy.allure().startStep("Set up English-Russian mode");
        mainPage.setupEngRusMode();
        cy.allure().endStep();
    });

    it('Search word "dog"', () => {
        cy.allure().startStep('Type "dog" into search');
        mainPage.typeSearch('dog');
        cy.allure().endStep();

        cy.allure().startStep('Submit search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "dog"');
        mainPage.resultHasWord('dog');
        cy.allure().endStep();

        cy.allure().startStep('Check result contains "собака"');
        mainPage.resultHasWord('собака');
        cy.allure().endStep();
    });

    it('Empty search', () => {
        cy.allure().startStep('Submit empty search');
        mainPage.submitSearch();
        cy.allure().endStep();

        cy.allure().startStep('Verify search input still here and empty');
        mainPage.isInputEmpty();
        cy.allure().endStep();
    });
});