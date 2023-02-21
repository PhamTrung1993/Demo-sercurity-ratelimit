package com.example.demosercurityratelimit.service.ftpclient;

import com.example.demosercurityratelimit.config.ConfigUtils;
import com.example.demosercurityratelimit.dto.ResponseObject;
import com.example.demosercurityratelimit.dto.ResponseUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FTPReaderService implements IFTPReaderService{
    @Autowired
    private ConfigUtils configUtils;
    private String server = "127.0.0.1";
    private int port = 21;
    private String user = "admin";
    private String pass = "quang01239748392";
    private String remoteFilePath ;
    private FTPClient ftpClient = new FTPClient();
    private InputStream inputStream;

    @PostConstruct
    public void init() {
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.setConnectTimeout(15*60*1000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanUp() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void getConnection() throws IOException {
        System.out.println(ftpClient.isConnected());
        if(!ftpClient.isConnected()){
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.setConnectTimeout(15*60*1000);
        }
    }


    public InputStream Reader(String serial, String urlKey) throws IOException {
        getConnection();
        remoteFilePath = configUtils.getConfig(urlKey);
        inputStream = ftpClient.retrieveFileStream(remoteFilePath + "/" + serial + ".png");
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            ftpClient.completePendingCommand();
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        }
        return inputStream;
    }

//    public String convertToBase64(String serial, String urlKey) throws IOException {
//        String result = FPTConvert.convertToBase64(Reader(serial, urlKey));
//        return result;
//    }

    public ResponseEntity<ResponseObject> readFile( String serial, String urlKey) throws IOException {
        InputStream convertBefore = Reader(serial, urlKey);
        String base64String = FPTConvert.convertToBase64(convertBefore);

        return base64String != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(1, "Convert PNG Successfully", new ResponseUtils(base64String))
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(0, "Convert PNG Failed", "")
                );
    }
}
