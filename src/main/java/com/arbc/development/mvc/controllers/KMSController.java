package com.arbc.development.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arbc.development.mvc.models.entities.ServerEncrypted;
import com.arbc.development.mvc.models.entities.Servers;
import com.arbc.development.mvc.models.entities.ServersEncrypted;
import com.arbc.development.mvc.models.entities.Prueba;
import com.arbc.development.mvc.models.entities.Server;
import com.arbc.development.mvc.services.KMSService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.rpc.context.AttributeContext.Response;

@RestController
@RequestMapping("/kms")
public class KMSController {

    @Autowired
    private KMSService kmsService;

    @GetMapping("/servers")
    public Servers encrypted (@RequestHeader Map<String,String> headers) throws Exception {
        String uri = "https://corebackend.onrender.com/api/v1/servidores_encriptados";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println(headers.get("authorization"));
        httpHeaders.set(HttpHeaders.AUTHORIZATION, headers.get("authorization"));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response  = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        
        String responseBody = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        ServersEncrypted serversCipher = objectMapper.readValue(responseBody, ServersEncrypted.class);
        Servers servers = new Servers();
        List<Server> serversLoad = new ArrayList();
        for (ServerEncrypted server: serversCipher.getServidores())
        {
            Server serverLoad = new Server();
            serverLoad.setSO(kmsService.decrypt(server.getSO()));
            serverLoad.setNombre(kmsService.decrypt(server.getNombre()));
            serverLoad.setMotorBase(kmsService.decrypt(server.getMotorBase()));
            serverLoad.setSO(kmsService.decrypt(server.getSO()));
            serversLoad.add(serverLoad);
        }
        servers.setServidores(serversLoad);
        servers.setTotal_count(serversCipher.getTotal_count());
        return servers;
    }

    //@PostMapping("/decrypt")
    //public Prueba decrypt(@RequestBody ServerEncrypted cipher) throws Exception {
    //    Prueba data = new Prueba();
    //    data.setTextParam(kmsService.decrypt(cipher.getCiphertext()));
    //    return data;
    //}
}
