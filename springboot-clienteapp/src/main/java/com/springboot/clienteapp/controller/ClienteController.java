package com.springboot.clienteapp.controller;

import java.util.List;

import com.springboot.clienteapp.models.entity.Ciudad;
import com.springboot.clienteapp.models.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springboot.clienteapp.models.entity.Cliente;
import com.springboot.clienteapp.models.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ICiudadService ciudadService;
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.listarTodso();
		
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoClientes);
		
		return "/views/clientes/listar";
	}
	@GetMapping("/create")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		List<Ciudad> listCiudades = ciudadService.listaCiudades();

		model.addAttribute("titulo", "Formulario:Nuevo Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);

		return "/views/clientes/frmCrear";
	}

	@PostMapping("/save")
	public String guardar(@ModelAttribute Cliente cliente){
		clienteService.guardar(cliente);
		System.out.println("Cliente Guardado con exito");
		return "redirect:/views/clientes/";
	}
	//AÑADIDO DE JAVI
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idCliente, Model model) {
		Cliente cliente = clienteService.buscarPorId(idCliente);

		List<Ciudad> listCiudades = ciudadService.listaCiudades();

		model.addAttribute("titulo", "Formulario: Editar Cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listCiudades);

		return "/views/clientes/frmCrear";
	}
	//AÑADIDO DE JAVI
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idCliente) {
		clienteService.eliminar(idCliente);
		System.out.println("Registro eliminado con exito!");

		return "redirect:/views/clientes/";
	}
}
