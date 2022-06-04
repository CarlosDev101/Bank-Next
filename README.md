<div align="center">
  <img src="https://media2.giphy.com/media/e6tA359EUw2kqhOBHL/giphy.gif?cid=ecf05e479dkyhe5tufa2k0dxzjng9xuh3wv78vv9c36jnqyc&rid=giphy.gif&ct=s" width="300px">
</div>

<h1 align="center">🪙 Banco-Next 🪙</h1>

<div align="center">
    <img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/m/CarlosDev101/Bank-Next">
</div>

<div align="center">
  Sejam bem-vindos ao <b>Banco Next!</b> O Banco Next faz parte de um projeto na qual eu criei um banco usando Java conectado com um Banco de dados chamado <b>H2</b>
</div>

<p align="center">
  <a href="https://github.com/CarlosDev101/Bank-Next/blob/main/README.md">Features</a> •
  <a href="https://github.com/CarlosDev101">Author</a>
</p>

<h1 align="center">  Features   </h2>

<div align="center">
  Todos os que estão marcados já se encontram presente no <b>Banco-Next</b> funcionando. 
</div>

  - <h2 width="30x">[🪙] Depósito</h2>
  - <h2 width="30x">[🪙] Transferencia</h2>
  - <h2 width="30x">[🪙] Saque</h2>


<div align="center">
  <h2>☕ Primeiro Passo - Instalando o Java ☕</h2>
  
  Antes de qualquer coisa, iremos instalar em nosso computador o <b>Java</b>. Java é uma linguagem de programação que vai lhe permitir utilizar este programa.<a href="https://java.com/pt-BR/download/ie_manual.jsp" target="_blank"> Instale o Java apertando aqui</a>. 
  
  Após a instalação do Java você vai instalar o JRE. O JRE é uma camada de software que é executado sobre um software de sistema operacional de um computador. <a href="https://www.oracle.com/java/technologies/install-windows.html" target="_blank"> Instale o JRE apertando aqui</a>. 
  
  Após a instalação do JRE você vai instalar o JDK. Este pacote é disponibilizado pela Oracle, e nele vem todo o ambiente necessário para a criação e execução dos aplicativos java. <a href="https://www.oracle.com/java/technologies/downloads/" target="_blank"> Instale o JDK apertando aqui</a>. 
</div>

<hr>

<div align="center">
  <h2>💻 Segundo Passo - Instalando o H2 💻</h2>
  Após a instalação do <b>Java, JRE e JDK</b>, iremos instalar em nosso computador um Banco de dados Chamado <b>H2</b>. Este banco de dados vai servir para salvar os cadastros que iremos inserir em nosso programa. <a href="http://h2database.com/html/download.html" target="_blank">Baixe o H2 apertando aqui</a>. 
  
  Após ter instalado, você vai abrir o CMD e digitar 
  
  	 cd C:\Program Files\H2\bin
  
  Em seguida digite
  
    java -cp h2-2.1.212.jar org.h2.tools.Shell
  
  Após isso você vai seguir estes passos

    [Enter]  jdbc:h2:mem2
  
      URL  jdbc:h2:~/FiservBD
  
    [Enter]  org.h2.Driver
  
    Driver  (aperta apenas enter)
  
    [Enter]  sa
  
    User  Carlos
  
    Password  (aperta apenas enter)
  
    Type the same password again to confirm database creation.
  
    Password  (aperta apenas enter)
  
    Connected
  
    sql> quit

   E feche o CMD 
  
</div>

<hr>

<div align="center">
  <h2>👛 Terceiro Passo - Criando um banco de dados 👛</h2>
  
  Após fechar o CMD você vai na barra de pesquisa do windows e digita <b>H2</b>. Após apertar nele, você vai ser jogado pro navegador e lá você vai apertar <b>Conect</b>.
  
  Quando você entrar no banco de dados você vai digitar
  
    CREATE TABLE CLIENTES(
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(60) NOT NULL,
    CPF VARCHAR(11) NOT NULL UNIQUE,
    SENHA VARCHAR(6) NOT NULL,
    EMAIL VARCHAR(60) NOT NULL UNIQUE
    );

    CREATE TABLE CONTA(
    ID  INT AUTO_INCREMENT PRIMARY KEY,
    NUMERO VARCHAR(6) NOT NULL UNIQUE,
    SALDO DOUBLE,
    FOREIGN KEY (ID) REFERENCES CLIENTES(ID)
    )
  
  Após você  digitar o código acima, aperte <b>Run</b> para rodar o código. Depois que você rodar o código vai criar duas tabelas. <b>Clientes</b> e <b>Conta</b>. Em seguida feche o navegador não se esqueça de fechar ele também no gerenciador de tarefas. 
</div>

<hr>

<div align="center">
  <h2>📥 Quarto Passo - Instalando uma IDE 📥</h2>
  
  Você vai precisar instalar uma IDE para rodar o programa, caso você já tenha instalado apenas ignore este tópico. Iremos usar a IDE eclipse, está IDE vai rodar os códigos em Java. <a href="https://www.eclipse.org/downloads/" target="_blank">Baixe o eclipse apertando aqui</a>. 
</div>

<hr>

<div align="center">
  <h2>▶️ Quinto Passo - Executando o código (Final) ▶️</h2>
  
  Após a instalação do Eclipse você vai baixar o código do Banco aqui do GitHub. Depois extraia e jogue no Eclipse, você vai entrar na pasta
  
    src/br/com/projetonext/view/Application.java
  
  E rode o arquivo.
  
</div>  

<hr>

<div align="center">
  <h2>🤓 Observações 🤓</h2>
  
  Comecei a estudar Java faz 3 meses, e decidi criar este projeto. Levei em torno de 1/2 semanas para conclui-lo.
</div> 
