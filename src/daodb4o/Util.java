package daodb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import modelo.Genero;
import modelo.Video;

public class Util {
	private static ObjectContainer manager = null;
	
	public static ObjectContainer conectarBanco() {
		
		if (manager != null)
			return manager;		// Caso exista uma conexão, retorna a conexão existente

		// Configurar, criar e abrir banco local (na pasta do projeto)
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // mensagens na tela 0(desliga),1,2,3...
		
		// Configuração do cascade para a classe Video
		config.common().objectClass(Video.class).cascadeOnDelete(false); // Ao apagar um vídeo, os gêneros relacionados não deverão ser apagados
		config.common().objectClass(Video.class).cascadeOnUpdate(true);
		config.common().objectClass(Video.class).cascadeOnActivate(true);
		
		// Configuração do cascade para a classe Genero
		config.common().objectClass(Genero.class).cascadeOnDelete(false); // Ao apagar um gênero, os vídeos relacionados não deverão ser apagados
		config.common().objectClass(Genero.class).cascadeOnUpdate(true);
		config.common().objectClass(Genero.class).cascadeOnActivate(true);

		// Conexão local
		manager = Db4oEmbedded.openFile(config, "banco.db4o");
		return manager;
	}

	public static void desconectar() {
		if(manager != null) {
			manager.close();
			manager = null;
		}
	}
}