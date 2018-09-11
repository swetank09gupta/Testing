package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.Institute;
import com.huntingCube.dataView.model.Institute;
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
public class InstituteController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(InstituteController.class);

    @RequestMapping(value = {"/instituteList"}, method = RequestMethod.GET)
    public String instituteList(ModelMap model) {

        List<Institute> institute = instituteService.findAllInstitutes();
        model.addAttribute("institute", institute);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/instituteList";
    }

    @RequestMapping(value = {"/addInstitute"}, method = RequestMethod.POST)
    public String saveInstitute(@Valid Institute institute, BindingResult result,
                                   ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(institute.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            instituteService.save(institute);
        } catch (Exception e) {
            logger.error("Error while saving Institute", e);
        }
        return "redirect:/instituteList";
    }

    @RequestMapping(value = {"/addInstitute"}, method = RequestMethod.GET)
    public String addInstitute(ModelMap model) {
        Institute institute = new Institute();
        logger.info("institute>>>>>>>>>>>"+institute);
        model.addAttribute("institute", institute);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addInstitute";
    }

    @RequestMapping(value = { "/edit-institute-{instituteId}" }, method = RequestMethod.GET)
    public String editInstitute(@PathVariable int instituteId, ModelMap model) {
        Institute institute = instituteService.findById(instituteId);
        model.addAttribute("institute", institute);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addInstitute";
    }

    @RequestMapping(value = {"/edit-institute-{instituteId}"}, method = RequestMethod.POST)
    public String updateInstitute(@Valid Institute institute, BindingResult result,
                                     ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addInstitute";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        institute.setAddedBy((String)model.get("userSSOId"));
        instituteService.updateInstitute(institute);
        return "redirect:/instituteList";
    }

    @RequestMapping(value = { "/delete-institute-{instituteId}" }, method = RequestMethod.GET)
    public String deleteInstitute(@PathVariable int instituteId, ModelMap model) {
        instituteService.deleteById(instituteId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/instituteList";
    }
}
