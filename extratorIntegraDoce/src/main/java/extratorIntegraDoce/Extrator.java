package extratorIntegraDoce;


import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.RDF;
//import org.eclipse.rdf4j.query.QueryEvaluationException;
//import org.eclipse.rdf4j.query.TupleQueryResultHandlerException;
//import org.eclipse.rdf4j.query.resultio.UnsupportedQueryResultFormatException;
import org.eclipse.rdf4j.repository.RepositoryConnection;

//"String[] args"

public class Extrator {
	
	public static void setRetorno(String retorn) {
	}

	public final static void main(String[] args) throws Exception {
		
//		String NS = "http://example.com/";
//		IRI IronMan = WD.createIRI(NS, "ironMan");
//		IRI BlackWidow = WD.createIRI(NS, "blackWidow");
//		IRI CaptainAmerica = WD.createIRI(NS, "captainAmerica");
//		IRI Thor = WD.createIRI(NS, "thor");
//		IRI IncredibleHulk = WD.createIRI(NS, "incredibleHulk");
		   
//		WD.addStatment(IronMan, RDF.TYPE, FOAF.PERSON);
//		WD.addStatment(IronMan, FOAF.NAME, "Anthony Edward Stark");
//		WD.addStatment(IronMan, FOAF.TITLE, "Iron Man");
//		WD.addStatment(IronMan, FOAF.GIVEN_NAME, "Anthony");
//		WD.addStatment(IronMan, FOAF.FAMILY_NAME, "Stark");
//		WD.addStatment(IronMan, FOAF.KNOWS, BlackWidow);
//		WD.addStatment(IronMan, FOAF.KNOWS, CaptainAmerica);
//		WD.addStatment(IronMan, FOAF.KNOWS, Thor);
//		WD.addStatment(IronMan, FOAF.KNOWS, IncredibleHulk);
		
//		StardogConnection SC = new StardogConnection("http://200.137.66.31:5820", "testDB");
//		RepositoryConnection repoConn = SC.getConnection();
//		WorkData WD = new WorkData(repoConn);
//		
//		//System.out.println(Formater.toDouble("a"));
//		String x = WD.sparqlQueryretorna("select * where { ?s ?p ?o } limit 10");
//		System.out.println(x);
	}

	public String getRetorno() throws Exception {
		StardogConnection SC = new StardogConnection("http://200.137.66.31:5820", "testDB");
		RepositoryConnection repoConn = SC.getConnection();
		WorkData WD = new WorkData(repoConn);
		
		String x = WD.sparqlQueryretorna("select * where { ?s ?p ?o } limit 10");
		System.out.println(x);
		
		return x;
	}
	
	public String consulta(String query) throws Exception {
		StardogConnection SC = new StardogConnection("http://200.137.66.31:5820", "testDB");
		RepositoryConnection repoConn = SC.getConnection();
		WorkData WD = new WorkData(repoConn);
		
		System.out.println("testando");
		
		String x = WD.sparqlQueryretorna(query);
		
		
		return x;
	}
}