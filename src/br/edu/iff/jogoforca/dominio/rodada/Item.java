package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.dominio.ObjetoDominioImpl;

public class Item extends ObjetoDominioImpl {

    private Palavra palavra;
    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;

    public static Item criar(long id, Palavra palavra) {
        return new Item(id, palavra);
    }

    public static Item reconstituir(long id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    private Item(long id, Palavra palavra) {
        super(id);

        setPalavra(palavra);
        setPosicoesDescobertas(palavra.getTamanho());
    }

    private Item(long id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        super(id);

        setPalavra(palavra);
        setPosicoesDescobertas(palavra.getTamanho());

        for (int i = 0; i < posicoesDescobertas.length; i++) {
            if (posicoesDescobertas[i] == 1) {
                this.posicoesDescobertas[i] = true;
            }
        }

        this.palavraArriscada = palavraArriscada;

    }

    public Palavra getPalavra() {
        return this.palavra;
    }

    public Letra[] getLetrasDescobertas() {
        Letra[] letrasDescobertas = new Letra[qtdeLetrasDescobertas()];
        int idxPosicoes = 0;

        for (int i = 0; i < this.posicoesDescobertas.length; i++) {
            if (this.posicoesDescobertas[i]) {
                letrasDescobertas[idxPosicoes] = this.palavra.getLetra(i);
                idxPosicoes++;
            }
        }

        return letrasDescobertas;
    }

    public Letra[] getLetrasEncobertas() {
        Letra[] letrasEncobertas = new Letra[qtdeLetrasEncobertas()];
        int idxPosicoes = 0;

        for (int i = 0; i < this.posicoesDescobertas.length; i++) {
            if (!this.posicoesDescobertas[i]) {
                letrasEncobertas[idxPosicoes] = this.palavra.getLetra(i);
                idxPosicoes++;
            }
        }

        return letrasEncobertas;
    }

    public int qtdeLetrasEncobertas() {
        int qtdeEncobertas = 0;

        for (boolean pos : this.posicoesDescobertas) {
            if (!pos)
                qtdeEncobertas++;
        }

        return qtdeEncobertas;
    }

    public int qtdeLetrasDescobertas() {
        int qtdeDescobertas = 0;

        for (boolean pos : this.posicoesDescobertas) {
            if (pos)
                qtdeDescobertas++;
        }

        return qtdeDescobertas;
    }

    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
        if (valorPorLetraEncoberta < 0)
            throw new IllegalArgumentException("O valor por letra encoberta não pode ser negativo!");

        return qtdeLetrasEncobertas() * valorPorLetraEncoberta;
    }

    public boolean descobriu() {
        return (acertou() || qtdeLetrasEncobertas() == 0);
    }

    public void exibir(Object contexto) {
    }

    protected boolean tentar(char codigo) {
        int[] posicoes = this.palavra.tentar(codigo);

        for (int i = 0; i < posicoes.length; i++) {
            this.posicoesDescobertas[posicoes[i]] = true;
        }

        return posicoes.length > 0;
    }

    protected void arriscar(String palavra) {
        if (palavraArriscada != null)
            throw new IllegalStateException("Você pode arriscar apenas uma vez.");

        setPalavraArriscada(palavra);
    }

    public String getPalavraArriscada() {
        return palavraArriscada;
    }

    public boolean arriscou() {
        return this.palavraArriscada != null;
    }

    public boolean acertou() {
        if (!arriscou())
            return false;

        return this.palavra.comparar(this.palavraArriscada);
    }

    private void setPalavra(Palavra palavra) {
        if (palavra == null)
            throw new IllegalArgumentException("A palavra não pode ser nula");

        this.palavra = palavra;
    }

    private void setPosicoesDescobertas(int tamanho) {
        if (tamanho < 0)
            throw new IllegalArgumentException("O tamanho não pode ser negativo");

        this.posicoesDescobertas = new boolean[tamanho];
    }

    private void setPalavraArriscada(String palavraArriscada) {
        if (palavraArriscada == null || palavraArriscada.isEmpty())
            throw new IllegalArgumentException("A palavra não pode ser nula nem vazia");

        this.palavraArriscada = palavraArriscada;
    }
}