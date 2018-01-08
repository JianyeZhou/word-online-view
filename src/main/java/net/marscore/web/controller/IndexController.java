package net.marscore.web.controller;

import net.marscore.pojo.WordFile;
import net.marscore.service.WordFileService;
import net.marscore.web.common.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Eden
 */
@Controller
public class IndexController {
    @Autowired
    WordFileService wordFileService;
    @Autowired
    WebUtil webUtil;

    @RequestMapping("/")
    public ModelAndView indexPage(){
        ModelAndView modelAndView = new ModelAndView("index");
        List<WordFile> wordFileList = wordFileService.selectList();
        modelAndView.addObject("wordFileList", wordFileList);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public ModelAndView indexPage(Long id){
        wordFileService.delete(id);
        return new ModelAndView("redirect:/");
    }
}
