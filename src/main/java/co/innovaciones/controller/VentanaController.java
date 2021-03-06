package co.innovaciones.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import co.innovaciones.model.Ventana;
import co.innovaciones.service.VentanaService;

@Controller
@RequestMapping("/ventanas")
public class VentanaController {

	private static final String MSG_SUCESS_INSERT = "Ventana inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Ventana successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted Ventana successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private VentanaService ventanaService;

	@GetMapping
	public String index(Model model) {
		List<Ventana> all = ventanaService.findAll();
		model.addAttribute("listVentana", all);
		return "ventana/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Optional<Ventana> ventana = ventanaService.findById(id);
			
			if (ventana.isPresent())
				model.addAttribute("ventana", ventana.get());
		}
		return "ventana/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Ventana entity) {
		model.addAttribute("ventana", entity);
		return "ventana/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Ventana entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Ventana ventana = null;
		try {
			ventana = ventanaService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/ventanas/" + ventana.getId();
	}
	
	@GetMapping("/edit/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Optional<Ventana> entity = ventanaService.findById(id);
				
				if (entity.isPresent())
					model.addAttribute("ventana", entity.get());
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "ventana/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Ventana entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Ventana ventana = null;
		try {
			ventana = ventanaService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/ventanas/" + ventana.getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Optional<Ventana> entity = ventanaService.findById(id);
				
				if (entity.isPresent()) {
					ventanaService.delete(entity.get());
					redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
				} else {
					redirectAttributes.addFlashAttribute("error", MSG_ERROR);
				}
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/ventanas";
	}

}
