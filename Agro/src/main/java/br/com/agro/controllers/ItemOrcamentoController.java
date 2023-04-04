package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.ItemOrcamento;
import br.com.agro.services.ItemOrcamentoService;

@Controller
@RequestMapping("/itemorcamento")
public class ItemOrcamentoController {
    @Autowired
    ItemOrcamentoService itemorcamentoService;

    @RequestMapping(path = "novo")
    public ModelAndView novoItemOrcamento() {
        ModelAndView mv = new ModelAndView("ItemOrcamento/form.html");
        mv.addObject("ItemOrcamento", new ItemOrcamento());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarItemOrcamento(ItemOrcamento ItemOrcamento) {
        ModelAndView mv = new ModelAndView("ItemOrcamento/form.html");
        ItemOrcamento ItemOrcamentoSalvo = itemorcamentoService.salvarItemOrcamento(ItemOrcamento);
        mv.addObject("ItemOrcamento", ItemOrcamentoSalvo);
        mv.addObject("mensagem", "ItemOrcamento salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarItemOrcamento(ItemOrcamento ItemOrcamento, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/ItemOrcamento/listar");
        if (result.hasErrors()) {
            mv.addObject("ItemOrcamento", ItemOrcamento);
            return mv;
        }
        ItemOrcamento ItemOrcamentoSalvo = itemorcamentoService.salvarItemOrcamento(ItemOrcamento);
        if (ItemOrcamentoSalvo != null) {
            attributes.addFlashAttribute("mensagem", "ItemOrcamento salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar ItemOrcamento!");
        }
        mv.addObject("ItemOrcamento", ItemOrcamentoSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarItemOrcamentos() {
        ModelAndView mv = new ModelAndView("ItemOrcamento/lista.html");
        mv.addObject("ItemOrcamentos", itemorcamentoService.findAll());
        mv.addObject("nome", "ItemOrcamentos");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirItemOrcamento(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/ItemOrcamento/listar");
        itemorcamentoService.excluirItemOrcamento(id);
        return mv;
    }
}
