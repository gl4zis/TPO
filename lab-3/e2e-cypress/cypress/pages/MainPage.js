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

    resultHasWord(word) {
        return cy.xpath("//a[contains(text(), '" + word + "')]").should('be.visible');
    }

    isInputEmpty() {
        return this.searchInput().should('have.value', '');
    }

    setupEngRusMode() {
        this.engRusModeButton().click({ force: true });
    }

    typeSearch(term) {
        this.searchInput().type(term, { force: true });
    }

    submitSearch() {
        this.submitSearchButton().click({ force: true });
    }
}
