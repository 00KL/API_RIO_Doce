package br.ufes.nemo.integradoce.extrator.cdp;

//(0)Site	(1)Sample Point Long Name	(2)Sample Point Short Name	(3)Lat	(4)Long	
//(5)X	(6)Y	(7)Z	(8)Projection	(9)Datum	(10)Sample Point Category	(11)Comment
public class GeographicPoint {

	private String geograficPoint; //Short Name
	private String label; //Long Name
	private String comment;
	private String latitude;
	private String longitude; 
	
	
	
	public String getGeograficPoint() {
		return geograficPoint;
	}

	public void setGeograficPoint(String geograficPoint) {
		this.geograficPoint = geograficPoint;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	
	
	
	
	
	
	
}
