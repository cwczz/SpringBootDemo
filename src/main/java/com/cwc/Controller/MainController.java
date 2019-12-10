package com.cwc.Controller;

import com.cwc.Service.HeroService;
import com.cwc.entity.Hero;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.PropertyValuesEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Api(value="用户controller",tags={"用户操作接口"})
@Controller
public class MainController {

    @Autowired
    private HeroService heroService;

    @ApiIgnore()
    @GetMapping("/addhtml")
    public String  addhtml(){
        return "add";
    }

    @ApiIgnore()
    @GetMapping("/main")
    public String main(Model model){
        List<Hero> heroes = heroService.selectAll();
        model.addAttribute("heroList",heroes);
        return "main";
    }

    @ApiIgnore()
    @PostMapping("/addandUpdateHero")
    public String addHero(Hero hero){
       heroService.addhero(hero);
        return "redirect:/main";
    }

    @ApiIgnore()
    @PutMapping("/addandUpdateHero")
    public String update(Hero hero){
        heroService.updatehero(hero);
        return "redirect:/main";
    }

    @ApiIgnore()
    @DeleteMapping("/deletehero/{id}")
    public String delete(@PathVariable("id") Integer id){
        heroService.deletehero(id);
        return "redirect:/main";
    }


    @ApiIgnore()
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Hero hero = heroService.OneHero(id);
        model.addAttribute("hero",hero);
        return "add";
    }

    @ApiOperation(value="获取一条用户信息",notes="请求类型GET",httpMethod="GET")
    @ApiImplicitParams({@ApiImplicitParam(name="id",value="用户id",dataType="Long", paramType = "path")})
    @GetMapping("/test/{id}")
    @ResponseBody
    public Hero test(@PathVariable("id") Integer id,Model model){
        Hero hero = heroService.OneHero(id);
        return hero;
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
