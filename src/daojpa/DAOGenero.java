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
}