package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.Stream;
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
public class StreamController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(StreamController.class);

    @RequestMapping(value = {"/streamList"}, method = RequestMethod.GET)
    public String streamList(ModelMap model) {

        List<Stream> stream = streamService.findAllStreams();
        model.addAttribute("stream", stream);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/streamList";
    }

    @RequestMapping(value = {"/addStream"}, method = RequestMethod.POST)
    public String saveStream(@Valid Stream stream, BindingResult result,
                                   ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(stream.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            streamService.save(stream);
        } catch (Exception e) {
            logger.error("Error while saving Stream", e);
        }
        return "redirect:/streamList";
    }

    @RequestMapping(value = {"/addStream"}, method = RequestMethod.GET)
    public String addStream(ModelMap model) {
        Stream stream = new Stream();
        model.addAttribute("stream", stream);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addStream";
    }

    @RequestMapping(value = { "/edit-stream-{streamId}" }, method = RequestMethod.GET)
    public String editStream(@PathVariable int streamId, ModelMap model) {
        Stream stream = streamService.findById(streamId);
        model.addAttribute("stream", stream);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addStream";
    }

    @RequestMapping(value = {"/edit-stream-{streamId}"}, method = RequestMethod.POST)
    public String updateStream(@Valid Stream stream, BindingResult result,
                                     ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addStream";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        stream.setAddedBy((String)model.get("userSSOId"));
        streamService.updateStream(stream);
        return "redirect:/streamList";
    }

    @RequestMapping(value = { "/delete-stream-{streamId}" }, method = RequestMethod.GET)
    public String deleteStream(@PathVariable int streamId, ModelMap model) {
        streamService.deleteById(streamId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/streamList";
    }
}
