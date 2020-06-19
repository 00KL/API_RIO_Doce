package br.ufes.nemo.integradoce.extrator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	// consulta o arquivo src/main/resources/elementosFisicoQuimicos.properties
	  // para checar os elementos estabelecidos pela ontologia.
	  // para se acrescentar novos elementos fisicos ou quimicos de uma 
	  // medição basta altear acrescenta-los no arquivo no formato 
	  // chave-para-encontrar-o-elemento = IRI-do-elemento,IRI-da-unidade-de-medida-do-elemento
	 public String consultaElemento(String elemento) {		 
		 
		 try (InputStream input = getClass().getClassLoader().getResourceAsStream("PhysicalChemicalQualityKind.properties")) {
	            Properties prop = new Properties();

	            //carrega o arquivo properties usando o caminho dado, dentro de um metodo estático
	            prop.load(input);
	            
	            //consulta se o elemento existe no arquivo property
	            //caso não exista se retorna null.
	            //caso exista são retornados tanto uma string representando a IRI do elemento, 
	            //		quanto a unidade de medida por ele adotada separada por virgula
	            //ex: http://purl.org/nemo/doce#TotalAlkalinityAsCaCO3,http://qudt.org/vocab/unit#MilliGM-PER-L
	            //System.out.println(prop.get(elemento));
	            return prop.getProperty(elemento);
	            

	        } catch (IOException ex) {
	        	System.out.println("Desculpe, não encontrei o arquivo config.properties");
	            ex.printStackTrace();
	        }
		 
		 return null;
	 }

}
