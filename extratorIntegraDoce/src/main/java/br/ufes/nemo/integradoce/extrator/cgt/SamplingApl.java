package br.ufes.nemo.integradoce.extrator.cgt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import br.ufes.nemo.integradoce.extrator.cdp.Sampling;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;
import br.ufes.nemo.integradoce.extrator.util.Prefixos;

public class SamplingApl extends AbstractApl{
	
	public SamplingApl(Repository repository) {
		super(repository);
	}
	
	public void post(String[] strings, Repository repository){
		Sampling sampling = new Sampling();
		try {	
			sampling.setSampling("WaterSampling" + strings[4]);
			sampling.setPointLongName(strings[1]);
			
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sampling.setDate(formatter1.parse(strings[3]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//System.out.println("Erro com formato da data.");
			e.printStackTrace();
		}
		sendStatement(sampling);
	}
		
		public void sendStatement(Sampling sampling) {
		
			//Sujeito
			IRI newSampling = repository.createIRI(Prefixos.DATABASE.label, sampling.getSampling());
			
			//Objeto
			IRI samplingType = repository.createIRI(Prefixos.DOCE.label, "Sampling");
			//data é um objeto literal, por isso é criado na declaração do Statment
			
			IRI pointShortName = LongToShortName(sampling.getPointLongName(), repository);
			
			
			//relações
			IRI locatedIn = repository.createIRI(Prefixos.DATABASE.label, "locatedIn");
			IRI hasBeginPointInXSDDateTimeStamp = repository.createIRI(Prefixos.DOCE.label, "hasBeginPointInXSDDateTimeStamp");
			IRI hasEndPointInXSDDateTimeStamp = repository.createIRI(Prefixos.DOCE.label, "hasEndPointInXSDDateTimeStamp");
			
			repository.addStatmentCluster(newSampling, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
			repository.addStatmentCluster(newSampling, RDF.TYPE, samplingType);
			repository.addStatmentCluster(newSampling, locatedIn, pointShortName);
			repository.addStatmentCluster(newSampling, hasBeginPointInXSDDateTimeStamp, repository.createLiteralDate(sampling.getDate()));
			repository.addStatmentCluster(newSampling, hasEndPointInXSDDateTimeStamp, repository.createLiteralDate(sampling.getDate()));			
		}
	
}
