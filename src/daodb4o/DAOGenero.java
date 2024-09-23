package daodb4o;

import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Genero;

public class DAOGenero extends DAO<Genero> {

	// Nome usado como campo único da classe Genero
	public Genero read (Object chave) {
		
		String nome = (String) chave;	// Casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Genero.class);
		q.descend("nome").constrain(nome);
		List<Genero> resultados = q.execute();
		
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}
	
	// ---------- Consultas ----------
	
	// Quais os vídeos que pertencem a um determinado gênero (opcional)
		public List<Genero> videosPorGenero(String nome) {
		    
			Query q = manager.query();
		    q.constrain(Genero.class);
		    q.descend("nome").constrain(nome);
		    ObjectSet<Genero> resultados = q.execute();
		    
		    return resultados;
		}
		
	// Quais os gêneros que tem mais de N vídeos (requisito)
	public List<Genero> generosComMaisVideos (int valor) {
		
		 Query q = manager.query();
		 q.constrain(Genero.class);
		 q.constrain( new Filtro(valor) );
		 List<Genero> generosComMaisVideos = q.execute();
		 	        
		 return generosComMaisVideos;
	}
}

// ---------- Implementação do filtro para a consulta 'generosComMaisVideos' ----------
class Filtro implements Evaluation {
    private int valor; // Como a consulta em questão vai receber um valor N, é necessário colocar valor como sendo um atributo da classe Filtro

    public Filtro(int valor) { // Construtor do Filtro
        this.valor = valor;
    }

    public void evaluate(Candidate candidate) {
        Genero g = (Genero) candidate.getObject();
        if (g.getVideos().size() > valor) { // Verifica quais os gêneros com mais de N filmes
            candidate.include(true); // Caso o números de filmes seja maior do que N, o gênero em questão vai pro resultado da consulta
        } else {
            candidate.include(false); // Caso contrário, será descartado
        }
    }
}