package br.ufes.nemo.integradoce.extrator.cih;

import org.eclipse.rdf4j.repository.RepositoryConnection;

import br.ufes.nemo.integradoce.extrator.cgd.Connection;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;

public class Extrator {
	
	public String consulta(String query) throws Exception {
		Connection SC = new Connection("http://200.137.66.31:5820", "RioDoceTest");
		RepositoryConnection repoConn = SC.getConnection();
		Repository repository = new Repository(repoConn);
		
		try {
			String x = repository.sparqlQueryretorna(query);
			SC.closeConnection();
			return x;
		} catch (Exception e) {
			// TODO: handle exception
			SC.closeConnection();
			return "Querry inapropriada";
		}
		
	}

}
