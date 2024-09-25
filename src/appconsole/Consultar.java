package appconsole;

import modelo.Genero;
import modelo.Video;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {

		try {
			Fachada.inicializar();
			System.out.println("consultas... \n");

			
			 System.out.println("\n===== Videos de classificação 5: =====");
			 
			 for(Video video : Fachada.videosPorClassificacao(5))
				 System.out.println(video);
			 
			 System.out.println("\n===== Vídeos cujo gênero é Suspense: =====");
			 for(Video video : Fachada.videosPorGenero("Suspense"))
				 System.out.println(video);
			 
			 System.out.println("\n===== Videos com o título 'Coraline': =====");
			 for(Video video : Fachada.videosPorTitulo("Coraline"))
				 System.out.println(video);
			 
			 System.out.println("\n===== Retorna um filme a partir do seu link: =====");
			 for(Video video : Fachada.videosPorLink("https://www.youtube.com/watch?v=yRUAzGQ3nSY"))
				 System.out.println(video);
			 
			 System.out.println("\n===== Gêneros com mais de 1 vídeo: =====");
			 for(Genero generos : Fachada.generosComMaisVideos(1)) System.out.println(generos);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}