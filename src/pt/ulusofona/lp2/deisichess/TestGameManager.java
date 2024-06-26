package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.jvm.functions.Function1;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static pt.ulusofona.lp2.deisichess.StatisticsKt.*;


public class TestGameManager {

    public GameManager jogo = new GameManager();


    @Test
    public void loadGame8x8() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Rei(1,0,10,"O Poderoso Chefao");
        peca1.setCoordenadas(0,0);
        pecasTeste.add(peca1);

        Peca peca2 = new Rainha(2,1,10,"A Dama Selvagem");
        peca2.setCoordenadas(1,0);
        pecasTeste.add(peca2);

        Peca peca3 = new PoneiMagico(3,2,10,"O Grande Artista");
        peca3.setCoordenadas(2,0);
        pecasTeste.add(peca3);

        Peca peca4 = new PadreDaVila(4,3,10,"Amante de Praia");
        peca4.setCoordenadas(3,0);
        pecasTeste.add(peca4);

        Peca peca5 = new TorreHorizontal(5,4,10,"Artolas");
        peca5.setCoordenadas(4,0);
        pecasTeste.add(peca5);

        Peca peca6 = new TorreVertical(6,5,10,"O Maior Grande");
        peca6.setCoordenadas(5,0);
        pecasTeste.add(peca6);

        Peca peca7 = new HomerSimpson(7,6,10,"Hommie");
        peca7.setCoordenadas(6,0);
        pecasTeste.add(peca7);

        Peca peca8 = new Joker(8,7,10,"O Beberolas");
        peca8.setCoordenadas(7,0);
        pecasTeste.add(peca8);

        Peca peca9 = new Rei(9,0,20,"Chefe dos Indios");
        peca9.setCoordenadas(0,7);
        pecasTeste.add(peca9);

        Peca peca10 = new Rainha(10,1,20,"A Barulhenta do Bairro");
        peca10.setCoordenadas(1,7);
        pecasTeste.add(peca10);

        Peca peca11 = new PoneiMagico(11,2,20,"My Little Pony");
        peca11.setCoordenadas(2,7);
        pecasTeste.add(peca11);

        Peca peca12 = new PadreDaVila(12,3,20,"Padreco");
        peca12.setCoordenadas(3,7);
        pecasTeste.add(peca12);

        Peca peca13 = new TorreHorizontal(13,4,20,"Torre Padreco");
        peca13.setCoordenadas(4,7);
        pecasTeste.add(peca13);

        Peca peca14 = new TorreVertical(14,5,20,"Torre Trapalhona");
        peca14.setCoordenadas(5,7);
        pecasTeste.add(peca14);

        Peca peca15 = new HomerSimpson(15,6,20,"Homer Jay Simpson");
        peca15.setCoordenadas(6,7);
        pecasTeste.add(peca15);

        Peca peca16 = new Joker(16,7,20,"O Bobo da Corte");
        peca16.setCoordenadas(7,7);
        pecasTeste.add(peca16);

        int countPecasIguais = 0;
        for(Peca peca : pecasTeste){
            for(Peca pecaTabuleiro : tabuleiro.getListaPecas()){
                if(peca.equals(pecaTabuleiro)){
                    countPecasIguais++;
                }

            }

        }

