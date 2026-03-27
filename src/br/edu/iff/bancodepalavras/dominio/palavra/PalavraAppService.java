package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

import java.util.Objects;

public class PalavraAppService {

    private final TemaRepository temaRepository;
    private final PalavraRepository palavraRepository;
    private final PalavraFactory palavraFactory;
    private static PalavraAppService soleInstance = null;

    public static void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        if (PalavraAppService.soleInstance == null)
            PalavraAppService.soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
    }

    public static PalavraAppService getSoleInstance() {
        return PalavraAppService.soleInstance;
    }

    private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        Objects.requireNonNull(temaRepository);
        Objects.requireNonNull(palavraRepository);
        Objects.requireNonNull(palavraFactory);

        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
        this.palavraFactory = palavraFactory;
    }

    public boolean novaPalavra(String palavra, long idTema) throws RepositoryException {
        Palavra p = palavraRepository.getPalavra(palavra);

        if (p != null) return true;

        Tema tema = temaRepository.getPorId(idTema);

        palavraFactory.createPalavra(palavra, tema);

        return true;
    }
}