package br.ufes.nemo.integradoce.extrator.cdp;

import org.eclipse.rdf4j.model.IRI;

//Especifica a ordem dos dados na string linha
//(0)Site                    (1)Sample Point	(2)Data Source	(3)Date	        (4)Sample Ref	(5)Lab Ref	    (6)Sample Type
// Aguas  Interiores	    Acaiaca - Carmo 01	ALS	             8/9/17 14:24	314020-2017-1	314020-2017-1	Superficial
public class Sampling {

	private String Sampling;
	private IRI geographicPoint;
	private java.util.Date date;
	
	
	public String getSampling() {
		return Sampling;
	}
	public void setSampling(String sampling) {
		Sampling = sampling;
	}
	public IRI getgeographicPoint() {
		return geographicPoint;
	}
	public void setgeographicPoint(IRI geographicPoint) {
		this.geographicPoint = geographicPoint;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	} 
	
	

	
	
	
}
