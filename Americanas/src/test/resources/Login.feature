Feature: Efetuar login  

Scenario: Efetuar login no site
  Given que acesso o site das Lojas Americanas
  And clico em Entrar
  And informo o e-mail "testefts125@gmail.com" 
  And informo a senha "fts1252020" e clico em Continuar
  Then confirmo o login no site 
  
