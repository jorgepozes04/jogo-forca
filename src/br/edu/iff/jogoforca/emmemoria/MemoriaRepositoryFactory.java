package br.edu.iff.jogoforca.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.emmemoria.MemoriaPalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.tema.emmemoria.MemoriaTemaRepository;
import br.edu.iff.jogoforca.RepositoryFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.jogoforca.dominio.jogador.emmemoria.MemoriaJogadorRepository;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.rodada.emmemoria.MemoriaRodadaRepository;

public class MemoriaRepositoryFactory implements RepositoryFactory {

    private static MemoriaRepositoryFactory soleInstance = null;

    private PalavraRepository palavraRepository;
    private TemaRepository temaRepository;
    private RodadaRepository rodadaRepository;
    private JogadorRepository jogadorRepository;

    public static MemoriaRepositoryFactory getSoleInstance() {
        if (MemoriaRepositoryFactory.soleInstance == null) {
            MemoriaRepositoryFactory.soleInstance = new MemoriaRepositoryFactory();
        }

        return MemoriaRepositoryFactory.soleInstance;
    }

    private MemoriaRepositoryFactory() {
        this.palavraRepository = MemoriaPalavraRepository.getSoleInstance();
        this.temaRepository = MemoriaTemaRepository.getSoleInstance();
        this.rodadaRepository = MemoriaRodadaRepository.getSoleInstance();
        this.jogadorRepository = MemoriaJogadorRepository.getSoleInstance();
    }

    public PalavraRepository getPalavraRepository() {
        return palavraRepository;
    }

    public TemaRepository getTemaRepository() {
        return temaRepository;
    }

    public RodadaRepository getRodadaRepository() {
        return rodadaRepository;
    }

    public JogadorRepository getJogadorRepository() {
        return jogadorRepository;
    }

}
