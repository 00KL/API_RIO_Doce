package br.ufes.nemo.integradoce.api.apiintegradoce.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.nemo.integradoce.extrator.cih.Extrator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "RioDoce")
public class RestfulControle {
	
	@ApiOperation(value = "Query in database")
	@RequestMapping(value = "/api/query", method = RequestMethod.POST)
	public String query (String query) {
		Extrator extrator = new Extrator();
		String result = null;
		try {
			result =  extrator.consulta(query);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
