package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.PassingYear;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by guptado on 22/05/2017.
 */
@Controller
public class PassingYearController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(PassingYearController.class);

    @RequestMapping(value = {"/passingYearList"}, method = RequestMethod.GET)
    public String passingYearList(ModelMap model) {

        List<PassingYear> passingYear = passingYearService.findAllPassingYears();
        model.addAttribute("passingYear", passingYear);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/passingYearList";
    }

    @RequestMapping(value = {"/addPassingYear"}, method = RequestMethod.POST)
    public String savePassingYear(@Valid PassingYear passingYear, BindingResult result,
                                ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(passingYear.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            passingYearService.save(passingYear);
        } catch (Exception e) {
            logger.error("Error while saving PassingYear", e);
        }
        return "redirect:/passingYearList";
    }

    @RequestMapping(value = {"/addPassingYear"}, method = RequestMethod.GET)
    public String addPassingYear(ModelMap model) {
        PassingYear passingYear = new PassingYear();
        model.addAttribute("passingYear", passingYear);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addPassingYear";
    }

    @RequestMapping(value = { "/edit-passingYear-{passingYearId}" }, method = RequestMethod.GET)
    public String editPassingYear(@PathVariable int passingYearId, ModelMap model) {
        PassingYear passingYear = passingYearService.findById(passingYearId);
        model.addAttribute("passingYear", passingYear);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addPassingYear";
    }

    @RequestMapping(value = {"/edit-passingYear-{passingYearId}"}, method = RequestMethod.POST)
    public String updatePassingYear(@Valid PassingYear passingYear, BindingResult result,
                                  ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addPassingYear";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        passingYear.setAddedBy((String)model.get("userSSOId"));
        passingYearService.updatePassingYear(passingYear);
        return "redirect:/passingYearList";
    }

    @RequestMapping(value = { "/delete-passingYear-{passingYearId}" }, method = RequestMethod.GET)
    public String deletePassingYear(@PathVariable int passingYearId, ModelMap model) {
        passingYearService.deleteById(passingYearId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/passingYearList";
    }
}
