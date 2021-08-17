package ru.halmount.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.halmount.test.entity.Client;
import ru.halmount.test.model.CreateClientDTO;
import ru.halmount.test.model.UpdateClientDTO;
import ru.halmount.test.service.ClientService;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody CreateClientDTO requestBody) {
        return clientService.createClient(requestBody);
    }

    @PutMapping("/clients/{idClient}")
    public Client putClient(@PathVariable(name = "idClient") Integer idClient, @RequestBody UpdateClientDTO requestBody) {
        return clientService.putClient(idClient, requestBody);
    }

    @DeleteMapping("/clients/{idClient}")
    public void deleteClient(@PathVariable(name = "idClient") Integer idClient) {
        clientService.deleteClient(idClient);
    }
}
