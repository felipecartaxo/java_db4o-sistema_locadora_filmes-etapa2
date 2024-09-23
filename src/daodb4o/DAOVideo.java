package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Video;

public class DAOVideo extends DAO<Video> {

	// Nome usado como campo único da classe Video
	public Video read (Object chave) {
		
		String titulo = (String) chave;	// Casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("titulo").constrain(titulo);
		List<Video> resultados = q.execute();
		
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}
	
	// Método sobrescrito da classe DAO para criar "id" sequencial 
	public void create(Video obj) {
		int novoid = super.gerarId();  	// Gera novo id da classe
		obj.setId(novoid);				// Atualiza id do objeto antes de gravá-lo no banco
		manager.store(obj);
	}
	
	// ---------- Consultas ----------
	
	// Quais os videos de classificação X (requisito)
	public List<Video> videosPorClassificacao (int valor) {
		
		// Quais os videos de título X
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("classificacao").constrain(valor);
		
		return q.execute();
	}
	
	// Quais os videos do gênero X (requisito)
	public List<Video> videosPorGenero(String nome) {

		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("generos").descend("nome").constrain(nome);
			
		return q.execute();
	}
	
	// Quais os videos de titulo X (opcional)
	public List<Video> videosPorTitulo (String titulo) {
		
		// Quais os videos de título X
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("titulo").constrain(titulo);
		
		return q.execute();
	}
	
	// Quais os vídeos de link X (opcional)
	public List<Video> videosPorLink (String link) {
		
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("link").constrain(link);
		
		return q.execute();
	}
}