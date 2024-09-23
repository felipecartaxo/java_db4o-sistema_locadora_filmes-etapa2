package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando videos...");
			
			Fachada.criarVideo("O labirinto do fauno", "https://youtu.be/jVZRnnVSQ8k", 5);
			Fachada.criarVideo("A viagem de Chihiro", "https://www.youtube.com/watch?v=ByXuk9QqQkk", 5);
			Fachada.criarVideo("Elementos", "https://www.youtube.com/watch?v=hXzcyx9V0xw", 2);
			Fachada.criarVideo("Coraline", "https://www.youtube.com/watch?v=m9bOpeuvNwY", 5);
			Fachada.criarVideo("Blade Runner", "https://www.youtube.com/watch?v=gCcx85zbxz4", 3);
			Fachada.criarVideo("Divertidamente", "https://www.youtube.com/watch?v=yRUAzGQ3nSY", 3);
			Fachada.criarVideo("Divertidamente 2", "https://www.youtube.com/watch?v=u69y5Ie519M", 3);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		 try { System.out.println("cadastrando generos...");
		 	Fachada.criarGenero("Ação"); Fachada.criarGenero("Aventura");
		 	Fachada.criarGenero("Suspense"); Fachada.criarGenero("Animação");
		 	Fachada.criarGenero("Romance"); Fachada.criarGenero("Terror");
		 }
		 
		 catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		 
		 try { System.out.println("categorizando filmes...");
		 
		 Fachada.categorizarVideo("O labirinto do fauno", "Suspense");
		 Fachada.categorizarVideo("O labirinto do fauno", "Terror");
		 Fachada.categorizarVideo("A viagem de Chihiro", "Animação");
		 Fachada.categorizarVideo("A viagem de Chihiro", "Aventura");
		 Fachada.categorizarVideo("Elementos", "Animação");
		 Fachada.categorizarVideo("Elementos", "Romance");
		 Fachada.categorizarVideo("Coraline", "Animação");
		 Fachada.categorizarVideo("Coraline", "Suspense");
		 Fachada.categorizarVideo("Blade Runner", "Aventura");
		 Fachada.categorizarVideo("Divertidamente", "Animação");
		 Fachada.categorizarVideo("Divertidamente 2", "Animação");
		 }
		 
		 catch (Exception e) { System.out.println(e.getMessage()); }
		 

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Cadastrar();
	}
}