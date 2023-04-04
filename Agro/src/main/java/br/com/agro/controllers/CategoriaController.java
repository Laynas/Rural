package br.com.agro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agro.entities.Categoria;
import br.com.agro.services.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @RequestMapping(path = "novo")
    public ModelAndView novoCategoria() {
        ModelAndView mv = new ModelAndView("Categoria/form.html");
        mv.addObject("Categoria", new Categoria());
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarCategoria(Categoria Categoria) {
        ModelAndView mv = new ModelAndView("Categoria/form.html");
        Categoria CategoriaSalvo = categoriaService.salvarCategoria(Categoria);
        mv.addObject("Categoria", CategoriaSalvo);
        mv.addObject("mensagem", "Categoria salvo com sucesso!");
        return mv;
    }


    @RequestMapping(method = RequestMethod.POST, path = "salvo")
    public ModelAndView salvarCategoria(Categoria Categoria, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/Categoria/listar");
        if (result.hasErrors()) {
            mv.addObject("Categoria", Categoria);
            return mv;
        }
        Categoria CategoriaSalvo = categoriaService.salvarCategoria(Categoria);
        if (CategoriaSalvo != null) {
            attributes.addFlashAttribute("mensagem", "Categoria salvo com sucesso!");
        } else {
            attributes.addFlashAttribute("mensagem", "Erro ao salvar Categoria!");
        }
        mv.addObject("Categoria", CategoriaSalvo);
        return mv; 
    }

    @RequestMapping("/listar")
    public ModelAndView listarCategorias() {
        ModelAndView mv = new ModelAndView("Categoria/lista.html");
        mv.addObject("Categorias", categoriaService.findAll());
        mv.addObject("nome", "Categorias");
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirCategoria(Long id) {
        ModelAndView mv = new ModelAndView("redirect:/Categoria/listar");
        categoriaService.excluirCategoria(id);
        return mv;
    }
}
