package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.Repository;
import br.edu.iff.repository.RepositoryException;

import java.util.List;

public interface PalavraRepository extends Repository {
    Palavra getPorId(long id);

    List<Palavra> getPorTema(Tema tema);

    List<Palavra> getTodas();

    Palavra getPalavra(String palavra);

    void inserir(Palavra palavra) throws RepositoryException;

    void atualizar(Palavra palavra) throws RepositoryException;

    void remover(Palavra palavra) throws RepositoryException;
}
