package br.org.quilombola.arquitetura.upload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Disco {
	
	@Autowired
	private Environment environment;
	
	public static final String DIRETORIO_FOTO_USUARIO = "img/users";
	
	private String raiz = "./static";
	
	private String diretorioFotos;
	
	public void salvarFoto(MultipartFile foto) {
		this.salvar(this.diretorioFotos, foto);
	}
	
	public void salvarFotoUsuario(MultipartFile foto, Long userId) throws IOException {
		Path path = Paths.get(environment.getProperty("image.path")+"/sigequi/"+Disco.DIRETORIO_FOTO_USUARIO);
		Files.createDirectories(path);
		String filename = StringUtils.cleanPath(userId.toString()+".jpg");
//		String filename = StringUtils.cleanPath(foto.getOriginalFilename());

		Path filePath = Paths.get(path.resolve(filename).toUri());
		Files.deleteIfExists(filePath);

		try (InputStream inputStream = foto.getInputStream()) {
            Files.copy(inputStream, path.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
//		this.salvar(environment.getProperty("image.path"), foto);
	}
	
	public void salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
}
