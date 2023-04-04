package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.Estoque;
import br.com.agro.services.EstoqueService;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueService estoqueService;

    @RequestMapping(path = "novo")
    public ModelAndView novoEstoque() {
        ModelAndView mv = new ModelAndView("Estoque/form.html");
        mv.addObject("Estoque", new Estoque());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarEstoque(Estoque Estoque) {
        ModelAndView mv = new ModelAndView("Estoque/form.html");
        Estoque EstoqueSalvo = estoqueService.salvarEstoque(Estoque);
        mv.addObject("Estoque", EstoqueSalvo);
        mv.addObject("mensagem", "Estoque salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarEstoque(Estoque Estoque, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/Estoque/listar");
        if (result.hasErrors()) {
            mv.addObject("Estoque", Estoque);
            return mv;
        }
        Estoque EstoqueSalvo = estoqueService.salvarEstoque(Estoque);
        if (EstoqueSalvo != null) {
            attributes.addFlashAttribute("mensagem", "Estoque salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar Estoque!");
        }
        mv.addObject("Estoque", EstoqueSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarEstoques() {
        ModelAndView mv = new ModelAndView("Estoque/lista.html");
        mv.addObject("Estoques", estoqueService.findAll());
        mv.addObject("nome", "Estoques");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirEstoque(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/Estoque/listar");
        estoqueService.excluirEstoque(id);
        return mv;
    }
}
