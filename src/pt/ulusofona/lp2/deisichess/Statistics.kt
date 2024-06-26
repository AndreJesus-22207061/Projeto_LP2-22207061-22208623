package pt.ulusofona.lp2.deisichess

import java.util.ArrayList

fun  getStatsCalculator(tipo: StatType): Function1<GameManager, ArrayList<String>> {
    when (tipo) {
        StatType.TOP_5_PONTOS -> return ::queryTOP5pontos
        StatType.TOP_5_CAPTURAS -> return ::queryTOP5capturas
        StatType.PECAS_MAIS_5_CAPTURAS -> return ::queryPecasComMais5capturas
        StatType.PECAS_MAIS_BARALHADAS -> return ::queryTOP3Barralhadas
        StatType.TIPOS_CAPTURADOS -> return ::queryTiposPecasCapturados

    }
}


fun queryTOP5capturas(jogo: GameManager) : ArrayList<String> {

    val tabuleiro = jogo.getTabuleiro();
    val arrayStrings = tabuleiro.top5Capturas()

    return  arrayStrings
}

fun queryPecasComMais5capturas(jogo: GameManager) :ArrayList<String>{

    val tabuleiro :Tabuleiro = jogo.getTabuleiro()
    val arrayStrings = tabuleiro.pecasComMais5capturas()

    return arrayStrings
}

fun queryTOP5pontos(jogo: GameManager) : ArrayList<String> {

    val tabuleiro = jogo.getTabuleiro();
    val arrayStrings = tabuleiro.top5Pontos()

    return  arrayStrings
}

fun queryTiposPecasCapturados(jogo: GameManager) : ArrayList<String> {

    val tabuleiro = jogo.getTabuleiro();
    val arrayStrings = tabuleiro.tiposPecasCapturados()

    return  arrayStrings
}

fun queryTOP3Barralhadas(jogo: GameManager) : ArrayList<String> {

    val tabuleiro = jogo.getTabuleiro();
    val arrayStrings = tabuleiro.top3Baralhadas()

    return  arrayStrings
}