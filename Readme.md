# Dots And Boxes
Este projeto é uma implementação simples do jogo _Dots And Boxes_. Seu objetivo é demonstrar o uso do algoritmo minimax com poda alpha beta por um agente adversário.

## Instruções do Jogo
   
* Alternadamente, dois jogadores desenham segmentos de reta horizontais ou verticais, um por vez, unindo dois pontos vizinhos numa malha retangular pontilhada de qualquer dimensão.
    
* Sempre que escolher uma jogada, e um quadrado for fechado, é escrito no interior da “caixa” o código do jogador que fechou o quadrado e o jogador joga novamente. Se essa nova jogada não completar outra caixa, ele passa a vez e o jogo continua.

    ![Board](https://cdn.discordapp.com/attachments/753744531085852683/1094745509308158013/image.png)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;![Board2](https://cdn.discordapp.com/attachments/753744531085852683/1094749035686006986/image.png)

    * Jogadas possíveis: 1; 3; 10; 12; 14; 21; 23; 30; 32; 34; 41; 43;
    * Jogador 0: IA    
    * Jogador 1: Player    

* Se não houver mais jogadas a fazer, o jogo termina e o vencedor é aquele com o maior número de quadrados fechados.

## Estrutura do Código
O código foi dividido em duas classes App e Node.

### App
Classe na qual foi implementado como classe principal, é nela onde ocorre toda interação entre o jogador e o jogo.

* Funções:
    * printTable:  Uma simples implementação com estruturas de _for_ para  passar por cada elemento de uma matriz de inteiros, fazendo um "conversão" de _int_ para _string_.
    * fillTable:  Estrutura de _for_ para declarar valores a matriz _table_. Responsável pela criação do tabuleiro.
    * playGame:  Função na qual é responsável pela interação do jogador com o jogo, é nela que está chamando todas as outras funções do jogo. Contem o _loop_ responsável pelo jogo.
    * checkScore: Função que faz a contagem de quantos quadrados foram fechados por cada jogador, retornando a diferença entre os pontos da IA e do jogador. Tem relação direta com a função miniMax.
    * isValidMove: Função que verifica se a jogada informada pelo jogador é válida.
    * isSquareComplete: Função que verifica se na jogada escolhida pelo jogador ou pela IA fechou um quadrado, caso tenha fechado adiciona no meio do quadrado o código que representa o jogador ou a IA, e retorna _true_.
    * createAllTree: Função responsável por criar a árvore de possibilidades na qual a IA mapeia as jogadas possíveis. Tem relação direta com a classe Node.
    * cloneTable: Função que está responsável por clonar o tabuleiro e retornar o clone do tabuleiro do jogo.
    * miniMax: A partir do retorno da função _checkScore_, está função vai até aos nós folhas da árvore e retorna o valor obtido, maximizando as jogadas da IA e minimizando as jogadas do jogador.

### Node
Classe Node implementada para conter informações de cada nó da árvore.

* Funções:
    * fill: Função para criar os filhos de cada nó da árvore.
    * playGameIA: Função para simular as jogadas em cada nó verificando se o quadrado foi fechado ou não, caso não, alterna o jogador para que a ia continue com a simulação.

#### Erros
Infelizmente não consegui resolver o erro de _space heap_ com a árvore com 10 jogadas possíveis, está rodando somente com valores menores que 10 e a IA não é tão eficiente, não são todas as partidas que ela ganha.