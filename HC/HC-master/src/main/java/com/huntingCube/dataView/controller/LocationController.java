package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.Location;
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
public class LocationController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @RequestMapping(value = {"/locationList"}, method = RequestMethod.GET)
    public String locationList(ModelMap model) {

        List<Location> location = locationService.findAllLocations();
        model.addAttribute("location", location);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/locationList";
    }

    @RequestMapping(value = {"/addLocation"}, method = RequestMethod.POST)
    public String saveLocation(@Valid Location location, BindingResult result,
                                   ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(location.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            locationService.save(location);
        } catch (Exception e) {
            logger.error("Error while saving Location", e);
        }
        return "redirect:/locationList";
    }

    @RequestMapping(value = {"/addLocation"}, method = RequestMethod.GET)
    public String addLocation(ModelMap model) {
        Location location = new Location();
        model.addAttribute("location", location);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addLocation";
    }

    @RequestMapping(value = { "/edit-location-{locationId}" }, method = RequestMethod.GET)
    public String editLocation(@PathVariable int locationId, ModelMap model) {
        Location location = locationService.findById(locationId);
        model.addAttribute("location", location);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addLocation";
    }

    @RequestMapping(value = {"/edit-location-{locationId}"}, method = RequestMethod.POST)
    public String updateLocation(@Valid Location location, BindingResult result,
                                     ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addLocation";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        location.setAddedBy((String)model.get("userSSOId"));
        locationService.updateLocation(location);
        return "redirect:/locationList";
    }

    @RequestMapping(value = { "/delete-location-{locationId}" }, method = RequestMethod.GET)
    public String deleteLocation(@PathVariable int locationId, ModelMap model) {
        locationService.deleteById(locationId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/locationList";
    }
}
