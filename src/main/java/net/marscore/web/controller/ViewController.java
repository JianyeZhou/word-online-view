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
public class ViewController {
    @Autowired
    WordFileService wordFileService;
    @Autowired
    WebUtil webUtil;

    @RequestMapping("/view")
    public ModelAndView indexPage(Long id){
        ModelAndView modelAndView = new ModelAndView("view");
        WordFile wordFile = null;
        if (id!=null) {
            wordFile = wordFileService.selectOne(id);
        }
        modelAndView.addObject("wordFile", wordFile);
        return modelAndView;
    }
}
