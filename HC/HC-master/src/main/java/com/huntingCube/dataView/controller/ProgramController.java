package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.Program;
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
public class ProgramController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(ProgramController.class);

    @RequestMapping(value = {"/programList"}, method = RequestMethod.GET)
    public String programList(ModelMap model) {

        List<Program> program = programService.findAllPrograms();
        model.addAttribute("program", program);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/programList";
    }

    @RequestMapping(value = {"/addProgram"}, method = RequestMethod.POST)
    public String saveProgram(@Valid Program program, BindingResult result,
                                ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(program.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            programService.save(program);
        } catch (Exception e) {
            logger.error("Error while saving Program", e);
        }
        return "redirect:/programList";
    }

    @RequestMapping(value = {"/addProgram"}, method = RequestMethod.GET)
    public String addProgram(ModelMap model) {
        Program program = new Program();
        model.addAttribute("program", program);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addProgram";
    }

    @RequestMapping(value = { "/edit-program-{programId}" }, method = RequestMethod.GET)
    public String editProgram(@PathVariable int programId, ModelMap model) {
        Program program = programService.findById(programId);
        model.addAttribute("program", program);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addProgram";
    }

    @RequestMapping(value = {"/edit-program-{programId}"}, method = RequestMethod.POST)
    public String updateProgram(@Valid Program program, BindingResult result,
                                  ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addProgram";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        program.setAddedBy((String)model.get("userSSOId"));
        programService.updateProgram(program);
        return "redirect:/programList";
    }

    @RequestMapping(value = { "/delete-program-{programId}" }, method = RequestMethod.GET)
    public String deleteProgram(@PathVariable int programId, ModelMap model) {
        programService.deleteById(programId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/programList";
    }
}
