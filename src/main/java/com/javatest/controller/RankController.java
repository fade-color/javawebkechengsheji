package com.javatest.controller;

import com.javatest.domain.Record;
import com.javatest.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RankController {

    @Autowired
    RecordService recordService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/rankList")
    public String rankList(Model model) {
        List<Record> records = recordService.selectAllRank();
        model.addAttribute("rankList",records);
        return "/rank";
    }

    @RequestMapping("/statusList")
    public String status(Model model) {
        List<Record> status = recordService.selectAllStatus();
        model.addAttribute("StatusList",status);
        return "status";
    }

    @RequestMapping("/mySubmitList")
    public String mySumbit(Model model) {
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("username");
        if (userId==null)
            return "redirect:/index.jsp";
        List<Record> mySumbit = recordService.selectByUserId(userId);
        model.addAttribute("MySubmitList",mySumbit);
        return "/mySubmit";
    }

}
