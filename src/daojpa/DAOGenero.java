package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Genero;

public class DAOGenero extends DAO<Genero> {
	
	public Genero read(Object chave) {
		
		try {
			String nome = (String) chave;
			TypedQuery<Genero> q = manager.createQuery(
		        "select g from Genero g where g.nome = :nome", Genero.class);
		    q.setParameter("nome", nome);
		    
		    return q.getSingleResult();
		}
		
		catch(NoResultException e ) {
			return null;
		}
	}
	
	public List<Genero> readAll() {
		TypedQuery<Genero> q = manager.createQuery("select g from Genero g LEFT JOIN FETCH g.videos order by g.id", Genero.class);
		
		return q.getResultList();
	}
	
	// ---------- Consultas ----------

	// Quais os gêneros que têm um nome específico (opcional)
	public List<Genero> generosPorNome(String nome) {
	    TypedQuery<Genero> q = manager.createQuery(
	        "SELECT g FROM Genero g WHERE g.nome = :nome", Genero.class);
	    q.setParameter("nome", nome);
	    
	    return q.getResultList();
	}

	// Quais os gêneros que têm mais de N vídeos (requisito)
	public List<Genero> generosComMaisVideos(int valor) {
	    TypedQuery<Genero> q = manager.createQuery(
	        "SELECT g FROM Genero g WHERE SIZE(g.videos) > :valor", Genero.class);
	    q.setParameter("valor", valor);
	    
	    return q.getResultList();
	}
}