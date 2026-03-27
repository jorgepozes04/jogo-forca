package br.edu.iff.bancodepalavras.dominio.letra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;

public interface LetraFactory {
    Letra getLetra(char codigo);

    Letra getLetraEncoberta();
}
