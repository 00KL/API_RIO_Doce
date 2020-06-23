//Classe principal que recebe a query de consulta ao banco
package br.ufes.nemo.integradoce.extrator.application;

import java.util.Scanner;
import java.util.ArrayList;


import org.eclipse.rdf4j.repository.RepositoryConnection; //Permite a utilizacao de funcoes para conectar-se ao banco

import br.ufes.nemo.integradoce.extrator.cgd.Connection;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;
import br.ufes.nemo.integradoce.extrator.cgt.GeographicPointApl;
import br.ufes.nemo.integradoce.extrator.cgt.MeasurementInSituAPL;
import br.ufes.nemo.integradoce.extrator.cgt.SamplingApl;
import br.ufes.nemo.integradoce.extrator.util.Reader;


public class Application {
	
	static void arquivoSample(String cabecalho[], ArrayList<String[]> tabela, Repository repository) {
		
		System.out.println("Begin");
		
		MeasurementInSituAPL m = new MeasurementInSituAPL(repository);
		SamplingApl s = new SamplingApl(repository);
		
		repository.beginStatment();
		for(int i = 0; i < tabela.size(); i++) {
			if(tabela.get(i)[4].contains("insitu")) {
				String[] linha = tabela.get(i);
				for(int j = 0; j < linha.length; j++) {
					m.post(cabecalho[j], linha[j], linha);		
				}
			}else {
				s.post(cabecalho, tabela.get(i));
			}
			
		}
		repository.commitStatment();
		
		System.out.println("End");
	}
	
	static void arquivoGeographicPoint(Repository repository, ArrayList<String[]> tabela ) {
		
		System.out.println("Begin");
		
		GeographicPointApl geo = new GeographicPointApl(repository);
		
		repository.beginStatment();
		for(int i = 0; i < tabela.size(); i++) {
			geo.post( tabela.get(i));	
		}
		repository.commitStatment();
		
		System.out.println("End");
	}
	
	public static void main(String[] args) throws Exception {
		//Inicia uma conexão com o banco
		Connection SC = new Connection("http://200.137.66.31:5820", "RioDoceTest");
		RepositoryConnection repoConn = SC.getConnection();
		Repository repository = new Repository(repoConn);
		//System.out.println("Criando conexão com o banco" + System.currentTimeMillis());
		
		//Pergunta para ecolha do usuário
		System.out.println("Esse código tem por objetivo carregar dados no banco.");
		System.out.println("É preciso que dados do tipo Pontos Geográficos sejam carregados primeiro,");
		System.out.println("pois esses são necessários para a carga de Amostras no banco.");
		System.out.println("Digite o caminho para o arquivo:");
		Scanner scanner = new Scanner(System.in); 
		String caminho = scanner.nextLine();
		
		
		//Le e organiza os dados
		Reader arquivoCsv = new Reader(caminho);
		////System.out.println(arquivoCsv.getCabecalho());C:\\Users\\Tieza\\Desktop\\Rio_doce\\dados_test\\agua.csv"
		ArrayList<String[]> tabela = arquivoCsv.getTabela();
		String cabecalho[] = arquivoCsv.getCabecalho();
		System.out.println("Arquivo lido com sucesso");
		
		//Pergunta o usuário qual tipo de arquivo será lido
		System.out.println("Qual tipo de arquivo será lido? \n 0 - Sair \n 1 - Ponto geográfico \n 2 - Amostra");
		
		int escolha = scanner.nextInt();
		scanner.close();
		switch (escolha) {
		case 0:
			System.out.println("Saindo");
			break;
			
		case 1:
			arquivoGeographicPoint(repository, tabela);
			break;

		case 2:
			arquivoSample(cabecalho, tabela, repository);
			break;
		
		default:
			System.out.println("Codigo não aceito, tente de novo.");
		}
		
		return;
		
	}
	
	
}





