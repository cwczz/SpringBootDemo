package com.cwc.Controller;

import com.cwc.Service.HeroService;
import com.cwc.entity.Hero;
import org.springframework.beans.PropertyValuesEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private HeroService heroService;
    @GetMapping("/addhtml")
    public String  addhtml(){
        return "add";
    }
    @GetMapping("/main")
    public String main(Model model){
        List<Hero> heroes = heroService.selectAll();
        model.addAttribute("heroList",heroes);
        return "main";
    }
    @PostMapping("/addandUpdateHero")
    public String addHero(Hero hero){
       heroService.addhero(hero);
        return "redirect:/main";
    }
    @PutMapping("/addandUpdateHero")
    public String update(Hero hero){
        heroService.updatehero(hero);
        return "redirect:/main";
    }
    @DeleteMapping("/deletehero/{id}")
    public String delete(@PathVariable("id") Integer id){
        heroService.deletehero(id);
        return "redirect:/main";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Hero hero = heroService.OneHero(id);
        model.addAttribute("hero",hero);
        return "add";
    }

    /*
    * 日期类型转换
    * registerCustomEditor:注册编辑器
    * PropertyEditorSupport:编辑器支持
    * */
    @InitBinder
    public void InitBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Date.class,new PropertyEditorSupport(){
            public void setAsText(String value){
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e) {
                    e.printStackTrace();
                    setValue(null);
                }
            }
            public String getAsText(){
                return  new SimpleDateFormat("yyyy-MM-dd").format((Date)getValue());
            }
        });
    }

}
