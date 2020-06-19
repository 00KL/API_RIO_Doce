package br.ufes.nemo.integradoce.extrator.cgt;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import br.ufes.nemo.integradoce.extrator.cdp.Sample;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;
import br.ufes.nemo.integradoce.extrator.util.Prefixos;
import br.ufes.nemo.integradoce.extrator.util.PropertiesUtil;


public class SampleApl extends AbstractApl {
	
	public SampleApl(Repository repository) {
		super(repository);
	}
	
	//precisa receber o cabeçalho para identificar o elemento da coluna
		public void post(String cabecalho[], String[] linha, Repository repository){
			Sample sample = new Sample();
			sample.setWaterSample("WaterSample" + linha[4].replaceAll("\\.", ""));
			sample.setWaterSampling("WaterSampling" + linha[4].replaceAll("\\.", ""));
			
			                                                   
			sendStatement(cabecalho, linha, sample);                                 
		}
		
		
		public void sendStatement(String cabecalho[], String[] linha, Sample sample) {
			
			//tripla -> sujeito relação objeto
				
//			Sujeito
			IRI newSample = repository.createIRI(Prefixos.DATABASE.label, sample.getWaterSample()); 
			
//			Objeto
			IRI samplingType  = repository.createIRI(Prefixos.DOCE.label , sample.getWaterSampling()); 
			IRI surfaceWaterSample = repository.createIRI(Prefixos.DOCE.label, "SurfaceWaterSample");
			IRI RioDoce = repository.createIRI(Prefixos.DOCE.label, "RioDoce");
			IRI pointShortName = LongToShortName(linha[1], repository);
		
//			Relações
			IRI wasCreatedIn = repository.createIRI(Prefixos.GUFO.label, "wasCreatedIn");
			IRI represents = repository.createIRI(Prefixos.DOCE.label, "represents");
			
			repository.addStatmentCluster(newSample, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
			repository.addStatmentCluster(newSample, RDF.TYPE, surfaceWaterSample);
			repository.addStatmentCluster(newSample, wasCreatedIn, samplingType);
			repository.addStatmentCluster(newSample, represents, RioDoce);
			
			SamplingApl samplingApl = new SamplingApl(repository);
			samplingApl.post(linha, repository, pointShortName);
			
			postAllMeasurement(cabecalho, linha, repository, newSample, pointShortName);
			
		}
		
		
		void postAllMeasurement(String cabecalho[], String[] linha, Repository repository, IRI sample, IRI pointShortName) {
			
			String elementoQuimicoStr = null;
			MeasurementApl elementoQuimico = new MeasurementApl(repository);
			PropertiesUtil propertiesUtil = new PropertiesUtil(); // consulta o arquivo src/main/resources/elementosFisicoQuimicos.properties
															  // para checar os elementos estabelecidos pela ontologia.
																  // para se acrescentar novos elementos fisicos ou quimicos de uma 
																  // medição basta altear acrescenta-los no arquivo no formato 
																  // chave-para-encontrar-o-elemento = IRI-do-elemento,IRI-da-unidade-de-medida-do-elemento
//			  doce:wasMeasuredIn :WaterMeasurementAlkalinity001
			IRI wasMeasuredIn = repository.createIRI(Prefixos.DOCE.label, "wasMeasuredIn");
			
			for(int i = 0; i < linha.length; i++) {
				if(!linha[i].isEmpty()) {
					elementoQuimicoStr = propertiesUtil.consultaElemento(cabecalho[i]);
					//System.out.println(elementoQuimicoStr);
					if(elementoQuimicoStr != null) {
						IRI measurement = elementoQuimico.post(cabecalho[i], linha[4].replaceAll("\\.", ""), linha[i], linha[3], elementoQuimicoStr.split(","), pointShortName);
						repository.addStatmentCluster(sample, wasMeasuredIn, measurement);
				
					}
					
				}
			}
		}	
}
