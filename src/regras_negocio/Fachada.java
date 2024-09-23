package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOGenero;
import daodb4o.DAOVideo;
import modelo.Genero;
import modelo.Video;

public class Fachada {
	private Fachada() {}

	private static DAOVideo daovideo = new DAOVideo();
	private static DAOGenero daogenero = new DAOGenero();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Video criarVideo(String titulo, String link, int classificacao) throws Exception {
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		// Lança exceção caso já exista um filme com este título
		if (video != null) {
			throw new Exception("Video ja cadastrado: " + titulo);
		}
		
		// Caso contrário, o vídeo em questão é instanciado
		video = new Video(titulo, link, classificacao);
		
		daovideo.create(video);
		DAO.commit();
		
		return video;
	}
	
	// Adiciona um genero a um video
	public static void categorizarVideo (String titulo, String nome) throws Exception {
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		
		// Lança exceção caso o vídeo não exista
		if (video == null) {
			throw new Exception("Video inexistente: " + titulo);
		}
		
		Genero genero = daogenero.read(nome);
		
		// Lança exceção ao tentar categorizar um vídeo com um gênero inexistente
		if (genero == null) {
			throw new Exception("Genero inexistente: " + nome);
		}
		
		video.adicionarGenero(genero);
		genero.adicionarVideo(video);
		
		daovideo.update(video);
		daogenero.update(genero);
		
		DAO.commit();
	}
	
	// Altera o titulo de um video já existente no banco
	public static void alterarTituloDoVideo(String titulo, String novoTitulo) throws Exception {
			
		DAO.begin();
		Video video = daovideo.read(titulo);
		
		// Lança exceção caso o vídeo não exista
		if(video == null) {
			throw new Exception ("titulo do video inexistente: " + titulo);
		}
		
		// Lança exceção se o vídeo já possuir o título passado como argumento
		if(video.getTitulo() == titulo) {
			throw new Exception ("video ja possui este titulo: " + titulo);
		}
		
		video.setTitulo(novoTitulo);
		DAO.commit();
	}
	
	// Altera a classificação de um video já existente no banco
	public static void alterarClassificacaoDoVideo(String titulo, int classificacao) throws Exception {
		DAO.begin();
		Video video = daovideo.read(titulo);
		
		// Lança exceção caso o vídeo não exista
		if(video == null) {
			throw new Exception ("video inexistente: " + titulo);
		}
		
		// Lança exceção se o vídeo já possuir a classificação passada como argumento
		if(video.getClassificacao() == classificacao) {
			throw new Exception ("video ja possui esta classificacao: " + titulo);
		}
		
		video.setClassificacao(classificacao);
		DAO.commit();
	}
	
	// Deleta um vídeo
	public static void excluirVideo(String titulo) throws Exception{
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		
		// Lança exceção caso o vídeo a ser excluído não exista
		if(video == null) {
			throw new Exception ("video incorreto para exclusao " + titulo);
		}
		
		// Apaga os vídeos dos gêneros onde este vídeo estava inserido
		for (Genero g : video.getGeneros()) {
			g.removerVideo(video); // Remove o vídeo a ser apagado da lista de gêneros em que o mesmo estava associado
			daogenero.update(g);
		}
		
		// Apaga o vídeo sem apagar os gêneros, pois podem existir outros filmes que compartilham do mesmo gênero
		daovideo.delete(video);
		DAO.commit();
	}
	
	// Cria um gênero
	public static Genero criarGenero(String nome) throws Exception {
		
		DAO.begin();
		Genero genero = daogenero.read(nome);
		
		// Lança exceção se o gênero já existir
		if (genero != null) {
			throw new Exception("Genero ja cadastrado: " + nome);
		}
		
		genero = new Genero(nome);
			
		daogenero.create(genero);
		DAO.commit();
			
		return genero;
	}
	
//	// Adiciona um video na lista de videos de um genero
//	public static void associarFilmeComGenero (Video video, String nome) throws Exception {
//		
//		DAO.begin();
//		Genero genero = daogenero.read(nome);
//		
//		if (genero == null) {
//			throw new Exception("Genero inexistente");
//		}
//		
//		genero.adicionarVideo(video);
//		
//	}
	
	// Deleta um gênero
	public static void excluirGenero(String nome) throws Exception{
		
		DAO.begin();
		Genero genero = daogenero.read(nome);
		
		// Lança exceção se o gênero a ser excluído não existir
		if(genero == null) {
			throw new Exception ("genero incorreto para exclusao " + nome);
		}
		
		// Apaga os gêneros dos gêneros onde este vídeo estava inserido
		for (Video v : genero.getVideos()) {
			v.removerGenero(genero); // Remove o vídeo a ser apagado da lista de gêneros em que o mesmo estava associado
			daovideo.update(v);
		}
		
		// Apaga o genero sem apagar os videos, pois os videos podem conter outros gêneros ou podem simplesmente ficar sem gênero até que outro possa ser incluido
		daogenero.delete(genero);
		DAO.commit();
	}
	
	// Lista todos os vídeos
	public static List<Video> listarVideos() {
		List<Video> resultado = daovideo.readAll();
		
		return resultado;
	}
	
	// Lista todos os gêneros
	public static List<Genero> listarGeneros() {
		List<Genero> resultado = daogenero.readAll();
		
		return resultado;
	}
	
	// ---------- Consultas ----------
	
	public static List<Video> videosPorClassificacao(int valor) {
		List<Video> resultado = daovideo.videosPorClassificacao(valor);
		
		return resultado;
	}
	
	public static List<Video> videosPorTitulo(String titulo) {
		List<Video> resultado = daovideo.videosPorTitulo(titulo);
		
		return resultado;
	}
	
	public static List<Video> videosPorLink(String link) {
		List<Video> resultado = daovideo.videosPorLink(link);
		
		return resultado;
	}
	
	public static List<Video> videosPorGenero(String nome){	
		List<Video> resultado =  daovideo.videosPorGenero(nome);
		
		return resultado;
	}
	
	public static List<Genero> generosComMaisVideos(int valor) {
		List<Genero> resultado = daogenero.generosComMaisVideos(valor);
		
		return resultado;
	}
}