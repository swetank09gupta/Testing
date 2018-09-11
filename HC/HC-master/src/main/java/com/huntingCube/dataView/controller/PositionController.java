package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.Position;
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
 * Created by dgup27 on 5/22/2017.
 */
@Controller
public class PositionController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(PositionController.class);

    @RequestMapping(value = {"/positionList"}, method = RequestMethod.GET)
    public String positionList(ModelMap model) {

        List<Position> position = positionService.findAllPositions();
        model.addAttribute("position", position);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/positionList";
    }

    @RequestMapping(value = {"/addPosition"}, method = RequestMethod.POST)
    public String savePosition(@Valid Position position, BindingResult result,
                               ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(position.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            positionService.save(position);
        } catch (Exception e) {
            logger.error("Error while saving Position", e);
        }
        return "redirect:/positionList";
    }

    @RequestMapping(value = {"/addPosition"}, method = RequestMethod.GET)
    public String addPosition(ModelMap model) {
        Position position = new Position();
        model.addAttribute("position", position);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addPosition";
    }

    @RequestMapping(value = {"/edit-position-{positionId}"}, method = RequestMethod.GET)
    public String editPosition(@PathVariable int positionId, ModelMap model) {
        Position position = positionService.findById(positionId);
        model.addAttribute("position", position);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addPosition";
    }

    @RequestMapping(value = {"/edit-position-{positionId}"}, method = RequestMethod.POST)
    public String updatePosition(@Valid Position position, BindingResult result,
                                 ModelMap model) {
        if (result.hasErrors()) {
            return "dataView/addPosition";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        position.setAddedBy((String) model.get("userSSOId"));
        positionService.updatePosition(position);
        return "redirect:/positionList";
    }

    @RequestMapping(value = { "/delete-position-{positionId}" }, method = RequestMethod.GET)
    public String deletePosition(@PathVariable int positionId, ModelMap model) {
        positionService.deleteById(positionId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/positionList";
    }
}