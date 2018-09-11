package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.ClientStatus;
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
public class ClientStatusController extends BaseController {

    static final Logger logger = LoggerFactory.getLogger(ClientStatusController.class);

    @RequestMapping(value = {"/clientStatusList"}, method = RequestMethod.GET)
    public String clientStatusList(ModelMap model) {

        List<ClientStatus> clientStatus = clientStatusService.findAllStatus();
        model.addAttribute("clientStatus", clientStatus);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/clientStatusList";
    }

    @RequestMapping(value = {"/addClientStatus"}, method = RequestMethod.POST)
    public String saveClientStatus(@Valid ClientStatus clientStatus, BindingResult result,
                                   ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(clientStatus.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            clientStatusService.save(clientStatus);
        } catch (Exception e) {
            logger.error("Error while saving Candidate Status", e);
        }
        return "redirect:/clientStatusList";
    }

    @RequestMapping(value = {"/addClientStatus"}, method = RequestMethod.GET)
    public String addClientStatus(ModelMap model) {
        ClientStatus clientStatus = new ClientStatus();
        model.addAttribute("clientStatus", clientStatus);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientStatus";
    }

    @RequestMapping(value = { "/edit-clientStatus-{clientStatusId}" }, method = RequestMethod.GET)
    public String editClientStatus(@PathVariable int clientStatusId, ModelMap model) {
        ClientStatus clientStatus = clientStatusService.findById(clientStatusId);
        model.addAttribute("clientStatus", clientStatus);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClientStatus";
    }

    @RequestMapping(value = {"/edit-clientStatus-{clientStatusId}"}, method = RequestMethod.POST)
    public String updateClientStatus(@Valid ClientStatus clientStatus, BindingResult result,
                                     ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addClientStatus";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        clientStatus.setAddedBy((String)model.get("userSSOId"));
        clientStatusService.updateClientStatus(clientStatus);
        return "redirect:/clientStatusList";
    }

    @RequestMapping(value = { "/delete-clientStatus-{clientStatusId}" }, method = RequestMethod.GET)
    public String deleteClientStatus(@PathVariable int clientStatusId, ModelMap model) {
        clientStatusService.deleteById(clientStatusId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/clientStatusList";
    }
}
