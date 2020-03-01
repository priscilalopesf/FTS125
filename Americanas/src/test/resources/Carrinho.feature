Feature: Buscar produto e adicionar no carrinho 

Scenario: Adicionar livro no carrinho
    Given que acesso o site da Americanas
    When digito o termo "livro harry potter" e pressiono Enter
    And visualizo a lista de produtos e clico no box
    And visualizo os dados do produto e clico em comprar
    Then adiciono o item no carrinho 
    