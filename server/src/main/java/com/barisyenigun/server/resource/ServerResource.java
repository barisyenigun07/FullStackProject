package com.barisyenigun.server.resource;

import com.barisyenigun.server.enumeration.Status;
import com.barisyenigun.server.model.Response;
import com.barisyenigun.server.model.Server;
import com.barisyenigun.server.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImplementation serverServiceImplementation;
    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(Map.of("servers",serverServiceImplementation.list(30)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceImplementation.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(Map.of("server",server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) throws IOException{

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDate.now())
                        .data(Map.of("server",serverServiceImplementation.create(server)))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping Failed")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
}
