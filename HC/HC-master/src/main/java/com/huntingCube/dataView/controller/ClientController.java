package com.huntingCube.dataView.controller;

import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by dgup27 on 5/20/2017.
 */
@Controller
public class ClientController extends BaseController{

    static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping(value = {"/clientList"}, method = RequestMethod.GET)
    public String clientList(ModelMap model) {

        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/clientList";
    }

    @RequestMapping(value = {"/addClient"}, method = RequestMethod.POST)
    public String saveClient(@Valid Client client, BindingResult result,
                             ModelMap model) {
        try {
            HuntingCubeUtility.setGlobalModelAttributes(model, userService);
            logger.info(client.toString());
            if (result.hasErrors()) {
                logger.info("Error in result");
            }
            clientService.save(client);
        } catch (Exception e) {
            logger.error("Error while saving Client", e);
        }
        return "redirect:/clientList";
    }

    @RequestMapping(value = {"/addClient"}, method = RequestMethod.GET)
    public String addClient(ModelMap model) {
        Client client = new Client();
        model.addAttribute("client", client);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClient";
    }

    @RequestMapping(value = { "/edit-client-{clientId}" }, method = RequestMethod.GET)
    public String editClient(@PathVariable int clientId, ModelMap model) {
        Client client = clientService.findById(clientId);
        model.addAttribute("client", client);
        model.addAttribute("edit", true);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "dataView/addClient";
    }

    @RequestMapping(value = {"/edit-client-{clientId}"}, method = RequestMethod.POST)
    public String updateClient(@Valid Client client, BindingResult result,
                               ModelMap model) {
        if(result.hasErrors()) {
            return "dataView/addClient";
        }
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        client.setAddedBy((String)model.get("userSSOId"));
        clientService.updateClient(client);
        return "redirect:/clientList";
    }

    @RequestMapping(value = { "/delete-client-{clientId}" }, method = RequestMethod.GET)
    public String deleteClient(@PathVariable int clientId, ModelMap model) {
        clientService.deleteById(clientId);
        HuntingCubeUtility.setGlobalModelAttributes(model, userService);
        return "redirect:/clientList";
    }

}
