package com.coditas.controller;

import com.coditas.bean.ClientBean;
import com.coditas.dto.Client;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {


    private Object ClientBean;

   /* @GetMapping("/clients")
    public List<ClientBean> getClients() {
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(1, "Tavisca", "test@tavisca.com", "1234"));
        clientList.add(new Client(2, "Parkar", "test@parkar.com", "5678"));
        clientList.add(new Client(3, "Simantic", "test@Simantic.com", "1357"));
    }*/

    @GetMapping("/client/{id}")
    public ClientBean getClientCount(@PathVariable(value = "id") int id) {


        return (ClientBean) ClientBean;
    }

    //This API returns client details along with associate project details of it.
    @GetMapping("/clientdetails/{id}")
    public ClientBean getClientDetails(@PathVariable(value = "id") int id) {


        return (ClientBean) ClientBean;
    }

    //This API used to add new client
    @PostMapping("/addclient")
    public ClientBean addClient() {


        return (ClientBean) ClientBean;
    }

    //This API used to add new client
    @PutMapping("/updateclient")
    public ClientBean UpdateClient() {


        return (ClientBean) ClientBean;
    }
}
