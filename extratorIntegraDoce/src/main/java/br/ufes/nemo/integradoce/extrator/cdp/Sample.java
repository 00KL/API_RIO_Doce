package br.ufes.nemo.integradoce.extrator.cdp;

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
	
	String waterSample;
	String waterSampling;
	
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
