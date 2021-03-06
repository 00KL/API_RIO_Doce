package br.ufes.nemo.integradoce.extrator.cgt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import br.ufes.nemo.integradoce.extrator.cdp.Sample;
import br.ufes.nemo.integradoce.extrator.cdp.Sampling;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;
import br.ufes.nemo.integradoce.extrator.util.Prefixos;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;


public class SamplingApl extends AbstractApl{
	
	public SamplingApl(Repository repository) {
		super(repository);
	}
	
	public void post(String cabecalho[], String[] linha){
		
		Sampling sampling = new Sampling();
		try {
//			Um hash MD5 é criado pegando uma sequência de qualquer comprimento e codificando-a em uma impressão digital de 128 bits. 
//			Codificar a mesma string usando o algoritmo MD5 sempre resultará na mesma saída de hash de 128 bits.
//			Tal encriptação é relevante pois não se tem uma representação de Sampling nas tabelas, mas é necessário utilizar uma Sampling 
//			a fim de criar uma Sample. Então se optou por encriptar a SampleRef(linha[4], um código único de cada sample da tabela), gerando assim 
//			um código único para cada Sampling. É importante ressaltar que não há como inferir que cada Sampling é única, é possivel apenas dizer que 
//			uma dada Sample possui uma Sampling.
			String md5Hex = DigestUtils
				      .md5Hex(linha[4]).toUpperCase();
			sampling.setSampling("WaterSampling" + md5Hex);
			
			IRI pointShortName = LongToShortName(linha[1], repository);
			sampling.setgeographicPoint(pointShortName);
			
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yy HH:mm");
			sampling.setDate(formatter1.parse(linha[3]));
		} catch (ParseException e) {
			System.out.println("Erro com formato da data.");
			e.printStackTrace();
		}
		sendStatement(cabecalho, linha, sampling);
	}
		
		public void sendStatement(String cabecalho[], String[] linha, Sampling sampling) {
		
			//Sujeito
			IRI newSampling = repository.createIRI(Prefixos.DATABASE.label, sampling.getSampling());
			
			//Objeto
			IRI samplingType = repository.createIRI(Prefixos.DOCE.label, "Sampling");
			//data é um objeto literal, por isso é criado na declaração do Statment
			
			
			//relações
			IRI locatedIn = repository.createIRI(Prefixos.DATABASE.label, "locatedIn");
			IRI hasBeginPointInXSDDateTimeStamp = repository.createIRI(Prefixos.DOCE.label, "hasBeginPointInXSDDateTimeStamp");
			IRI hasEndPointInXSDDateTimeStamp = repository.createIRI(Prefixos.DOCE.label, "hasEndPointInXSDDateTimeStamp");
			
			this.repository.addStatmentCluster(newSampling, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
			this.repository.addStatmentCluster(newSampling, RDF.TYPE, samplingType);
			this.repository.addStatmentCluster(newSampling, locatedIn, sampling.getgeographicPoint());
			this.repository.addStatmentCluster(newSampling, hasBeginPointInXSDDateTimeStamp, repository.createLiteralDate(sampling.getDate()));
			this.repository.addStatmentCluster(newSampling, hasEndPointInXSDDateTimeStamp, repository.createLiteralDate(sampling.getDate()));
			
			SampleApl sample = new SampleApl(repository);
			sample.post(cabecalho, linha, sampling.getgeographicPoint());
		}
		
	
}
