package extratorIntegraDoce;


import org.eclipse.rdf4j.model.IRI; //Permite a criação de Internationalized Resource Identifiers (IRIs)
import org.eclipse.rdf4j.model.vocabulary.FOAF; //Permite a utilizacao do prefixo Friend Of A Friend (FOAF) a fim de utilizar predicados já definidos 
import org.eclipse.rdf4j.model.vocabulary.RDF; //Permite a utilizacao do prefixo Resource Description Framework (RDF) a fim de utilizar predicados já definidos
import org.eclipse.rdf4j.repository.RepositoryConnection; //Permite a utilizacao de funcoes para conectar-se ao banco


public class Extrator {
	
	//INPUT: Uma string 
	//OUTPUT: Caso a string esteja bem formada dentro dos padrões estabelicidos pela linguagem SPARQL retorna o resultado da consulta
	//		  Caso contrário retorna uma mensagem de erro alertando sobre a mal formacao da string de consulta
	public String consulta(String query) throws Exception {
		StardogConnection SC = new StardogConnection("http://200.137.66.31:5820", "testDB");
		RepositoryConnection repoConn = SC.getConnection();
		WorkData WD = new WorkData(repoConn);
		
		try {
			String x = WD.sparqlQueryretorna(query);
			SC.closeConnection();
			return x;
		} catch (Exception e) {
			// TODO: handle exception
			SC.closeConnection();
			return "Querry inapropriada";
		}
		
		
	}
	
	//INPUT: Uma lista de strings
	//OUTPUT: Caso a string esteja bem formada dentro dos padrões estabelicidos pela linguagem SPARQL printa no console o resultado da consulta
	//		  Caso contrário printa no console uma mensagem de erro alertando sobre a mal formacao da string de consulta
	public final static void main(String[] args) throws Exception {
		
		StardogConnection SC = new StardogConnection("http://200.137.66.31:5820", "testDB");
		RepositoryConnection repoConn = SC.getConnection();
		WorkData WD = new WorkData(repoConn);
		
		//System.out.println(Formater.toDouble("a"));
		try {
			String x = WD.sparqlQueryretorna("select * where { <http://example.com/ironMan> ?p ?o } limit 10");
			System.out.println(x);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Querry inapropriada");
			return;
		}
		
		SC.closeConnection();
		
	}
}

//exemplos de adicao
//WD.addStatment(IronMan, RDF.TYPE, FOAF.PERSON);
//WD.addStatment(IronMan, FOAF.NAME, "Anthony Edward Stark");
//WD.addStatment(IronMan, FOAF.TITLE, "Iron Man");
//WD.addStatment(IronMan, FOAF.GIVEN_NAME, "Anthony");
//WD.addStatment(IronMan, FOAF.FAMILY_NAME, "Stark");
//WD.addStatment(IronMan, FOAF.KNOWS, BlackWidow);
//WD.addStatment(IronMan, FOAF.KNOWS, CaptainAmerica);
//WD.addStatment(IronMan, FOAF.KNOWS, Thor);
//WD.addStatment(IronMan, FOAF.KNOWS, IncredibleHulk);

//exemplo de remocao
//WD.rmStatment(IronMan, FOAF.KNOWS, "<http://example.com/test>");

//exemplo de criação de prefixo e sua utilizacao
//String NS = "http://example.com/";
//IRI IronMan = WD.createIRI(NS, "ironMan");
//IRI BlackWidow = WD.createIRI(NS, "blackWidow");
//IRI Thor = WD.createIRI(NS, "OMiranha");





