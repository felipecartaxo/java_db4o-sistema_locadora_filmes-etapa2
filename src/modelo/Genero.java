package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity // Indica que a classe Genero é uma entidade do banco
public class Genero {
	
	// Atributos
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	
	@ManyToMany(mappedBy="generos", cascade= {CascadeType.PERSIST, CascadeType.ALL})
	private List<Video> videos = new ArrayList<>(); // Um gênero pode estar relacionado com um ou mais vídeos
	
	// Construtor padrão
	public Genero() {
	}
	
	// Construtor com argumentos
	public Genero(String nome) {
		this.nome = nome;
	}

	// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// Retorna a lista de vídeos
	public List<Video> getVideos() {
		return videos;
	}
	
	// Adiciona um vídeo à lista
	public void adicionarVideo(Video video) {
		videos.add(video);
	}
	
	// Remove um vídeo da lista
	public void removerVideo(Video video) {
		videos.remove(video);
	}
	
	// Localiza um vídeo
	/*
	 * public Video localizarVideo(String nome) { for (Video v : videos) {
	 * if(v.getTitulo().equals(nome)) { return v; } }
	 * 
	 * return null; }
	 */
	
	// toString
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n====== " + nome + " =====");
		
		for(Video v : videos) {
			if(v.getGeneros() != null) {
				sb.append("\nVideo: " + v.getTitulo());
			}
		};
		
		return sb.toString();
	}
}