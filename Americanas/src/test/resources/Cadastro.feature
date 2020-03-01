Feature: Fazer cadastro no site

  Scenario: Fazer cadastro no site
    Given que acesso o site da loja Americanas
    When Clico em Cadastrar
    And preencho o e-mail "teste1253@teste.com.br"
    And preencho a senha "testefts"
    And preencho o CPF "11211905020"
    And preencho o nome e sobrenome "Joao da Silva"
    And preencho a data de nascimento "17/05/1990"
    And seleciono o sexo
    And preencho o telefone "17999999999"
    And clico no botao criar seu cadastro
    Then confirmo que o cadastro foi feito
