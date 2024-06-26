package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{  // nao tem limite de movimento na vertical
    public TorreVertical(int id, int tipo ,int equipa, String alcunha) {
        super(id,tipo ,equipa, alcunha);
        definirPontos();
        defenirImagem();
    }

    @Override
    String toString(ContadorRondas contadorRondas) {
        if(!getEstado()) {
            return getID() + " | " +getTipoString(contadorRondas)+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | " +getTipoString(contadorRondas)+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";
    }

    @Override
    String getTipoString(ContadorRondas contadorRondas) {
        return "TorreVert";
    }

    @Override
    void definirPontos() {
        this.valor = 3;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "TorrePretaV.png";
        }else{
            this.imagem = "TorreBrancaV.png";
        }
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho()) {
            return false;
        }

        // Verificar se a posição de destino está na mesma coluna vertical
        if (xFinal != getX()) {
            return false;
        }

        int deltaY = Math.abs(yFinal - getY());

        // Verificar se há peças no caminho
        int stepY = (yFinal - getY()) / Math.max(1, deltaY);

        for (int i = 1; i < deltaY; i++) {
            int currentY = getY() + i * stepY;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(xFinal, currentY) != null) {
                return false;
            }
        }

        // Verificar se a posição de destino está vazia ou contém uma peça adversária
        Peca pecaDestino = tabuleiro.getPeca(xFinal, yFinal);
        return pecaDestino == null || pecaDestino.getEquipa() != getEquipa();
    }
}
