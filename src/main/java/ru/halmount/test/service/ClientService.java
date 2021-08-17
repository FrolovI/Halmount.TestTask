package ru.halmount.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.halmount.test.entity.Client;
import ru.halmount.test.model.CreateClientDTO;
import ru.halmount.test.model.UpdateClientDTO;
import ru.halmount.test.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client createClient(CreateClientDTO requestBody) {

        Client queueClient = new Client();
        queueClient.idBank = requestBody.idBank;
        queueClient.fullName = requestBody.fullName;
        queueClient.email = requestBody.email;
        queueClient.passNumber = requestBody.passNumber;
        queueClient.phoneNumber = requestBody.phoneNumber;
        return clientRepository.save(queueClient);
    }

    public Client putClient(Integer idClient, UpdateClientDTO requestBody) {
        Client updateClient = clientRepository.findById(idClient).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateClient.fullName = requestBody.fullName;
        updateClient.email = requestBody.email;
        updateClient.passNumber = requestBody.passNumber;
        updateClient.phoneNumber = requestBody.phoneNumber;
        return clientRepository.save(updateClient);
    }

    public void deleteClient(Integer idClient) {
        clientRepository.deleteById(idClient);
    }
}
