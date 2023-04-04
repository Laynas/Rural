package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.Telefone;
import br.com.agro.services.TelefoneService;

@Controller
@RequestMapping("/telefone")
public class TelefoneController {
    @Autowired
    TelefoneService telefoneService;

    @RequestMapping(path = "novo")
    public ModelAndView novoTelefone() {
        ModelAndView mv = new ModelAndView("Telefone/form.html");
        mv.addObject("Telefone", new Telefone());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarTelefone(Telefone Telefone) {
        ModelAndView mv = new ModelAndView("Telefone/form.html");
        Telefone TelefoneSalvo = telefoneService.salvarTelefone(Telefone);
        mv.addObject("Telefone", TelefoneSalvo);
        mv.addObject("mensagem", "Telefone salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarTelefone(Telefone Telefone, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/Telefone/listar");
        if (result.hasErrors()) {
            mv.addObject("Telefone", Telefone);
            return mv;
        }
        Telefone TelefoneSalvo = telefoneService.salvarTelefone(Telefone);
        if (TelefoneSalvo != null) {
            attributes.addFlashAttribute("mensagem", "Telefone salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar Telefone!");
        }
        mv.addObject("Telefone", TelefoneSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarTelefones() {
        ModelAndView mv = new ModelAndView("Telefone/lista.html");
        mv.addObject("Telefones", telefoneService.findAll());
        mv.addObject("nome", "Telefones");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirTelefone(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/Telefone/listar");
        telefoneService.excluirTelefone(id);
        return mv;
    }
}
