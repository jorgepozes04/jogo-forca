package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl {
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosTodasPalavrasDescobertas = 100;
    private static int pontosPorLetraEncoberta = 15;

    private Jogador jogador;
    private BonecoFactory bonecoFactory;

    private Item[] itens = new Item[maxPalavras];
    private Letra[] erradas = new Letra[maxErros];
    private int qtdeErros = 0;

    private Rodada(long id, Palavra[] palavras, Jogador jogador) {
        super(id);
        setJogador(jogador);
        inicializarItens(palavras);
    }

    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }

    public static Rodada reconstituir(long id, Palavra[] palavras, Jogador jogador,
            int[] posicoesDescobertas, String[] palavrasArriscadas,
            Letra[] erradas, int qtdeErros) {
        Rodada rodada = new Rodada(id, palavras, jogador);
        rodada.setErradas(erradas);
        rodada.qtdeErros = qtdeErros;
        return rodada;
    }

    private void inicializarItens(Palavra[] palavras) {
        if (palavras == null || palavras.length == 0) {
            throw new IllegalArgumentException("Deve ter ao menos uma palavra");
        }
        if (palavras.length > maxPalavras) {
            throw new IllegalArgumentException("Máximo " + maxPalavras + " palavras permitidas");
        }

        long id = 0;
        for (int i = 0; i < palavras.length; i++) {
            itens[i] = Item.criar(id++, palavras[i]);
        }
    }

    public static int getMaxPalavras() {
        return maxPalavras;
    }

    public static int getMaxErros() {
        return maxErros;
    }

    public static int getPontosTodasPalavrasDescobertas() {
        return pontosTodasPalavrasDescobertas;
    }

    public static int getPontosPorLetraEncoberta() {
        return pontosPorLetraEncoberta;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        if (jogador == null) {
            throw new IllegalArgumentException("Jogador não pode ser nulo");
        }
        this.jogador = jogador;
    }

    public BonecoFactory getBonecoFactory() {
        return bonecoFactory;
    }

    public void setBonecoFactory(BonecoFactory bonecoFactory) {
        this.bonecoFactory = bonecoFactory;
    }

    public Item[] getItens() {
        return itens;
    }

    public void setItens(Item[] itens) {
        this.itens = itens;
    }

    public Letra[] getErradas() {
        return erradas;
    }

    public void setErradas(Letra[] erradas) {
        this.erradas = erradas;
    }

    public int getQtdeErros() {
        return qtdeErros;
    }

    public boolean tentarLetra(char codigo, Letra letra) {
        if (perdeu()) {
            throw new IllegalStateException("Rodada perdida");
        }

        boolean acertou = false;
        for (int i = 0; i < itens.length; i++) {
            if (itens[i] != null && itens[i].tentar(codigo)) {
                acertou = true;
            }
        }

        if (!acertou) {
            registrarErro(letra);
        }

        return acertou;
    }

    public void arriscarPalavra(String palavra, int idxItem) {
        if (idxItem < 0 || idxItem >= itens.length || itens[idxItem] == null) {
            throw new IllegalArgumentException("Índice de item inválido");
        }
        itens[idxItem].arriscar(palavra);
    }

    private void registrarErro(Letra letra) {
        if (qtdeErros >= maxErros) {
            throw new IllegalStateException("Limite de erros atingido");
        }
        erradas[qtdeErros] = letra;
        qtdeErros++;
    }

    public boolean ganhou() {
        if (itens[0] == null) {
            return false;
        }

        for (int i = 0; i < itens.length; i++) {
            if (itens[i] != null && !itens[i].descobriu()) {
                return false;
            }
        }
        return true;
    }

    public boolean perdeu() {
        return qtdeErros >= maxErros;
    }

    public int calcularPontos() {
        int pontos = 0;

        if (ganhou()) {
            pontos += pontosTodasPalavrasDescobertas;
        }

        for (int i = 0; i < itens.length; i++) {
            if (itens[i] != null) {
                pontos += itens[i].calcularPontosLetrasEncobertas(pontosPorLetraEncoberta);
            }
        }

        return pontos;
    }

    public void exibir(Object contexto) {
        if (bonecoFactory != null) {
            bonecoFactory.getBoneco().exibir(contexto, qtdeErros);
        }

        for (int i = 0; i < itens.length; i++) {
            if (itens[i] != null) {
                itens[i].exibir(contexto);
            }
        }
    }
}
