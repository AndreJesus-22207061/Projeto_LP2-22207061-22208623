package pt.ulusofona.lp2.deisichess;

public class CountJogadas {

    private int equipa;

    private int pecasCapturadas =0;

    private int jogadasValidas=0;

    private int jogadasInvalidas=0;


    public CountJogadas(int equipa) {
        this.equipa = equipa;

    }

    void pecaFoiCapturada() {
        this.pecasCapturadas++;
    }

    void decrementaPecaFoiCapturada() {
        this.pecasCapturadas--;
    }


    void jogadaValida() {
        this.jogadasValidas++;
    }

    void decrementaJogadaValida() {
        this.jogadasValidas--;
    }

    void jogadaInvalida(){
        this.jogadasInvalidas++;
    }


    int getEquipa(){
        return this.equipa;
    }

    int getPecasCapturadas(){
        return this.pecasCapturadas;
    }

    int getJogadasValidas(){
        return this.jogadasValidas;
    }

    int getJogadasInvalidas(){
        return this.jogadasInvalidas;
    }

    void alteraJogadasInvalidas(int jogInvalidas) {
        this.jogadasInvalidas = jogInvalidas;
    }

    void alteraPecasCapturadas(int pecasCapturadas){
        this.pecasCapturadas = pecasCapturadas;
    }

    void alteraJogadasValidas(int jogadasValidas){
        this.jogadasValidas = jogadasValidas;
    }


}