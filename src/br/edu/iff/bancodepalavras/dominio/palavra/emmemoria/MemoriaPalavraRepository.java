package br.edu.iff.bancodepalavras.dominio.palavra.emmemoria;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPalavraRepository implements PalavraRepository {

    private static MemoriaPalavraRepository soleInstance;

    private List<Palavra> pool = new ArrayList<>();

    public static MemoriaPalavraRepository getSoleInstance() {
        if (MemoriaPalavraRepository.soleInstance == null) {
            MemoriaPalavraRepository.soleInstance = new MemoriaPalavraRepository();
        }

        return MemoriaPalavraRepository.soleInstance;
    }

    private MemoriaPalavraRepository() {
    }

    @Override
    public Palavra getPorId(long id) {
        for (Palavra p : this.pool) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new IllegalArgumentException("Nenhum Palavra encontrada");
    }

    @Override
    public Palavra[] getPorTema(Tema tema) {
        List<Palavra> palavras = new ArrayList<>();
        for (Palavra p : this.pool) {
            if (p.getTema() == tema) {
                palavras.add(p);
            }
        }
        return palavras.toArray(new Palavra[0]);
    }

    @Override
    public Palavra[] getTodas() {
        return this.pool.toArray(new Palavra[0]);
    }

    @Override
    public Palavra getPalavra(String palavra) {
        for (Palavra p : this.pool) {
            if (p.toString().equals(palavra)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void inserir(Palavra palavra) throws RepositoryException {
        try {
            pool.add(palavra);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
        ;
    }

    @Override
    public void atualizar(Palavra palavra) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (this.pool.get(i).getId() == palavra.getId()) {
                this.pool.set(i, palavra);
            }
        }
        throw new IllegalArgumentException("Nenhum palavra encontrada");
    }

    @Override
    public void remover(Palavra palavra) throws RepositoryException {
        pool.remove(palavra);
    }

    @Override
    public long getProximoId() {
        return this.pool.size();
    }
}
