package appconsole;

import regras_negocio.Fachada;

public class Alterar {

	public Alterar(){
		Fachada.inicializar();
		// Alteração 1
		try {
			Fachada.alterarTituloDoVideo("Video1", "Blade Runner");
			System.out.println("alterado nome Video1 para Blade Runner");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Alteração 2
		try {
			Fachada.alterarClassificacaoDoVideo("Video2", 4);;
			System.out.println("alterado classificacao do Video2 para 4");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Alterar();
	}
}