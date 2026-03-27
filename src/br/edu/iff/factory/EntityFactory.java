package br.edu.iff.factory;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.repository.Repository;

public abstract class EntityFactory {

    Repository repository;

    protected EntityFactory(Repository repository) {
        this.repository = repository;
    }

    protected Repository getRepository() {
        return this.repository;
    }

    protected long getProximoId(){
        return repository.getProximoId();
    }
}
