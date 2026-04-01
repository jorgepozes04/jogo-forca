package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {
    private static PalavraFactoryImpl soleInstance;

    public static void createSoleInstance(PalavraRepository repository) {
        if (PalavraFactoryImpl.soleInstance == null) {
            PalavraFactoryImpl.soleInstance = new PalavraFactoryImpl(repository);
        }
    }

    public static PalavraFactoryImpl getSoleInstance() {
        return PalavraFactoryImpl.soleInstance;
    }

    private PalavraFactoryImpl(PalavraRepository repository) {
        super(repository);
    }

    private PalavraRepository getPalavraRepository() {
        return (PalavraRepository) getRepository();
    }

    @Override
    public Palavra createPalavra(String palavra, Tema tema) {
        Palavra p = getPalavraRepository().getPalavra(palavra);

        if (p != null) {
            throw new IllegalArgumentException("A palavra " + palavra + " já existe!");
        }

        Palavra novaPalavra = Palavra.criar(getProximoId(), palavra, tema);
        try {
            getPalavraRepository().inserir(novaPalavra);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        return novaPalavra;
    }
}
