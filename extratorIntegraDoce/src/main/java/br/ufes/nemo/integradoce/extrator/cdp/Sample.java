package br.ufes.nemo.integradoce.extrator.cdp;

//(0)Site                    (1)Sample Point	(2)Data Source	(3)Date	        (4)Sample Ref	(5)Lab Ref	    (6)Sample Type
//Aguas  Interiores	         Acaiaca-Carmo01	ALS	             8/9/17 14:24	314020-2017-1	314020-2017-1	Superficial
public class Sample {
//	:WaterSample314020-2017-1 rdf:type owl:NamedIndividual ,
//    doce:SurfaceWaterSample ;
//	  doce:represents doce:RioDoce ;
//	  doce:wasMeasuredIn :WaterMeasurementAlkalinity001 ; //usar "001" após o nome da medida significa que eu tenho q por um 
														  //nome único em cada identificador de medida de cada elemento?
														  //Nesse caso vale mais a pena so usar o identificador da medida. 
														  //a menos que aquela medição fosse ser usada em outras amostragens, 
														  // o que n faz sentido pq a mediçao é inerente a amostra de onde 
														  // foi medida.
//	  gufo:wasCreatedIn :WaterSampling314020-2017-1 .
	
	private String waterSample;
	private String waterSampling;
	
	public String getWaterSample() {
		return waterSample;
	}
	public void setWaterSample(String waterSample) {
		this.waterSample = waterSample;
	}
	public String getWaterSampling() {
		return waterSampling;
	}
	public void setWaterSampling(String waterSampling) {
		this.waterSampling = waterSampling;
	}
	
	
	
	
	
}