        assertEquals(16,countPecasIguais);

    }

    @Test
    public void loadComErrosAMais()throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8ErrosAMais.txt");
        jogo.reset();
        String output = "";

        try {
            jogo.loadGame(file);
        } catch (IOException IO) {

        } catch (InvalidGameInputException e) {
            output = "Ocorreu um erro a ler o ficheiro, na linha " + e.getLineWithError() + " com o seguinte problema: "
                    + e.getProblemDescription();
        }

        String esperado = "Ocorreu um erro a ler o ficheiro, na linha 20 com o seguinte problema: DADOS A MAIS (Esperava: 8 ; Obtive: 9)";

        assertEquals(esperado,output);


    }

    @Test
    public void loadComErrosAMenos()throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8ErrosAMenos.txt");
        jogo.reset();
        String output = "";

        try {
            jogo.loadGame(file);
        } catch (IOException var6) {

        } catch (InvalidGameInputException e) {
            output = "Ocorreu um erro a ler o ficheiro, na linha " + e.getLineWithError() + " com o seguinte problema: "
                    + e.getProblemDescription();
        }

        String esperado =  "Ocorreu um erro a ler o ficheiro, na linha 6 com o seguinte problema: DADOS A MENOS (Esperava: 4 ; Obtive: 3)";


        assertEquals(esperado,output);


    }


    @Test
    public void testGetSquareInfo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para coordenadas inválidas---------------------
        String[] resultInvalid = jogo.getSquareInfo(10, 20);
        assertNull(resultInvalid);


        // -------------------Teste para coordenadas com peça-------------------------------
        String[] resultComPeca = jogo.getSquareInfo(0, 0);
        assertArrayEquals(new String[]{"1", "0", "10", "O Poderoso Chefao", "ReiPreto.png"}, resultComPeca);


        // ------------------ Teste para coordenadas sem peça-----------------------------
        String[] resultSemPeca = jogo.getSquareInfo(2, 2);
        assertArrayEquals(new String[]{}, resultSemPeca);
    }


    @Test
    public void testGetPieceInfo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para peça inválida---------------------
        String[] resultInvalid = jogo.getPieceInfo(20);
        assertArrayEquals(new String[7], resultInvalid);


        // -------------------Teste para peça válida-------------------------------
        String[] resultComPeca = jogo.getPieceInfo(1);
        assertArrayEquals(new String[]{"1", "0", "10", "O Poderoso Chefao","em jogo","0","0"}, resultComPeca);

    }

    @Test
    public void testGetPieceInfoAsString() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para peça inválida---------------------
        String resultInvalid = jogo.getPieceInfoAsString(20);
        assertNull(resultInvalid);


        // -------------------Teste para peça válida-------------------------------
        String resultComPeca = jogo.getPieceInfoAsString(1);
        assertEquals("1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (0, 0)", resultComPeca);



    }


    @Test
    public void movePoneiMagico() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean move;
        boolean gameOver;

        move =jogo.move(2,0,4,2 );  //move ponei preto
        assertTrue(move);
        boolean gameOver1 = jogo.gameOver();
        assertFalse(gameOver1);


        move = jogo.move(1,7,1,5 ); // move rainha branco
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(4,2,3,3 ); // move ponei preto
        assertFalse(move); // Retorna false movimento invalido
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(4,2,6,0 ); // move ponei pretp
        assertFalse(move); // Retorna false posicao com peca da mesma equipa
        gameOver = jogo.gameOver();
        assertFalse(gameOver);


        move =jogo.move(4,2,6,4 ); // move ponei preto
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);


        move =jogo.move(5,7,5,4 ); // move tv branca
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);


        move =jogo.move(1,0,1,1 );// move rei preto
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(6,7,5,6 );// move hommer branco
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(6,4,4,6 );// move ponei Preto
        assertFalse(move); // retorna false tem duas pecas em cada caminho possivel
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        String resultComPeca = jogo.getPieceInfoAsString(3);
        assertEquals("3 | Ponei Mágico | 5 | 10 | O Grande Artista @ (6, 4)", resultComPeca);

    }


    @Test
    public void moveHomer() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean move;
        boolean gameOver;
        String resultComPeca = "";

        move =jogo.move(6,0,7,1 );  //move homer preto
        assertFalse(move); //retorna false  ta a dormir
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(15);
        assertEquals("Doh! zzzzzz", resultComPeca);

        move =jogo.move(5,0,5,4 );  //move tv preta
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(6,7,6,6 );  //move homer branca
        assertFalse(move);//retorna false // nao anda na vertical
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(6,7,7,6 );  //move homer branca
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(1,0,1,5 );  //move rainha preta
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(5,7,5,6 );  //move tv branca
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(4,0,5,0 );  //move th preta
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(5);
        assertEquals("5 | TorreHor | 3 | 10 | Artolas @ (5, 0)", resultComPeca);


        move =jogo.move(7,6,6,5 );  //move homer branca
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(1,5,0,5 );  //move rainha preta
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        move =jogo.move(6,5,5,4 );  //move homer branca captura TV preta
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(15);
        assertEquals("15 | Homer Simpson | 2 | 20 | Homer Jay Simpson @ (5, 4)", resultComPeca);

        move =jogo.move(0,5,0,7 );  //move rainha preta captura rei branco
        assertTrue(move);
        gameOver = jogo.gameOver();
        assertTrue(gameOver);



    }


    @Test
    public void moveJoker() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);
        Tabuleiro tabuleiro = jogo.tabuleiro;
        boolean move;
        boolean gameOver;
        String resultComPeca = "";


        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/Rainha | 4 | 10 | O Beberolas @ (7, 0)", resultComPeca);

        move = jogo.move(7,0,7,1 );//joker(rainha) anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/Ponei Mágico | 4 | 10 | O Beberolas @ (7, 1)", resultComPeca);


        move = jogo.move(0,7,0,6);//rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/Padre da Vila | 4 | 10 | O Beberolas @ (7, 1)", resultComPeca);


        move = jogo.move(7,1,7,3);//joker(padre da vila) movimento invalido
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(7,1,5,3);//joker(padre da vila) anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/TorreHor | 4 | 10 | O Beberolas @ (5, 3)", resultComPeca);


        move = jogo.move(0,6,0,7); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/TorreVert | 4 | 10 | O Beberolas @ (5, 3)", resultComPeca);


        move = jogo.move(5,3,5,7); //joker(TV) preto anda e captura TV branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/Homer Simpson | 4 | 10 | O Beberolas @ (5, 7)", resultComPeca);


        move = jogo.move(0,7,0,6);//rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(8);
        assertEquals("8 | Joker/Rainha | 4 | 10 | O Beberolas @ (5, 7)", resultComPeca);


        move = jogo.move(5,7,4,7 );//joker(rainha) anda e captura TH branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,6,0,7); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        jogo.undo();

        move = jogo.move(0,6,0,7); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(4,7,1,4);//joker(padre da vila) anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,7,1,6); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,4,1,6); //joker(TV) preto anda e captura rei branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertTrue(gameOver);
    }


    @Test
    public void Undo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        jogo.move(1,0,1,5 );
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);
        jogo.move(1,7,1,5 );
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);
        jogo.undo();
        jogo.undo();

    }


    @Test
    public void testContadorRondasUndo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;


        boolean mov1 = jogo.move(1,0,1,5 );
        assertTrue(mov1);
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

        ContadorRondas contadorRondas = tabuleiro.getContadorRondas();
        int rondaAtual = contadorRondas.getRondaAtual();
        int rondaJoker = contadorRondas.getRondasJoker();
        int rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(1,rondaAtual);
        assertEquals(2,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov2 =jogo.move(1,7,1,5 );
        assertFalse(mov2); //rainha nao come rainha
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(1,rondaAtual);
        assertEquals(2,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov3 =jogo.move(2,0,4,2 );
        assertFalse(mov3); // equipa branca a jogar ainda
        boolean jogada3 = jogo.gameOver();
        assertFalse(jogada3);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(1,rondaAtual);
        assertEquals(2,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov4 =jogo.move(3,7,6,4 );
        assertTrue(mov4);
        boolean jogada4 = jogo.gameOver();
        assertFalse(jogada4);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(2,rondaAtual);
        assertEquals(3,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov5 =jogo.move(5,0,5,1 );
        assertTrue(mov5);
        boolean jogada5 = jogo.gameOver();
        assertFalse(jogada5);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(3,rondaAtual);
        assertEquals(4,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov6 =jogo.move(6,4,4,2 );
        assertTrue(mov6);
        boolean jogada6 = jogo.gameOver();
        assertFalse(jogada6);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(4,rondaAtual);
        assertEquals(5,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        jogo.undo();
        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(3,rondaAtual);
        assertEquals(4,rondaJoker);
        assertEquals(0,rondasSemCaptura);

        jogo.undo();
        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(2,rondaAtual);
        assertEquals(3,rondaJoker);
        assertEquals(0,rondasSemCaptura);

        jogo.getPieceInfo(8);
        jogo.getPieceInfoAsString(8);
        jogo.getSquareInfo(7,0);

    }




    @Test
    public void soReis()throws IOException, InvalidGameInputException {
        File file = new File("test-files","4x4pequeno.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean mov1 = jogo.move(2,1,1,2 );
        assertTrue(mov1);
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

        boolean mov2 = jogo.move(1,3,1,2 );
        assertTrue(mov2);
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);

        boolean mov3 = jogo.move(1,0,1,1 );
        assertTrue(mov3);
        boolean jogada3 = jogo.gameOver();
        assertFalse(jogada3);

        boolean mov4 = jogo.move(1,2,2,2 );
        assertTrue(mov4);
        boolean jogada4 = jogo.gameOver();
        assertFalse(jogada4);

        boolean mov5 = jogo.move(1,1,2,2 );
        assertTrue(mov5);
        boolean jogada5 = jogo.gameOver();
        assertFalse(jogada5);

        boolean mov6 = jogo.move(2,3,2,2 );
        assertTrue(mov6);
        boolean jogada6 = jogo.gameOver();
        assertTrue(jogada6);

    }

    @Test
    public void estatisticasDaPecaRainhaPreta()throws IOException, InvalidGameInputException {
        File file = new File("test-files", "8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean move;
        boolean gameOver;

        move = jogo.move(1,0,1,5);//rainha preta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,7,0,6); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,5,1,7);//rainha preta n anda pois n pode comer rainha branca
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        Peca peca = tabuleiro.getPeca(1,5);
        CountJogadas countJogadas = peca.getCountJogadas();
        int jogadasValidas = countJogadas.getJogadasValidas();
        int jogadasInvalidas = countJogadas.getJogadasInvalidas();

        assertEquals(1,jogadasValidas);
        assertEquals(1,jogadasInvalidas);


        move = jogo.move(1,5,3,7);//rainha preta anda e captura PV branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,6,0,7); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(3,7,2,7);//rainha preta anda e captura PM branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,7,0,6); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(2,7,1,7);//rainha preta n anda pois n pode comer rainha branca
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(2,7,1,6);//rainha preta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,6,0,7); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,6,0,7);//rainha preta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertTrue(gameOver);

        peca = tabuleiro.getPeca(0,7);
        ArrayList<Integer> pecasQueCaptorou = peca.getListaPecasCapturadas();


        countJogadas = peca.getCountJogadas();
        jogadasValidas = countJogadas.getJogadasValidas();
        jogadasInvalidas = countJogadas.getJogadasInvalidas();

        ArrayList<Integer> esperado = new ArrayList<>();
        esperado.add(12);
        esperado.add(11);
        esperado.add(9);

        assertEquals(5,jogadasValidas);
        assertEquals(2,jogadasInvalidas);
        assertArrayEquals(esperado.toArray(),pecasQueCaptorou.toArray());
    }


    @Test
    public void testQuerysStatisticskt()throws IOException, InvalidGameInputException {
        File file = new File("test-files", "8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        String resultComPeca = "";
        boolean move;
        boolean gameOver;


        move = jogo.move(1,0,6,5);//rainha preta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(6,7,7,6); //Hommer branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(6,5,0,6); //rainha preta invalido
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(6,5,7,6);//rainha preta anda e captura Hommer branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(7,7,7,6); //joker branco Invalido
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(5,7,5,0);//TorreVer Branca come TorreVer Preta
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(7,6,7,7); //rainha Preta come Joker Branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(5,0,7,6);//TorreVer Branca invalido
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(5,0,5,7);//TorreVer Branca anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(14);
        assertEquals("14 | TorreVert | 3 | 20 | Torre Trapalhona @ (5, 7)", resultComPeca);


        move = jogo.move(7,7,5,7); //rainha Preta come TorreVert Branca
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(3,7,2,6);//Padre Branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(12);
        assertEquals("12 | Padre da Vila | 3 | 20 | Padreco @ (2, 6)", resultComPeca);

        move = jogo.move(5,7,4,7); //rainha Preta come TorreHor Branca
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(2,6,3,7); //Padre Branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(4,7,3,7); //rainha Preta come Padre Branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(2);
        assertEquals("2 | Rainha | 8 | 10 | A Dama Selvagem @ (3, 7)", resultComPeca);

        move = jogo.move(0,7,0,6); //Rei Branco move
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(3,7,2,7); //rainha Preta come Ponei Branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        //----------------------TOP5CAPTURAS--------------------------------------//

        Function1<GameManager, ArrayList<String>> calculator = getStatsCalculator(StatType.TOP_5_CAPTURAS);
        ArrayList<String> result = queryTOP5capturas(jogo);

        ArrayList<String> arrayString = new ArrayList<>();
        String peca1 = "A Dama Selvagem (PRETA) fez 6 capturas";
        arrayString.add(peca1);
        String peca2 = "Torre Trapalhona (BRANCA) fez 1 capturas";
        arrayString.add(peca2);
        String peca3 = "O Poderoso Chefao (PRETA) fez 0 capturas";
        arrayString.add(peca3);
        String peca4 = "O Grande Artista (PRETA) fez 0 capturas";
        arrayString.add(peca4);
        String peca5 = "Amante de Praia (PRETA) fez 0 capturas";
        arrayString.add(peca5);

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------TOP5PONTOS---------------------------------------//

        calculator = getStatsCalculator(StatType.TOP_5_PONTOS);
        result = queryTOP5pontos(jogo);

        arrayString = new ArrayList<>();
        peca1 = "A Dama Selvagem (PRETA) tem 20 pontos";
        arrayString.add(peca1);
        peca2 = "Torre Trapalhona (BRANCA) tem 3 pontos";
        arrayString.add(peca2);

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------PecasCom+5Capturas------------------------//

        calculator = getStatsCalculator(StatType.PECAS_MAIS_5_CAPTURAS);
        result = queryPecasComMais5capturas(jogo);

        arrayString = new ArrayList<>();
        peca1 = "PRETA:A Dama Selvagem:6";
        arrayString.add(peca1);


        assertArrayEquals(result.toArray(), arrayString.toArray());


        //----------------------TOP3PecasMaisBARALHADAS----------------------//

        calculator = getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS);
        result = queryTOP3Barralhadas(jogo);

        arrayString = new ArrayList<>();
        peca1 = "20:O Bobo da Corte:1:0";
        arrayString.add(peca1);
        peca2 = "20:Torre Trapalhona:1:2";
        arrayString.add(peca2);
        peca3 = "10:A Dama Selvagem:1:7";
        arrayString.add(peca3);

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------TiposPecasCapturados-------------------------//

        calculator = getStatsCalculator(StatType.TIPOS_CAPTURADOS);
        result = queryTiposPecasCapturados(jogo);

        arrayString = new ArrayList<>();
        peca1 = "Homer Simpson";
        arrayString.add(peca1);
        peca2 = "Joker/Ponei Mágico";
        arrayString.add(peca2);
        peca3 = "Padre da Vila";
        arrayString.add(peca3);
        String peca6 = "Ponei Mágico";
        arrayString.add(peca6);
        peca4 = "TorreHor";
        arrayString.add(peca4);
        peca5 = "TorreVert";
        arrayString.add(peca5);

        assertArrayEquals(result.toArray(), arrayString.toArray());
        //------------------------Undo------------------------------------------
        jogo.undo();    //UNDOOOOOOO !!!!!!!!!!!!

        //----------------------TOP5CAPTURAS--------------------------------------//

        calculator = getStatsCalculator(StatType.TOP_5_CAPTURAS);
        result = queryTOP5capturas(jogo);

        arrayString = new ArrayList<>();
        peca1 = "A Dama Selvagem (PRETA) fez 5 capturas";
        arrayString.add(peca1);
        peca2 = "Torre Trapalhona (BRANCA) fez 1 capturas";
        arrayString.add(peca2);
        peca3 = "O Poderoso Chefao (PRETA) fez 0 capturas";
        arrayString.add(peca3);
        peca4 = "O Grande Artista (PRETA) fez 0 capturas";
        arrayString.add(peca4);
        peca5 = "Amante de Praia (PRETA) fez 0 capturas";
        arrayString.add(peca5);

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------TOP5PONTOS---------------------------------------//

        calculator = getStatsCalculator(StatType.TOP_5_PONTOS);
        result = queryTOP5pontos(jogo);

        arrayString = new ArrayList<>();
        peca1 = "A Dama Selvagem (PRETA) tem 15 pontos";
        arrayString.add(peca1);
        peca2 = "Torre Trapalhona (BRANCA) tem 3 pontos";
        arrayString.add(peca2);

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------PecasCom+5Capturas------------------------//

        calculator = getStatsCalculator(StatType.PECAS_MAIS_5_CAPTURAS);
        result = queryPecasComMais5capturas(jogo);

        arrayString = new ArrayList<>();

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------TOP3PecasMaisBARALHADAS----------------------//

        calculator = getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS);
        result = queryTOP3Barralhadas(jogo);

        arrayString = new ArrayList<>();
        peca1 = "20:O Bobo da Corte:1:0";
        arrayString.add(peca1);
        peca2 = "20:Torre Trapalhona:1:2";
        arrayString.add(peca2);
        peca3 = "10:A Dama Selvagem:1:6";
        arrayString.add(peca3);

        assertArrayEquals(result.toArray(), arrayString.toArray());

        //----------------------TiposPecasCapturados-------------------------//

        calculator = getStatsCalculator(StatType.TIPOS_CAPTURADOS);
        result = queryTiposPecasCapturados(jogo);

        arrayString = new ArrayList<>();
        peca1 = "Homer Simpson";
        arrayString.add(peca1);
        peca2 = "Joker/Rainha";
        arrayString.add(peca2);
        peca3 = "Padre da Vila";
        arrayString.add(peca3);
        peca4 = "TorreHor";
        arrayString.add(peca4);
        peca5 = "TorreVert";
        arrayString.add(peca5);

        assertArrayEquals(result.toArray(), arrayString.toArray());

    }





    @Test
    public void getHints()throws IOException, InvalidGameInputException {
        File file = new File("test-files", "8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean move;
        boolean gameOver;



        move = jogo.move(1,0,1,5);//rainha preta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,7,0,6); //rei branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(3,0,0,3); //PV preto anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,7,6,2); //rainha branca anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,3,1,4); //PV preto anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(6,2,6,3); //rainha branca anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,4,0,5); //PV preto anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        String expected;
        expected = jogo.getHints(0,6).toString(); // sugestao rainha

        String obtained;
        obtained = "[(0,5) -> 3, (0,7) -> 0, (1,5) -> 8, (1,6) -> 0, (1,7) -> 0]";
        assertEquals(expected,obtained);

    }

    @Test
    public void testSave_Load()throws IOException, InvalidGameInputException {
        File file = new File("test-files", "8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean move;
        boolean gameOver;



        move = jogo.move(5,0,5,7);//TorreVer preta como TorreVer branca
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(4,7,4,6); //torreHor Branca move se invalido
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(2,7,4,5); //Ponei Magico Branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,0,0,3); //rainha Preta anda invalido
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,0,1,5); //Rainha Preta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(3,7,1,5); //Padre Branco Come rainha preta
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(3,0,5,2); //Padre preto anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(4,5,6,3); //Ponei Branco anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(5,2,6,3); //Padre preto como ponei branco
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        File file1 = new File("test-files", "8x8Save.txt");
        jogo.saveGame(file1);

        jogo.loadGame(file1);
        jogo.getAuthorsPanel();
        jogo.getBoardSize();
        jogo.getCurrentTeamID();
        jogo.customizeBoard();

    }

    @Test
    public void sapoEncantado()throws IOException, InvalidGameInputException {
        File file = new File("test-files", "8x8TesteSapo.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        String resultComPeca = "";
        boolean move;
        boolean gameOver;

        move = jogo.move(1,0,1,5);//RainhaPreta anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,6,2,4); //sapo n anda pois n salta por peca equipa diferente
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,6,0,3); //sapo n anda pois n salta tantas casas
        assertFalse(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,6,0,4); //sapo anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,0,1,0);//ReiPreto anda
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,4,1,5); //sapo anda e captura rainhaPreta
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        resultComPeca = jogo.getPieceInfoAsString(18);
        assertEquals("18 | Sapo Encantado | 5 | 20 | Susana Simões @ (1, 5)", resultComPeca);


    }


    @Test
    public void testeMenuFinal()throws IOException, InvalidGameInputException {
        File file = new File("test-files", "8x8TesteSapo.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        String resultComPeca = "";
        boolean move;
        boolean gameOver;

        move = jogo.move(1,0,1,5);
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(0,7,1,6);
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertFalse(gameOver);

        move = jogo.move(1,5,1,6);
        assertTrue(move);
        gameOver= jogo.gameOver();
        assertTrue(gameOver);

        ArrayList<String> menuFinal = new ArrayList<>();

        String linha1 = "JOGO DE CRAZY CHESS";
        String linha2 = "Resultado: VENCERAM AS PRETAS" ;
        String linha3 = "---";
        String linha4="Equipa das Pretas";
        String linha5= "1";
        String linha6= "2";
        String linha7= "0";
        String linha8="Equipa das Brancas";
        String linha9= "0";
        String linha10 = "1";
        String linha11 = "0";

        menuFinal.add(linha1);
        menuFinal.add(linha2);
        menuFinal.add(linha3);
        menuFinal.add(linha4);
        menuFinal.add(linha5);
        menuFinal.add(linha6);
        menuFinal.add(linha7);
        menuFinal.add(linha8);
        menuFinal.add(linha9);
        menuFinal.add(linha10);
        menuFinal.add(linha11);

        assertArrayEquals(menuFinal.toArray(),jogo.getGameResults().toArray());




    }







}









