package br.ufes.nemo.integradoce.api.apiintegradoce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.ufes.nemo.integradoce.api.apiintegradoce.dto.RioDoceDTO;
import br.ufes.nemo.integradoce.extrator.cih.Extrator;

@Controller
public class WebController {

	@GetMapping("/")
	public String defaultForm(Model model) {
		model.addAttribute("riodoce", new RioDoceDTO());
		return "riodoce";
	}

	@GetMapping("/riodoce")
	public String riodoceForm(Model model) {
		model.addAttribute("riodoce", new RioDoceDTO());
		return "riodoce";
	}

	@PostMapping("/riodoce")
	public String riodoceSubmit(@ModelAttribute RioDoceDTO riodoce, Model model) {

		System.out.println(riodoce.getResposta());

		Extrator extrator = new Extrator();
		String resposta;
		
		try {
			resposta = extrator.consulta(riodoce.getResposta());
			riodoce.setResposta(resposta);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("riodoce", riodoce);

		return "resultRiodoce";
	}

}
