package com.example.demosercurityratelimit.controller;

import com.example.demosercurityratelimit.dto.ResponseObject;
import com.example.demosercurityratelimit.dto.ResponseUtils;
import com.example.demosercurityratelimit.service.ftpclient.FTPReaderService;
import com.example.demosercurityratelimit.service.ftpclient.IFTPReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/userManagement/v1")
public class UserController {
    @Autowired
    private IFTPReaderService ftpReaderService;
    @GetMapping("/readFile")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> readFile(@PathVariable String serial, @PathVariable String urlKey) throws IOException {
        return ftpReaderService.readFile(serial,urlKey);
    }
}
