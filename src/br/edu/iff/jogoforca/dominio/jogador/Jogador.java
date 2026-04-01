package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Jogador extends ObjetoDominioImpl {
    private String nome;
    private int pontuacao = 0;

    private Jogador(long id, String nome) {
        super(id);
        setNome(nome);
    }

    private Jogador(long id, String nome, int pontuacao) {
        super(id);
        setNome(nome);
        atualizarPontuacao(pontuacao);
    }

    public Jogador criar(long id, String nome) {
        return new Jogador(id, nome);
    }

    public Jogador reconstituir(long id, String nome, int pontuacao) {
        return new Jogador(id, nome, pontuacao);
    }

    public String getNome() {
        return this.nome;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void atualizarPontuacao(int pontos) {
        this.pontuacao = pontos;
    }
}
