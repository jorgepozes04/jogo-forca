package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.repository.RepositoryException;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {

    private static JogadorFactoryImpl soleInstance = null;

    public static void createSoleInstance(JogadorRepository repository) {
        if (JogadorFactoryImpl.soleInstance == null)
            JogadorFactoryImpl.soleInstance = new JogadorFactoryImpl(repository);
    }

    public static JogadorFactoryImpl getSoleInstance() {
        return JogadorFactoryImpl.soleInstance;
    }

    private JogadorFactoryImpl(JogadorRepository repository) {
        super(repository);
    }

    @Override
    public Jogador getJogador(String nome) {
        Jogador jogador = getJogadorRepository().getPorNome(nome);

        if (jogador == null) {
            Jogador novo = Jogador.criar(getProximoId(), nome);
            try {
                getJogadorRepository().inserir(novo);
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
            return novo;
        }

        return jogador;
    }

    private JogadorRepository getJogadorRepository() {
        return (JogadorRepository) getRepository();
    }

}
