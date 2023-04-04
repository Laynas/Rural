package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.Cliente;
import br.com.agro.services.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @RequestMapping(path = "novo")
    public ModelAndView novoCliente() {
        ModelAndView mv = new ModelAndView("cliente/form.html");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarCliente(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/form.html");
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);
        mv.addObject("cliente", clienteSalvo);
        mv.addObject("mensagem", "Cliente salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarCliente(Cliente cliente, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
        if (result.hasErrors()) {
            mv.addObject("cliente", cliente);
            return mv;
        }
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);
        if (clienteSalvo != null) {
            attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar cliente!");
        }
        mv.addObject("cliente", clienteSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarClientes() {
        ModelAndView mv = new ModelAndView("cliente/lista.html");
        mv.addObject("clientes", clienteService.findAll());
        mv.addObject("nome", "Clientes");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirCliente(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
        clienteService.excluirCliente(id);
        return mv;
    }
}
