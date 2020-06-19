//Classe que monta as funções que serão necessárias para adicionar, remover, consultar e atualizar o banco de triplas
//utiliza a coneção gerada pela classe StardogConnection
package br.ufes.nemo.integradoce.extrator.cgd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;


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


public class Repository {

	private RepositoryConnection repoConn;

	public ValueFactory valueFactory;
	

	public Repository(RepositoryConnection repoConn) {
		super();
		this.repoConn = repoConn;
		this.valueFactory = repoConn.getValueFactory();
	}
	
	//INPUT: String
	//OUTPUT: IRI da string dada
	public IRI createIRI(String localName) {			
		return valueFactory.createIRI(localName);
	}
	
	//INPUT: String referente ao namespace(prefixo) e a variável local
	//OUTPUT: Concatena ambas em uma IRI
	public IRI createIRI(String namespace, String localName) {	
		return valueFactory.createIRI(namespace, localName);
	}
	
	//INPUT: Uma string
	//OUTPUT: Retorna uma representacao da string em formato XMLSchema.STRING.
	public Literal createLiteral(String literal) {
		return valueFactory.createLiteral(literal);
	}
	
	public Literal createLiteral(float literal) {
		return valueFactory.createLiteral(literal);
	}
	
	public Literal createLiteral(double literal) {
		return valueFactory.createLiteral(literal);
	}
	
	public Literal createLiteralDate(Date data) {
		return valueFactory.createLiteral(data);
	}
	
	
	
	//Inicia uma nova transação, exigindo que commitStatment () seja chamado para finalizar a transação.
	public void beginStatment() {
		this.repoConn.begin();
	}
	//Adiciona uma statament na transação com o banco aberta em beginStatament
	public void addStatmentCluster(Resource subject, IRI predicate, Value object) {
		this.repoConn.add(subject, predicate, object);
		
	}//Adiciona uma statament na transação com o banco aberta em beginStatament
	public void addStatmentCluster(Resource subject, IRI predicate, String object) {
		this.repoConn.add(subject, predicate, this.createLiteral(object));
	}
	//Confirma a transação ativa. Esta operação encerra a transação ativa.
	public void commitStatment() {
		this.repoConn.commit();
	}
		
	
	//Adiciona quey de insercao 
		public void addStatment(Resource subject, IRI predicate, Value object) {
			repoConn.begin();
			this.repoConn.add(subject, predicate, object);
			repoConn.commit();
		}
	//Adiciona query ao banco de dados com o objeto sendo uma string
	public void addStatment(Resource subject, IRI predicate, String object) {
		repoConn.begin();
		this.repoConn.add(subject, predicate, this.createLiteral(object));
		repoConn.commit();
	}
	
	//Recebe query de insercao e envia ao banco
	public void addStatment(String query) {
		//System.out.println(query);
		repoConn.begin();
		repoConn.prepareUpdate(query).execute();
		repoConn.commit();
	}
	
	
	//Remove via query de remoção
	public void rmStatment(IRI subject, IRI predicate, IRI object) {
		repoConn.begin();
		this.repoConn.add(subject, predicate, object);
		repoConn.commit();
	}
	
	//Remove via query de remoção com o objeto sendo uma string
	public void rmStatment(IRI subject, IRI predicate, String object) {
		repoConn.begin();
		this.repoConn.add(subject, predicate, this.createLiteral(object));
		repoConn.commit();
	}
	
	//Consulta o banco e printa saida no terminal
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
	
	//Consulta banco e retorna uma string com a saída
	public String sparqlQueryretorna(String query) throws TupleQueryResultHandlerException, QueryEvaluationException, UnsupportedQueryResultFormatException, IOException {
		TupleQuery tupleQuery = this.repoConn.prepareTupleQuery(QueryLanguage.SPARQL, query);
		try (TupleQueryResult results = tupleQuery.evaluate()) {
			try {
				//coloca a stream num arquivo temporário
				File temp = File.createTempFile("meu-arquivo-temporario", ".tmp"); //cria arquivo temporario, função pronta da biblioteca java.io.File;
				OutputStream output = new FileOutputStream(temp);
				QueryResultIO.writeTuple(results, TupleQueryResultFormat.TSV, output);
				output.flush();
				output.close();
				
				//le o arquivo e passa para string
				BufferedReader br = new BufferedReader(new FileReader(temp));
				
				//Cria e povoa o vetor de Strings
				String linha = "";
				
				while(br.ready()){
					linha = linha + br.readLine() + "\n";
					
				}
				
				br.close();
				
				//apaga arquivo temporário
				temp.delete();
				
				
				return linha;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		//TODO colocar mensagem de erro aqui
		return null;
	}

}
