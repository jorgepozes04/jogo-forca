package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import java.util.ArrayList;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class MemoriaTemaRepository implements TemaRepository {

    private static MemoriaTemaRepository soleInstance = null;

    private ArrayList<Tema> pool = new ArrayList<>();

    public static MemoriaTemaRepository getSoleInstance() {
        if (MemoriaTemaRepository.soleInstance == null) {
            MemoriaTemaRepository.soleInstance = new MemoriaTemaRepository();
        }

        return MemoriaTemaRepository.soleInstance;
    }

    @Override
    public long getProximoId() {
        return this.pool.size();
    }

    @Override
    public Tema getPorId(long idTema) {
        return pool.get((int) idTema);
    }

    @Override
    public Tema[] getPorNome(String nome) {
        ArrayList<Tema> temas = new ArrayList<>();
        for (Tema tema : this.pool) {
            if (tema.getNome().equals(nome)) {
                temas.add(tema);
            }
        }
        return temas.toArray(new Tema[0]);
    }

    @Override
    public Tema[] getTodos() {
        Tema[] todos = this.pool.toArray(new Tema[0]);

        return todos;
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        this.pool.add(tema);
    }

    @Override
    public void atualizar(Tema novoTema) throws RepositoryException {
        for (int i = 0; i < this.pool.size(); i++) {
            if (pool.get(i).getId() == novoTema.getId()) {
                pool.set(i, novoTema);
            }
        }
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        this.pool.remove(tema);
    }
}
