package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.FileTemplate;
import pe.edu.upc.service.ITemplateService;

@Controller
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private ITemplateService tS;
	private int valid = 0;

	@GetMapping("/list")
	public String listTemplates(Model model) {
		try {
			ingresar_datos();
			model.addAttribute("template", new FileTemplate());
			model.addAttribute("listTemplate", tS.list());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "template/listTemplate";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute FileTemplate temp) throws ParseException {

		List<FileTemplate> listaTemplates;
		listaTemplates = tS.findName(temp.getTemplateName());

		if (listaTemplates.isEmpty()) {

			model.put("mensaje", "No se encontró");
		}

		model.put("listTemplate", listaTemplates);
		return "template/findTemplate";

	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		FileTemplate temp = tS.listId(id);
		if (temp == null) {
			flash.addFlashAttribute("error", "El Archivo no existe en la base de datos");
			return "template/listTemplate"; // vistaaaaa
		}
		model.put("template", temp);
		model.put("titulo", "Requisitos para la redaccion de : " + temp.getTemplateName());

		return "template/templatebody"; // vista
	}

	private void ingresar_datos() {

		if (valid == 0) {

			FileTemplate temp = new FileTemplate(null, "Contrato de compraventa internacional",
					"Este tipo de contrato es un acuerdo entre operadores económicos que residen en países distintos y por el cual una parte se compromete a entregar a la otra una mercadería en el lugar convenido, en el plazo determinado y al precio pactado. Su origen fue en el Con­greso de Viena, el 11 de abril de 1980, sobre la compraventa internacional de mercaderías.");
			FileTemplate temp2 = new FileTemplate(null, "Contratos de intermediación o colaboración","Los principales tipos de este modelo de contrato son el contrato de agencia, el de distribución y el de franquicia. Un aspecto imprescindible para el exportador es la correcta elección de los canales para establecer sus productos en el mercado internacional. Ya que, es importante elegir las vías de entrada más competitivas.");
			FileTemplate temp3 = new FileTemplate(null, "Contrato de agencia", "Este tipo de contrato según la normativa española es la alianza por la cual una persona física o jurídica está obligada frente a otra, todo ello a cambio de una remuneración, a promover actos u operaciones de comercio por cuenta ajena o a promoverlos y concluirlos por cuenta y en nombre ajenos como intermediario independiente. Y todo sin asumir el riesgo de las operaciones realizadas.");
			FileTemplate temp4 = new FileTemplate(null, "Contrato de distribución","Este contrato es aquel, el cual una empresa se compromete a vender sus productos a otra con carácter exclusivo o en un determinado territorio y con fines de reventa (en el mismo estado en que fueron comprados).En estos contratos la exclusividad territorial se suele conceder a cambio de un compromiso por parte del distribuidor para solicitar un volumen mínimo de pedidos o de ventas.");
			FileTemplate temp5 = new FileTemplate(null, "Contrato de Franquicia","Es cuando una parte cede a otra el derecho a la explotación de una franquicia para comercializar determinados productos o servicios a cambio de una con­traprestación económica. Es una forma de colaboración comercial entre empresas, tiene como finalidad fijar una red de distribución con identidad común y mediante inversiones limitadas.");
			FileTemplate temp6 = new FileTemplate(null, "El contrato de transferencia de tecnología","Es un contrato mediante el cual una persona física o jurídica proporciona a otra un acceso a la tecnología o know-how (transferencia tecnológica) a cambio de una remuneración.La transferencia de tecnología es una vía de acceso a los mercados exteriores con la que cuenta el exportador, bien cuando existan barreras que dificulten la importación de sus productos, aranceles, homologaciones, etc., bien cuando no quieran asumir los elevados costes y riesgos que supone una implantación productiva en el exterior.");
			FileTemplate temp7 = new FileTemplate(null, "Contrato Leasing","Este contrato tiene por objeto exclusivo la cesión de uso de bienes inmuebles para el desarrollo de una actividad económica a cambio de una contraprestación consistente en el abono periódico de cuotas. El leasing suele tener un propósito financiero y una serie de ventajas fiscales. En su modalidad de exportación, es una forma de financiación al importador extranjero.");
			FileTemplate temp8 = new FileTemplate(null, "Contrato Factoring","Con origen en el comercio de ultramar de Inglaterra, es el acuerdo por el cual una empresa vendedora transfiere los créditos derivados de su actividad comercial a una sociedad especializada que se encarga de gestionar su cobro a cambio de una remuneración.");
			FileTemplate temp9 = new FileTemplate(null, "Contrato de joint-venture","Se refiere a una forma de cooperación internacional basada en la asociación de empresas de distintos países. Una empresa exportadora puede fabricar o en­samblar sus productos en un país extranjero compartiendo la gestión empresarial con sus socios en el país de constitución.");
			tS.insert(temp);
			tS.insert(temp2);
			tS.insert(temp3);
			tS.insert(temp4);
			tS.insert(temp5);
			tS.insert(temp6);
			tS.insert(temp7);
			tS.insert(temp8);
			tS.insert(temp9);
			valid++;
		}

	}
}
