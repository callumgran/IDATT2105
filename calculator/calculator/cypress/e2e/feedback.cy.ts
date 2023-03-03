describe('My First Test', () => {
  it('visits the app root url', () => {
    cy.visit('/contact')

    cy.get('#name').type('Username')
    cy.get('#email').type('email@gmail.com')
    cy.get('#message').type('Hello! Nice calculator!')
    cy.get('#submit').click()

    cy.contains("#success", "Thank you for your feedback!")

  })

  it('Sjekker at knappen er disabled hvis informasjonen ikke er fylt ut', () => {
    cy.visit('/contact')

    cy.get('#name').type('myuser')
    cy.get('#message').type('feedback')
    cy.get('#submit').should('be.disabled')
  })
})