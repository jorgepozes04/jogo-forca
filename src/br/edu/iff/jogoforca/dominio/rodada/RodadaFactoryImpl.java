package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.factory.EntityFactory;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory {

    private TemaRepository temaRepository;
    private PalavraRepository palavraRepository;

    protected RodadaFactoryImpl(RodadaRepository rodadaRepository, TemaRepository temaRepository,
            PalavraRepository palavraRepository) {
        super(rodadaRepository);

        setTemaRepository(temaRepository);
        setPalavraRepository(palavraRepository);
    }

    @Override
    public abstract Rodada getRodada(Jogador jogador);

    protected RodadaRepository getRodadaRepository() {
        return (RodadaRepository) getRepository();
    }

    protected TemaRepository getTemaRepository() {
        return this.temaRepository;
    }

    protected PalavraRepository getPalavraRepository() {
        return this.palavraRepository;
    }

    private void setTemaRepository(TemaRepository temaRepository) {
        if (temaRepository == null)
            throw new IllegalArgumentException("O repositorio de tema nao pode ser nulo");

        this.temaRepository = temaRepository;
    }

    private void setPalavraRepository(PalavraRepository palavraRepository) {
        if (palavraRepository == null)
            throw new IllegalArgumentException("O repositorio de palavra nao pode ser nulo");

        this.palavraRepository = palavraRepository;
    }

}