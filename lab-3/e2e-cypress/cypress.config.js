const { defineConfig } = require('cypress');
const allureWriter = require('@shelex/cypress-allure-plugin/writer');

module.exports = defineConfig({
  e2e: {
    baseUrl: 'https://www.multitran.com',
    specPattern: 'cypress/e2e/**/*.spec.js',
    setupNodeEvents(on, config) {
      allureWriter(on, config);
      return config;
    }
  },
  env: {
    supportFile: 'cypress/support/e2e.js',
    allure: true
  }
})