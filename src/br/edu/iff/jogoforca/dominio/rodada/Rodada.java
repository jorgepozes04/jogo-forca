package br.edu.iff.jogoforca.dominio.rodada;

import java.util.Arrays;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public class Rodada extends ObjetoDominioImpl {

    private Item[] itens;
    private Letra[] letrasErradas;
    private Jogador jogador;
    private static BonecoFactory bonecoFactory = null;

    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosPorLetraEncoberta = 15;
    private static int pontosPorTodasPalavrasDescobertas = 100;

    public static BonecoFactory getBonecoFactory() {
        return bonecoFactory;
    }

    public static void setBonecoFactory(BonecoFactory bonecoFactory) {
        Rodada.bonecoFactory = bonecoFactory;
    }

    public static int getPontosPorLetraEncoberta() {
        return pontosPorLetraEncoberta;
    }

    public static void setPontosPorLetraEncoberta(int pontosPorLetraEncoberta) {
        Rodada.pontosPorLetraEncoberta = pontosPorLetraEncoberta;
    }

    public static int getPontosQuandoDescobreTodasAsPalavras() {
        return pontosPorTodasPalavrasDescobertas;
    }

    public static void setPontosQuandoDescobreTodasAsPalavras(int pontosQuandoDescobreTodasAsPalavras) {
        Rodada.pontosPorTodasPalavrasDescobertas = pontosQuandoDescobreTodasAsPalavras;
    }

    public static int getMaxErros() {
        return maxErros;
    }

    public static void setMaxErros(int maxErros) {
        Rodada.maxErros = maxErros;
    }

    public static int getMaxPalavras() {
        return maxPalavras;
    }

    public static void setMaxPalavras(int maxPalavras) {
        Rodada.maxPalavras = maxPalavras;
    }

    public static Rodada criar(long id, Palavra[] palavras, Jogador jogador) {
        return new Rodada(id, palavras, jogador);
    }

    public static Rodada reconstituir(long id, Item[] itens, Letra[] letrasErradas, Jogador jogador) {
        return new Rodada(id, itens, letrasErradas, jogador);
    }

    private Rodada(long id, Palavra[] palavras, Jogador jogador) {
        super(id);

        if (bonecoFactory == null)
            throw new IllegalStateException("O factory do jogador não foi definido");

        validarPalavras(palavras);
        criarItens(palavras);
        iniciarLetrasErradas();
        setJogador(jogador);
    }

    private Rodada(long id, Item[] itens, Letra[] letrasErradas, Jogador jogador) {
        super(id);

        if (bonecoFactory == null)
            throw new IllegalStateException("O factory do jogador não foi definido");

        setItens(itens);
        setLetrasErradas(letrasErradas);
        setJogador(jogador);
    }

    public Jogador getJogador() {
        return this.jogador;
    }

    public Tema getTema() {
        return itens[0].getPalavra().getTema();
    }

    public Palavra[] getPalavras() {
        Palavra[] palavras = new Palavra[itens.length];

        for (int i = 0; i < palavras.length; i++) {
            palavras[i] = itens[i].getPalavra();
        }

        return palavras;
    }

    public int getNumPalavras() {
        return itens.length;
    }

    public void tentar(char codigo) {
        if (encerrou())
            throw new IllegalStateException("O Jogo ja encerrou, voce não pode mais tentar");

        boolean acertou = false;

        for (Item i : itens) {
            if (i.tentar(codigo))
                acertou = true;
        }

        if (!acertou) {
            Letra[] novo = Arrays.copyOf(letrasErradas, letrasErradas.length + 1);
            novo[novo.length - 1] = Palavra.getLetraFactory().getLetra(codigo);
            this.letrasErradas = novo;
        }

        if (encerrou()) {
            jogador.atualizarPontuacao(calcularPontos());
        }
    }

    public void arriscar(String[] palavras) {
        if (encerrou())
            throw new IllegalStateException("Jogo encerrado, não é mais possível arriscar!");

        for (int i = 0; i < itens.length; i++) {
            itens[i].arriscar(palavras[i]);
        }

        if (encerrou()) {
            jogador.atualizarPontuacao(calcularPontos());
        }
    }

    public void exibirItens(Object contexto) {
        for (Item i : itens) {
            i.exibir(contexto);
        }
    }

    public void exibirBoneco(Object contexto) {
        bonecoFactory.getBoneco().exibir(contexto, getQtdeErros());
    }

    public void exibirPalavras(Object contexto) {
        for (Item i : itens) {
            i.getPalavra().exibir(contexto);
        }
    }

    public void exibirLetrasErradas(Object contexto) {
        for (Letra l : letrasErradas) {
            l.exibir(contexto);
        }
    }

    public Letra[] getTentativas() {
        Letra[] tentativas = new Letra[getQtdeTentativas()];

        for (int i = 0; i < getCertas().length; i++) {
            tentativas[i] = getCertas()[i];
        }

        for (int i = 0; i < getErradas().length; i++) {
            tentativas[getCertas().length + i] = getErradas()[i];
        }

        return tentativas;
    }

    public Letra[] getCertas() {
        Letra[] letras = new Letra[26];
        int idx = 0;

        for (int i = 0; i < itens.length; i++) {
            for (int j = 0; j < itens[i].getLetrasDescobertas().length; j++) {

                Letra letraAtual = itens[i].getLetrasDescobertas()[j];
                boolean jaExiste = false;

                for (int k = 0; k < idx; k++) {
                    if (letras[k].equals(letraAtual)) {
                        jaExiste = true;
                        break;
                    }

                }
                if (!jaExiste) {
                    letras[idx] = letraAtual;
                    idx++;
                }
            }
        }

        return Arrays.copyOf(letras, idx);
    }

    public Letra[] getErradas() {
        return Arrays.copyOf(letrasErradas, letrasErradas.length);
    }

    public int calcularPontos() {
        if (!descobriu())
            return 0;

        int pontos = pontosPorTodasPalavrasDescobertas;

        for (Item i : itens) {
            pontos += i.calcularPontosLetrasEncobertas(pontosPorLetraEncoberta);
        }

        return pontos;
    }

    public boolean encerrou() {
        return arriscou() || descobriu() || getQtdeTentativasRestantes() == 0;
    }

    public boolean descobriu() {
        for (Item i : itens) {
            if (!i.descobriu())
                return false;
        }

        return true;
    }

    public boolean arriscou() {
        for (Item i : itens) {
            if (i.arriscou())
                return true;
        }

        return false;
    }

    public int getQtdeTentativasRestantes() {
        return maxErros - getQtdeErros();
    }

    public int getQtdeErros() {
        return letrasErradas.length;
    }

    public int getQtdeAcertos() {
        return getCertas().length;
    }

    public int getQtdeTentativas() {
        return getQtdeAcertos() + getQtdeErros();
    }

    private void setJogador(Jogador jogador) {
        if (jogador == null)
            throw new IllegalArgumentException("O jogador não pode ser nulo");

        this.jogador = jogador;
    }

    private void validarPalavras(Palavra[] palavras) {
        if (palavras == null)
            throw new IllegalArgumentException("A array não pode ser nula");

        if (palavras.length == 0 || palavras.length > maxPalavras)
            throw new IllegalArgumentException("Tamanho inválido, precisa ser maior que 0 e menor que " + maxPalavras);

        for (int i = 1; i < palavras.length; i++) {
            if (!(palavras[i].getTema().getId() == palavras[0].getTema().getId())) {
                throw new IllegalStateException("Os temas tem que ser iguais");
            }
        }
    }

    private void criarItens(Palavra[] palavras) {
        this.itens = new Item[palavras.length];

        for (int i = 0; i < this.itens.length; i++) {
            this.itens[i] = Item.criar(i, palavras[i]);
        }
    }

    private void iniciarLetrasErradas() {
        this.letrasErradas = new Letra[0];
    }

    private void setItens(Item[] itens) {
        if (itens == null || itens.length == 0)
            throw new IllegalArgumentException("A array não pode ser nula nem vazia");

        this.itens = itens;
    }

    public Item[] getItens() {
        return Arrays.copyOf(itens, itens.length);
    }

    private void setLetrasErradas(Letra[] letrasErradas) {
        if (letrasErradas == null)
            throw new IllegalArgumentException("O vetor de letras não pode ser nulo");

        this.letrasErradas = letrasErradas;
    }
}