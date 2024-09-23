package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity // Indica que a classe Video é uma entidade do banco
public class Video {
	
	// Atributos
	
	// Indica que o atributo id será a chave primária e o mesmo será gerado automaticamente pelo JPA
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; // Gerado automaticamente no DAOVideo
	private String titulo;
	private String link;
	private int classificacao; // De 1 a 5
	
	// Indica que o relacionamento com a classe Genero é muitos para muitos, mas que a classe Video é o lado dominante
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.ALL})
	private ArrayList<Genero> generos = new ArrayList<>(); // Um vídeo possui um ou mais gêneros
	
	// Construtor padrão
	public Video () {}
	
	// Construtor com argumentos
	public Video (String titulo, String link, int classificacao) throws Exception {
		
		// Lança exceção se a classificação for menor do que 1 ou maior do 5
		if (classificacao < 1 || classificacao > 5) {
			throw new Exception("Classificação de 1 a 5");
		}
		
		this.titulo = titulo;
		this.link = link;
		this.classificacao = classificacao;
	}

	// Getters e settters
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}

	public String getTitulo () {
		return titulo;
	}

	public void setTitulo (String titulo) {
		this.titulo = titulo;
	}

	public String getLink () {
		return link;
	}

	public void setLink (String link) {
		this.link = link;
	}

	public int getClassificacao () {
		return classificacao;
	}

	public void setClassificacao (int classificacao) {
		this.classificacao = classificacao;
	}
	
	// Retorna a lista de gêneros
	public ArrayList<Genero> getGeneros() {
		return generos;
	}
	
	// Adiciona um gênero à lista
	public void adicionarGenero (Genero genero) throws Exception {
		
		// Lança exceção caso o gênero passado como argumento já esteja presente na lista de gêneros
		if(generos.contains(genero)) {
			throw new Exception("O filme não pode ter 2 gêneros iguais");
		}
		
		generos.add(genero);
	}
	
	// Remove um gênero da lista
	public void removerGenero (Genero genero) {
		generos.remove(genero);
	}
	
	// toString
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nVideo: " + id + ", " + titulo + ", " + link + ", " + classificacao + "\nGeneros: ");
		
		for(Genero g : generos){
			sb.append(g.getNome() + " ");
		};
		
		return sb.toString();
	}
}