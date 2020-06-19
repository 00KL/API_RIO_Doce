package br.ufes.nemo.integradoce.extrator.cgt;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.eclipse.rdf4j.model.vocabulary.RDF;

import br.ufes.nemo.integradoce.extrator.cdp.Measurement;
import br.ufes.nemo.integradoce.extrator.cgd.Repository;
import br.ufes.nemo.integradoce.extrator.util.Prefixos;

public class MeasurementApl extends AbstractApl {

	public MeasurementApl(Repository repository) {
		super(repository);
	}

	public IRI post(String quemicalElement, String sample, String value, String data, String[] elementAndUnit, IRI geographicPoint) {
		Measurement measurement = new Measurement();
		try {
			
			measurement.setWaterMeasurement("WaterMeasurement" + "_" + quemicalElement + "_" + sample);
			measurement.setQualityKind(elementAndUnit[0]);
			measurement.setValue(value);
			measurement.setUnit(elementAndUnit[1]);
			measurement.setgeographicPoint(geographicPoint);

			SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
			measurement.setData(formatter1.parse(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sendStatement(measurement);

	}

	private  IRI sendStatement(Measurement measurement) {
		//		//Sujeito
		IRI waterMeasurement = this.repository.createIRI(Prefixos.DATABASE.label, measurement.getWaterMeasurement());
//		
//		//Objeto
		IRI unit = this.repository.createIRI(measurement.getUnit());
		IRI measurement_x = this.repository.createIRI(Prefixos.DOCE.label, "Measurement");
		IRI qualityKind = this.repository.createIRI(measurement.getQualityKind());

		// relações
		IRI expressedIn = this.repository.createIRI(Prefixos.DOCE.label, "expressedIn");
		IRI measuredQualityKind = this.repository.createIRI(Prefixos.DOCE.label, "measuredQualityKind");
		IRI hasQualityValue = this.repository.createIRI(Prefixos.GUFO.label, "hasQualityValue");
		IRI hasBeginPointInXSDDateTimeStamp = this.repository.createIRI(Prefixos.DOCE.label, "hasBeginPointInXSDDateTimeStamp");
		IRI hasEndPointInXSDDateTimeStamp = this.repository.createIRI(Prefixos.DOCE.label, "hasEndPointInXSDDateTimeStamp");
		IRI locatedIn = repository.createIRI(Prefixos.DATABASE.label, "locatedIn");//deu ruim?
		
		this.repository.addStatmentCluster(waterMeasurement, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
		this.repository.addStatmentCluster(waterMeasurement, RDF.TYPE, measurement_x);
		this.repository.addStatmentCluster(waterMeasurement, expressedIn, unit);
		this.repository.addStatmentCluster(waterMeasurement, locatedIn, measurement.getgeographicPoint());//deu ruim?
		this.repository.addStatmentCluster(waterMeasurement, measuredQualityKind, qualityKind);
		this.repository.addStatmentCluster(waterMeasurement, hasBeginPointInXSDDateTimeStamp, this.repository.createLiteralDate(measurement.getData()));
		this.repository.addStatmentCluster(waterMeasurement, hasEndPointInXSDDateTimeStamp, this.repository.createLiteralDate(measurement.getData()));
		
		if (!measurement.getValue().contains("<")) {
			this.repository.addStatmentCluster(waterMeasurement, hasQualityValue, this.repository.createLiteral(Double.parseDouble(measurement.getValue())));
		} else {
			this.repository.addStatmentCluster(waterMeasurement, hasQualityValue, this.repository.createLiteral(measurement.getValue()));
		}

		return waterMeasurement;

	}

}
