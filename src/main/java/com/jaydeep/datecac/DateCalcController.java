package com.jaydeep.datecac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Controller
public class DateCalcController {
    Logger logger = LoggerFactory.getLogger(DateCalcController.class);

    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("formInput", new FormInput());
        return "date-calc";
    }

    @PostMapping("/")
    public String calcDate(@ModelAttribute @Valid FormInput formInput, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            logger.info("Validation error.");
            return "date-calc";
        }
        logger.info(formInput.getFromDate().toString());
        logger.info(formInput.getToDate().toString());

        long diff = ChronoUnit.DAYS.between(formInput.getFromDate(), formInput.getToDate());

        redirectAttributes.addFlashAttribute("message",
                String.format(
                        "%d days between %s and %s",
                        diff,
                        formInput.getFromDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")),
                        formInput.getToDate().format(DateTimeFormatter.ofPattern("dd/MM/yy"))));

        return "redirect:/";
    }
}


