package br.ufes.nemo.integradoce.extrator.cgt;


import java.text.NumberFormat;
import java.text.ParseException;

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

	public IRI post(String quemicalElement, String sampleString, String value, String data, String[] elementAndUnit, IRI geographicPoint, IRI sampleIRI) {
		Measurement measurement = new Measurement();
		
		measurement.setWaterMeasurement("WaterMeasurement" + "_" + quemicalElement + "_" + sampleString);
		measurement.setQualityKind(elementAndUnit[0]);
		measurement.setValue(value);
		measurement.setUnit(elementAndUnit[1]);
		measurement.setMeasuredObject(sampleIRI);

		return sendStatement(measurement);

	}

	private  IRI sendStatement(Measurement measurement) {
		//Sujeito
		IRI waterMeasurement = this.repository.createIRI(Prefixos.DATABASE.label, measurement.getWaterMeasurement());
		
		//Objeto
		IRI unit = this.repository.createIRI(measurement.getUnit());
		IRI measurement_x = this.repository.createIRI(Prefixos.DOCE.label, "Measurement");
		IRI qualityKind = this.repository.createIRI(measurement.getQualityKind());

		// relações
		IRI expressedIn = this.repository.createIRI(Prefixos.DOCE.label, "expressedIn");
		IRI measuredQualityKind = this.repository.createIRI(Prefixos.DOCE.label, "measuredQualityKind");
		IRI hasQualityValue = this.repository.createIRI(Prefixos.GUFO.label, "hasQualityValue");
		IRI measured = repository.createIRI(Prefixos.DOCE.label, "measured");
		
		this.repository.addStatmentCluster(waterMeasurement, RDF.TYPE, OWL.NAMEDINDIVIDUAL);
		this.repository.addStatmentCluster(waterMeasurement, RDF.TYPE, measurement_x);
		this.repository.addStatmentCluster(waterMeasurement, expressedIn, unit);
		this.repository.addStatmentCluster(waterMeasurement, measuredQualityKind, qualityKind);
		this.repository.addStatmentCluster(waterMeasurement, measured, measurement.getMeasuredObject());
		
		
		
		if (!measurement.getValue().contains("<")) {
			NumberFormat nf = NumberFormat.getNumberInstance();
			nf.setMaximumFractionDigits(4);
	 
			float value;
	 
			try {
				value = nf.parse(measurement.getValue()).floatValue();
				

				this.repository.addStatmentCluster(waterMeasurement, hasQualityValue, this.repository.createLiteral(value));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			this.repository.addStatmentCluster(waterMeasurement, hasQualityValue, this.repository.createLiteral(measurement.getValue()));
		}
		
		
		return waterMeasurement;

	}

}
