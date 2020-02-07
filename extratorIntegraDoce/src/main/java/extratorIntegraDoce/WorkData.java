package extratorIntegraDoce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;


import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.query.TupleQueryResultHandlerException;
import org.eclipse.rdf4j.query.resultio.QueryResultIO;
import org.eclipse.rdf4j.query.resultio.TupleQueryResultFormat;
import org.eclipse.rdf4j.query.resultio.UnsupportedQueryResultFormatException;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class WorkData {

	private RepositoryConnection repoConn;

	ValueFactory valueFactory;

	public WorkData(RepositoryConnection repoConn) {
		super();
		this.repoConn = repoConn;
		this.valueFactory = repoConn.getValueFactory();
	}

	public IRI createIRI(String localName) {			
		return valueFactory.createIRI(localName);
	}
	
	public IRI createIRI(String namespace, String localName) {			
		return valueFactory.createIRI(namespace, localName);
	}
	
	public Literal createLiteral(String literal) {
		return valueFactory.createLiteral(literal);
	}
	
	public void addStatment(Resource subject, IRI predicate, Value object) {
		repoConn.begin();
		this.repoConn.add(subject, predicate, object);
		repoConn.commit();
	}
	
	public void addStatment(Resource subject, IRI predicate, String object) {
		repoConn.begin();
		this.repoConn.add(subject, predicate, this.createLiteral(object));
		repoConn.commit();
	}
	
	public void rmStatment(Resource subject, IRI predicate, Value object) {
		repoConn.begin();
		this.repoConn.remove(subject, predicate, object);
		repoConn.commit();
	}
	
	public void rmStatment(Resource subject, IRI predicate, String object) {
		repoConn.begin();
		this.repoConn.remove(subject, predicate, this.createLiteral(object));
		repoConn.commit();
	}

	public void sparqlQuery(String query) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		TupleQuery tupleQuery = this.repoConn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		try (TupleQueryResult results = tupleQuery.evaluate()) {
			try {
				QueryResultIO.writeTuple(results, TupleQueryResultFormat.TSV, System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String sparqlQueryretorna(String query) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		TupleQuery tupleQuery = this.repoConn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		try (TupleQueryResult results = tupleQuery.evaluate()) {
			try {
				//coloca a stream num arquivo temporário
				OutputStream output = new FileOutputStream("/tmp/input_text");
				QueryResultIO.writeTuple(results, TupleQueryResultFormat.TSV, output);
				output.flush();
				output.close();
				
				//le o arquivo e passa para string
				BufferedReader br = new BufferedReader(new FileReader("/tmp/input_text"));
				
				//Cria e povoa o vetor de Strings
				String linha = "";
				//Vector<String> l = new Vector<String>();
				while(br.ready()){
					linha = linha + br.readLine() + "\n";
					
				}
				br.close();
				
				//apaga arquivo temporário
				File file = new File( "/tmp/input_text" ); 
				file.delete();
				
				
				return linha;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//TODO colocar mensagem de erro aqui
		return null;
	}

}
