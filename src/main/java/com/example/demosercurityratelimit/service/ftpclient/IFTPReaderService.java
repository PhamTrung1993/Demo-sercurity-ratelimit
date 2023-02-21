package com.example.demosercurityratelimit.service.ftpclient;

import com.example.demosercurityratelimit.dto.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;

public interface IFTPReaderService {
    ResponseEntity<ResponseObject> readFile(String serial, String urlKey) throws IOException;
}
