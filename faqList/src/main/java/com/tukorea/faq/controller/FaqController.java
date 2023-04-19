package com.tukorea.faq.controller;

import com.tukorea.faq.dto.FaqListDto;
import com.tukorea.faq.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class FaqController {

    private FaqService service;

    public FaqController(FaqService service) {
        this.service = service;
    }
    @GetMapping(value = {"/", "/faq/list"})
    public String faqlist(FaqListDto faqListDto, Model model) {
        Map<String, Object> resultMap = service.getFaqList(faqListDto);
        model.addAllAttributes(resultMap);

        return "faq/faqList";
    }
}
