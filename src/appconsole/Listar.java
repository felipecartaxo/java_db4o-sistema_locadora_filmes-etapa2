package appconsole;

import modelo.Genero;
import modelo.Video;
import regras_negocio.Fachada;

public class Listar {

	public Listar(){
		try {
			Fachada.inicializar();

			System.out.println("\n*** Listagem de videos:");
			for(Video video : Fachada.listarVideos()) {
				System.out.println(video);
			}

			System.out.println("\n*** Listagem de generos:");
			for(Genero genero : Fachada.listarGeneros()) {
				System.out.println(genero);
			}
				
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
	}
	
	public static void main(String[] args) {
		new Listar();
	}
}