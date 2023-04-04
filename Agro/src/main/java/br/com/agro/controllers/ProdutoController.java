package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.Produto;
import br.com.agro.services.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @RequestMapping(path = "novo")
    public ModelAndView novoProduto() {
        ModelAndView mv = new ModelAndView("Produto/form.html");
        mv.addObject("Produto", new Produto());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarProduto(Produto Produto) {
        ModelAndView mv = new ModelAndView("Produto/form.html");
        Produto ProdutoSalvo = produtoService.salvarProduto(Produto);
        mv.addObject("Produto", ProdutoSalvo);
        mv.addObject("mensagem", "Produto salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarProduto(Produto Produto, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/Produto/listar");
        if (result.hasErrors()) {
            mv.addObject("Produto", Produto);
            return mv;
        }
        Produto ProdutoSalvo = produtoService.salvarProduto(Produto);
        if (ProdutoSalvo != null) {
            attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar Produto!");
        }
        mv.addObject("Produto", ProdutoSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarProdutos() {
        ModelAndView mv = new ModelAndView("Produto/lista.html");
        mv.addObject("Produtos", produtoService.findAll());
        mv.addObject("nome", "Produtos");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirProduto(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/Produto/listar");
        produtoService.excluirProduto(id);
        return mv;
    }
}
