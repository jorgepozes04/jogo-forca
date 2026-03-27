package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;

public interface PalavraFactory {
    Palavra createPalavra(String palavra, Tema tema);
}
