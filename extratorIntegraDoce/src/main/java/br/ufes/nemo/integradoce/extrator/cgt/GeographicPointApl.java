package br.ufes.nemo.integradoce.extrator.cgt;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;

import br.ufes.nemo.integradoce.extrator.cdp.GeographicPoint;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;
import br.ufes.nemo.integradoce.extrator.util.Prefixos;

public class GeographicPointApl extends AbstractApl{
	
	public GeographicPointApl(Repository repository) {
		super(repository);
	}
	
	
	public void post(String[] strings){
		
		GeographicPoint geographicPoint = new GeographicPoint();
		
		geographicPoint.setGeograficPoint (strings[2].replaceAll("\\s", "-"));
		geographicPoint.setLabel (strings[1]);
		geographicPoint.setComment(strings[11]);
		geographicPoint.setLatitude(strings[3].replaceAll(",", "."));
		geographicPoint.setLongitude(strings[4].replaceAll(",", "."));
		
		this.sendStatement(geographicPoint);
	}
	
	private void sendStatement(GeographicPoint geographicPoint) {
		
		//objetos
		IRI newGeographicPoint = this.repository.createIRI(Prefixos.DATABASE.label.toString(), geographicPoint.getGeograficPoint());
		IRI geographicPointType = this.repository.createIRI(Prefixos.DOCE.label.toString(), "GeographicPoint");
		//relaçoes
		IRI latitude = this.repository.createIRI(Prefixos.DOCE.label, "hasLatitude");
		IRI longitude = this.repository.createIRI(Prefixos.DOCE.label, "hasLongitude");
		
		this.repository.addStatmentCluster(newGeographicPoint, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
		this.repository.addStatmentCluster(newGeographicPoint, RDF.TYPE, geographicPointType);
		this.repository.addStatmentCluster(newGeographicPoint, latitude, this.repository.createLiteral(Float.parseFloat(geographicPoint.getLatitude())));
		this.repository.addStatmentCluster(newGeographicPoint, longitude, this.repository.createLiteral(Float.parseFloat(geographicPoint.getLongitude())));
		this.repository.addStatmentCluster(newGeographicPoint, RDFS.COMMENT, geographicPoint.getComment());
		this.repository.addStatmentCluster(newGeographicPoint, RDFS.LABEL, geographicPoint.getLabel());

		
	}

	
}
