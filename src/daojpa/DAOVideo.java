package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Video;

public class DAOVideo extends DAO<Video> {
	
	public Video read(Object chave) {
		
		try {
			String titulo = (String) chave;
			TypedQuery<Video> q = manager.createQuery(
		        "select v from Video v where v.titulo = :titulo", Video.class);
		    q.setParameter("titulo", titulo);
		    
		    return q.getSingleResult();
		}
		
		catch(NoResultException e ) {
			return null;
		}
	}
	
	public List<Video> readAll() {
		TypedQuery<Video> q = manager.createQuery("select v from Video v LEFT JOIN FETCH v.generos order by v.id", Video.class);
		
		return q.getResultList();
	}
}