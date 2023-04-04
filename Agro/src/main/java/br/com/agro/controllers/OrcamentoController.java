package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.Orcamento;
import br.com.agro.services.OrcamentoService;

@Controller
@RequestMapping("/orcamento")
public class OrcamentoController {
    @Autowired
    OrcamentoService orcamentoService;

    @RequestMapping(path = "novo")
    public ModelAndView novoOrcamento() {
        ModelAndView mv = new ModelAndView("Orcamento/form.html");
        mv.addObject("Orcamento", new Orcamento());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarOrcamento(Orcamento Orcamento) {
        ModelAndView mv = new ModelAndView("Orcamento/form.html");
        Orcamento OrcamentoSalvo = orcamentoService.salvarOrcamento(Orcamento);
        mv.addObject("Orcamento", OrcamentoSalvo);
        mv.addObject("mensagem", "Orcamento salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarOrcamento(Orcamento Orcamento, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/Orcamento/listar");
        if (result.hasErrors()) {
            mv.addObject("Orcamento", Orcamento);
            return mv;
        }
        Orcamento OrcamentoSalvo = orcamentoService.salvarOrcamento(Orcamento);
        if (OrcamentoSalvo != null) {
            attributes.addFlashAttribute("mensagem", "Orcamento salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar Orcamento!");
        }
        mv.addObject("Orcamento", OrcamentoSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarOrcamentos() {
        ModelAndView mv = new ModelAndView("Orcamento/lista.html");
        mv.addObject("Orcamentos", orcamentoService.findAll());
        mv.addObject("nome", "Orcamentos");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirOrcamento(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/Orcamento/listar");
        orcamentoService.excluirOrcamento(id);
        return mv;
    }
}
