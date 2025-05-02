export class MainPage {

    visit() {
        cy.visit('/');
    }

    searchInput() {
        return cy.xpath("//input[@name='s']");
    }

    submitSearchButton() {
        return cy.xpath("//input[@type='submit']");
    }

    engRusModeButton() {
        return cy.xpath("//a[@href='/m.exe?l1=1&l2=2']");
    }

    gerRusModeButton() {
        return cy.xpath("//a[@href='/m.exe?l1=3&l2=2']");
    }

    searchForm() {
        return cy.xpath("//div[@id='start']/div[5]/form");
    }

    swapLangButton() {
        return cy.xpath("//a[contains(text(),'⇄')]");
    }

    formHasText(text) {
        return this.searchForm().contains(text).should('be.visible');
    }

    pageHasText(text) {
        return cy.contains(text).should('be.visible');
    }

    resultHasLink(text) {
        return cy.xpath("//a[contains(text(), '" + text + "')]").should('be.visible');
    }

    goToResultLink(text) {
        cy.xpath("//a[contains(text(), '" + text + "')]").click({ force: true });
    }

    resultHasNoLink(word) {
        return cy.xpath("//a[contains(text(), '" + word + "')]").should('not.exist');
    }

    isInputEmpty() {
        return this.inputHasValue('');
    }

    inputHasValue(value) {
        return this.searchInput().should('have.value', value);
    }

    setupEngRusMode() {
        this.engRusModeButton().click({ force: true });
    }

    setupGerRusMode() {
        this.gerRusModeButton().click({ force: true });
    }

    typeSearch(term) {
        this.searchInput().type(term, { force: true });
    }

    submitSearch() {
        this.submitSearchButton().click({ force: true });
    }

    swapLanguages() {
        this.swapLangButton().click({ force: true });
    }
}
