package com.br.ufc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.service.PratoService;

@Controller
public class IndexController {

    @Autowired
    private PratoService pratoService;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("Index");

        mv.addObject("listPratos", pratoService.listarTodos());

        return mv;
    }

    @RequestMapping("/logar")
    public ModelAndView formLogin(){

        ModelAndView mv = new ModelAndView("Login");
        return mv;

    }

}