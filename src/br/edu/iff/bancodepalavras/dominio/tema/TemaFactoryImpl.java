package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {

    private static TemaFactoryImpl soleInstance = null;

    public static void createSoleInstance(TemaRepository repository) {
        if (TemaFactoryImpl.soleInstance == null)
            TemaFactoryImpl.soleInstance = new TemaFactoryImpl(repository);
    }

    public static TemaFactoryImpl getSoleInstance() {
        return TemaFactoryImpl.soleInstance;
    }

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
    }

    @Override
    public Tema getTema(String nome) {
        Tema[] temas = getTemaRepository().getPorNome(nome);

        if (temas.length == 0) {
            Tema novo = Tema.criar(getProximoId(), nome);
            try {
                getTemaRepository().inserir(novo);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            return novo;
        }

        return temas[0];
    }

    private TemaRepository getTemaRepository() {
        return (TemaRepository) getRepository();
    }

}
