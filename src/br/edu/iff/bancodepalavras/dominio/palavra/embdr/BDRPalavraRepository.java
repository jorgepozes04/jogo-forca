package br.edu.iff.bancodepalavras.dominio.palavra.embdr;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

import java.util.List;

public class BDRPalavraRepository implements PalavraRepository {
    @Override
    public Palavra getPorId(long id) {
        return null;
    }

    @Override
    public List<Palavra> getPorTema(Tema tema) {
        return null;
    }

    @Override
    public List<Palavra> getTodas() {
        return null;
    }

    @Override
    public Palavra getPalavra(String palavra) {
        return null;
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {

    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {

    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {

    }

    @Override
    public long getProximoId() {
        return -1;
    }
}
