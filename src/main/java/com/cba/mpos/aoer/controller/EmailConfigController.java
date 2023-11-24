//package com.cba.mpos.aoer.controller;
//
//import com.cba.mpos.aoer.model.internal.EmailConfig;
//import com.cba.mpos.aoer.service.EmailConfigService;
//import com.cba.mpos.aoer.utils.RestApiV1;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestApiV1
//public class EmailConfigController {
//
//    @Autowired
//    private EmailConfigService emailConfigService;
//
//    @GetMapping(path = "/email-configs")
//    public ResponseEntity<List<EmailConfig>> getAll() throws Exception {
//        return new ResponseEntity<>(emailConfigService.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/email-configs/{id}")
//    public ResponseEntity<EmailConfig> getById(@PathVariable("id") Integer id) throws Exception {
//        return new ResponseEntity<>(emailConfigService.findById(id), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/email-configs")
//    public ResponseEntity<EmailConfig> add(@RequestBody EmailConfig emailConfig) throws Exception {
//        return new ResponseEntity<>(emailConfigService.create(emailConfig), HttpStatus.OK);
//    }
//
//    @PutMapping(path = "/email-configs/{id}")
//    public ResponseEntity<EmailConfig> update(@PathVariable("id") Integer id, @RequestBody EmailConfig emailConfig) throws Exception {
//        return new ResponseEntity<>(emailConfigService.update(id, emailConfig), HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = "/email-configs/{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws Exception {
//        emailConfigService.deleteById(id);
//        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
//    }
//}
