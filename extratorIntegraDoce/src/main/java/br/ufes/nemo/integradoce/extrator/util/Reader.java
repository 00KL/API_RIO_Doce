package br.ufes.nemo.integradoce.extrator.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;


public class Reader {
	private String[] cabecalho;
	private ArrayList< String[] > tabela = new ArrayList< String[] >();
	
	public Reader (String caminhoParaArquivoCsv){
		String row;
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(caminhoParaArquivoCsv));
			
			String cabecalhoCompleto = csvReader.readLine();
			this.cabecalho = setCabecalho(cabecalhoCompleto);
			
			while ((row = csvReader.readLine()) != null) {	    
			    tabela.add(row.split("\\t"));
			    
			}
			csvReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao carregar o arquivo, programa finalizado.");
			System.exit(1);
		}
		
	}
	
	//Em Measurement.java os nomes das colunas são usados para identificar 
	//as measurement, e assim é necessário tirar os espaços e as unidades de
	//medida.
	//Ex: Aluminio total (mg/L) -> Aluminio-total
	public String[] setCabecalho(String cabecalhoCompleto) {
		//cabecalhoCompleto = cabecalhoCompleto.replaceAll("\\s", "-");
	
		String[] cabecalho = cabecalhoCompleto.split("\\t");
		
		for(int i = 7; i < cabecalho.length; i++) {			
			cabecalho[i] = cabecalho[i].replaceAll("\\s", "-");
			
			if(cabecalho[i].contains("(")) {
				cabecalho[i] = cabecalho[i].replace("-(", "(");
				cabecalho[i] = cabecalho[i].split("\\(")[0];
			}
		}
		return cabecalho;
	}
	
	public String[] getCabecalho() {
		return this.cabecalho;
	}
	
	public ArrayList<String[]> getTabela(){
		return this.tabela;
	}
	
	
	// Return a CSVParser (used by iterators and CSVRecord)
	public static CSVParser headerAutoDetection(String path, char del, char quote, String endl) {
		
		try {
			return CSVFormat.DEFAULT
				   .withDelimiter(del)
				   .withQuote(quote)
				   .withRecordSeparator(endl)
				   .withIgnoreEmptyLines(true)
				   .withFirstRecordAsHeader().parse(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Chooser
	public static String chooserTSV() {
		
		JFileChooser choosen = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.tsv", "tsv");
        choosen.setFileFilter(filter);
        return (choosen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) ? choosen.getSelectedFile().getAbsolutePath() : null;
        
	}
	
	
	
}
