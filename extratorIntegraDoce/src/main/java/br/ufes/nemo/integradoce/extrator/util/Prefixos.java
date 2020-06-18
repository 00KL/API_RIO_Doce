package br.ufes.nemo.integradoce.extrator.util;

public enum Prefixos {
	
	OWL("http://www.w3.org/2002/07/owl#"),
	RDF("http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
	xml("http://www.w3.org/XML/1998/namespace#" ),
	XSD("http://www.w3.org/2001/XMLSchema#" ),
	DOCE("http://purl.org/nemo/doce#" ),
	GUFO("http://purl.org/nemo/gufo#" ),
	RDFS("http://www.w3.org/2000/01/rdf-schema#" ),
	UNIT("http://qudt.org/vocab/unit#" ),
	DATABASE("http://purl.org/nemo/prefixos/doceprefixo#" );
	
	public final String label;
	 
    private Prefixos(String label) {
        this.label = label;
    }

}
