package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Product;
import pe.edu.upc.service.IBrandService;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.IProductService;
import pe.edu.upc.service.ISupplierService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService pService;

	@Autowired
	private ICategoryService cService;

	@Autowired
	private IBrandService bService;

	@Autowired
	private ISupplierService sService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	// METODO PARA VER EL DETALLE EMPLEADO
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") long id, Map<String, Object> model, RedirectAttributes flash) {

		Product product = pService.listarId(id);

		if (product == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "product/listProducts"; // vistaaaaa
		}

		model.put("product", product);
		model.put("titulo", "Detalle de producto: " + product.getProductName());

		return "product/ver"; // vista
	}

	@RequestMapping("/")
	public String irPet(Map<String, Object> model) {
		model.put("listaProductos", pService.listar());
		return "product/listProducts"; // vistaa
	}

	@RequestMapping("/new")
	public String irRegistrar(Model model) {
		model.addAttribute("listaCategorias", cService.list());
		model.addAttribute("listaMarcas", bService.list());
		model.addAttribute("listaProveedores", sService.list());
		model.addAttribute("product", new Product());
		return "product/product"; // vista
	}

	@RequestMapping("/save")
	public String registrar(@ModelAttribute @Valid Product objPro, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCategorias", cService.list());
			model.addAttribute("listaMarcas", bService.list());
			model.addAttribute("listaProveedores", sService.list());
			return "product/product";
		} else {
			if (!foto.isEmpty()) {

				if (objPro.getIdProducto() > 0 && objPro.getFoto() != null && objPro.getFoto().length() > 0) {

					uploadFileService.delete(objPro.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				objPro.setFoto(uniqueFilename);
			}
			boolean flag = pService.insert(objPro);
			if (flag) {
				return "redirect:/products/list";
			} else {
				model.addAttribute("mensaje", "OcurriÃ³ un error");
				return "redirect:/products/new";
			}
		}
	}

	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Product objPro, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/products/listar";
		} else {
			boolean flag = pService.modificar(objPro);

			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/products/list";

			} else {
				objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
				return "redirect:/products/list";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Product objPro = pService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/products/listar";
		} else {
			model.addAttribute("listaCategorias", cService.list());
			model.addAttribute("listaMarcas", bService.list());
			model.addAttribute("listaProveedores", sService.list());
			model.addAttribute("product", objPro);
			return "product/product";
		}
	}

	@RequestMapping("/eliminar/{id}")
	public String eliminar(Map<String, Object> model, @PathVariable(value = "id") Integer id,
			RedirectAttributes flash) {
		try {
			Product pro = pService.listarId(id);
			if (id != null && id > 0) {
				pService.eliminar(id);
				if (uploadFileService.delete(pro.getFoto())) {
					flash.addFlashAttribute("info", "Foto " + pro.getFoto() + " eliminada con exito!");
				}
				model.put("listaProductos", pService.listar());
			}
		} catch (Exception e) {
			model.put("mensaje", "No se pudo eliminar");
			model.put("listaProductos", pService.listar());

		}
		return "product/listProducts"; // vistas
	}

	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaProductos", pService.listar());
		return "product/listProducts";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Product pro) {
		pService.listarId(pro.getIdProducto());
		return "product/listProducts";

	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Product producto) throws ParseException {

		List<Product> listaProductos;
		producto.setProductName(producto.getProductName());
		listaProductos = pService.findName(producto.getProductName());

		if (listaProductos.isEmpty())

		{

			model.put("mensaje", "No se encontrÃ³");
		}

		model.put("listaProductos", listaProductos);
		return "redirect:/products/irBuscar"; // vista
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {

		model.addAttribute("producto", new Product());
		return "product/buscarProducto";

	}

	@RequestMapping("/reporte1")
	public String productosXimp(Map<String, Object> model) {
		model.put("listProdxImp", pService.prodXimp());
		return "reports/productosImportados";
	}

}
