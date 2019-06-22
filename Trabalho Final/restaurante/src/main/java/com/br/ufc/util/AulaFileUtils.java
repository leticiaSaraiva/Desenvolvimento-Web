package com.br.ufc.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class AulaFileUtils {

	public static void salvarImagem(String caminho, @RequestParam(value="imagem") MultipartFile imagem) {
		File file = new File(caminho);
		
		try {
			FileUtils.writeByteArrayToFile(file, imagem.getBytes());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
