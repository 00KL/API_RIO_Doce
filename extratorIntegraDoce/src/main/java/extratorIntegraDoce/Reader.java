package extratorIntegraDoce;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

// https://commons.apache.org/proper/commons-csv/index.html

public class Reader {
	
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
	public static String chooserCSV() {
		
		JFileChooser choosen = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
        choosen.setFileFilter(filter);
        return (choosen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) ? choosen.getSelectedFile().getAbsolutePath() : null;
        
	}
	
}
