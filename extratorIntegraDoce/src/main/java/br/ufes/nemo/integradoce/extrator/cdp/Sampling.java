package br.ufes.nemo.integradoce.extrator.cdp;

//Especifica a ordem dos dados na string linha
//(0)Site                    (1)Sample Point	(2)Data Source	(3)Date	        (4)Sample Ref	(5)Lab Ref	    (6)Sample Type
// Aguas  Interiores	    Acaiaca - Carmo 01	ALS	             8/9/17 14:24	314020-2017-1	314020-2017-1	Superficial
public class Sampling {
//	###  http://purl.org/nemo/examples/doceexample#WaterSampling314020-2017-1
//		:WaterSampling314020-2017-1 rdf:type owl:NamedIndividual ,
//		                                     doce:Sampling ;
//		                            doce:locatedIn :RCA-01 ;
//		                            gufo:hasBeginPointInXSDDateTimeStamp "2009-08-17T14:24:00-03:00"^^xsd:dateTimeStamp ;
//		                            gufo:hasEndPointInXSDDateTimeStamp "2009-08-17T14:24:00-03:00"^^xsd:dateTimeStamp .
	
	String Sampling;
	String pointLongName;
	java.util.Date date;
	
	
	public String getSampling() {
		return Sampling;
	}
	public void setSampling(String sampling) {
		Sampling = sampling;
	}
	public String getPointLongName() {
		return pointLongName;
	}
	public void setPointLongName(String pointLongName) {
		this.pointLongName = pointLongName;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	} 
	
	

	
	
	
}
