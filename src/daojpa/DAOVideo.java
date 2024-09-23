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
	
	// ---------- Consultas ----------

	// Quais os vídeos de classificação X (requisito)
	public List<Video> videosPorClassificacao(int valor) {
	    TypedQuery<Video> q = manager.createQuery(
	        "SELECT v FROM Video v WHERE v.classificacao = :classificacao", Video.class);
	    q.setParameter("classificacao", valor);
	    
	    return q.getResultList();
	}

	// Quais os vídeos do gênero X (requisito)
	public List<Video> videosPorGenero(String nome) {
	    TypedQuery<Video> q = manager.createQuery(
	        "SELECT v FROM Video v JOIN v.generos g WHERE g.nome = :nome", Video.class);
	    q.setParameter("nome", nome);
	    
	    return q.getResultList();
	}

	// Quais os vídeos de título X (opcional)
	public List<Video> videosPorTitulo(String titulo) {
	    TypedQuery<Video> q = manager.createQuery(
	        "SELECT v FROM Video v WHERE v.titulo = :titulo", Video.class);
	    q.setParameter("titulo", titulo);
	    
	    return q.getResultList();
	}

	// Quais os vídeos de link X (opcional)
	public List<Video> videosPorLink(String link) {
	    TypedQuery<Video> q = manager.createQuery(
	        "SELECT v FROM Video v WHERE v.link = :link", Video.class);
	    q.setParameter("link", link);
	    
	    return q.getResultList();
	}

}