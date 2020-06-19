package br.ufes.nemo.integradoce.extrator.cdp;

import org.eclipse.rdf4j.model.IRI;

public class Measurement {
//	:WaterMeasurementAlkalinity001 rdf:type owl:NamedIndividual ,
//		    doce:Measurement ;
//			doce:expressedIn unit:MilliGM-PER-L ;
//			doce:measuredQualityKind doce:TotalAlkalinityAsCaCO3 ;
//			gufo:hasQualityValue "22.0"^^xsd:double .
	
	private String waterMeasurement;
	private String qualityKind;
	private String value;
	private String unit;
	private java.util.Date data;
	private IRI geographicPoint;
	

	
	public String getWaterMeasurement() {
		return waterMeasurement;
	}
	public void setWaterMeasurement(String waterMeasurement) {
		this.waterMeasurement = waterMeasurement;
	}
	public String getQualityKind() {
		return qualityKind;
	}
	public void setQualityKind(String qualityKind) {
		this.qualityKind = qualityKind;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public IRI getgeographicPoint() {
		return geographicPoint;
	}
	public void setgeographicPoint(IRI geographicPoint) {
		this.geographicPoint = geographicPoint;
	}
	
	
}
