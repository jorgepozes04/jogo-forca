package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.dominio.ObjetoDominioImpl;

import java.util.ArrayList;
import java.util.List;

public class Palavra extends ObjetoDominioImpl {

    private String palavra;
    private Tema tema;
    private List<Letra> letras = new ArrayList<>();
    private static LetraFactory letraFactory;

    public static void setLetraFactory(LetraFactory letraFactory) {
        Palavra.letraFactory = letraFactory;
    }

    public static LetraFactory getLetraFactory() {
        return Palavra.letraFactory;
    }

    public static Palavra criar(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }

    private Palavra(long id, String palavra, Tema tema) {
        super(id);
        setPalavra(palavra);
        setTema(tema);
    }

    public List<Letra> getLetras() {
        // Retorna cópia
        List<Letra> lista = this.letras;
        return lista;
    }

    public Letra getLetra(int i) {
        return letras.get(i);
    }

    public int[] tentar(char codigo) {
        int contador = 0;
        for (Letra l : letras) {
            if (l.getCodigo() == codigo) {
                contador++;
            }
        }

        int[] posicoes = new int[contador];
        int idxPosicoes = 0;
        for (int i = 0; i < letras.size(); i++) {
            if (letras.get(i).getCodigo() == codigo) {
                posicoes[idxPosicoes] = i;
                idxPosicoes++;
            }
        }

        return posicoes;
    }

    public Tema getTema() {
        return this.tema;
    }

    private void setTema(Tema tema) {
        if (tema == null) {
            throw new IllegalArgumentException("Inválido, o tema não pode ser nulo!");
        }
        this.tema = tema;
    }

    public boolean comparar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return false;
        }
        return this.palavra.equalsIgnoreCase(palavra);
    }

    public int getTamanho() {
        return palavra.length();
    }

    private void setPalavra(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            throw new IllegalArgumentException("A palavra não pode estar vazia!");
        }
        this.palavra = palavra;

        // Limpa a lista por segurança
        this.letras.clear();

        // Pega a fábrica de letras (que foi injetada pela classe Aplicacao)
        LetraFactory factory = getLetraFactory();

        // Transforma cada caractere da String em um Objeto Letra e adiciona na lista
        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            this.letras.add(factory.getLetra(caractere));
        }
    }

    @Override
    public String toString() {
        return palavra;
    }

    public void exibir(Object contexto, boolean[] posicoes) {
        System.out.println(palavra);
    }

    public void exibir(Object contexto) {
        System.out.println(palavra);
    }
}