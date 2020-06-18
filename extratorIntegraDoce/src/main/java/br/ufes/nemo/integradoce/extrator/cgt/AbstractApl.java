package br.ufes.nemo.integradoce.extrator.cgt;

import java.io.IOException;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.query.QueryEvaluationException;
import org.eclipse.rdf4j.query.TupleQueryResultHandlerException;
import org.eclipse.rdf4j.query.resultio.UnsupportedQueryResultFormatException;

import br.ufes.nemo.integradoce.extrator.cgd.Repository;

public abstract class AbstractApl {
	
	protected Repository repository;
	
	public AbstractApl(Repository repository) {
		this.repository = repository;
	}
	
	IRI LongToShortName(String longName, Repository repository) {
		try {
			String queryTest = "SELECT ?s WHERE{ ?s ?a ?o FILTER (?a = rdfs:label && ?o = \"" + longName + "\")}";
			String x = repository.sparqlQueryretorna(queryTest);
			if(x == null) {
				System.out.println("Ponto geografico n existe no banco, ou não foi possível se connectar com o banco.");
				System.out.println("Possíveis soluções: Carregue os dados dos pontos geográficos antes de carregar a amostra.");
				System.out.println("Cheque se o banco está acessível.");
				System.exit(1);
			}
			//retira ?s padrão do retorno(com o split da quebra de linha e seleçãodo segundo componente) e remove "< >" da IRI 
			String shortName = x.split("\n")[1].replace("<", "").replace(">", "");
			String nameSpaceAndPoint[] = shortName.split("#");
			IRI pointShortName = repository.createIRI(nameSpaceAndPoint[0]+"#", nameSpaceAndPoint[1]);
			return pointShortName;
		} catch (TupleQueryResultHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedQueryResultFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
