package br.edu.iff.jogoforca.embdr;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;

public class BDRRepositoryFactory implements RepositoryFactory {

    @Override
    public PalavraRepository getPalavraRepository() {
        throw new UnsupportedOperationException("Unimplemented method 'getPalavraRepository'");
    }

    @Override
    public TemaRepository getTemaRepository() {
        throw new UnsupportedOperationException("Unimplemented method 'getTemaRepository'");
    }

    @Override
    public RodadaRepository getRodadaRepository() {
        throw new UnsupportedOperationException("Unimplemented method 'getRodadaRepository'");
    }

    @Override
    public JogadorRepository getJogadorRepository() {
        throw new UnsupportedOperationException("Unimplemented method 'getJogadorRepository'");
    }
}
