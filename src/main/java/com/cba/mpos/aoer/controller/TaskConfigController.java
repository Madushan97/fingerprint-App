//package com.cba.mpos.aoer.controller;
//
//import com.cba.mpos.aoer.model.internal.TaskConfig;
//import com.cba.mpos.aoer.service.TaskConfigService;
//import com.cba.mpos.aoer.utils.RestApiV1;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestApiV1
//public class TaskConfigController {
//
//    @Autowired
//    TaskConfigService taskConfigService;
//
//    @GetMapping(path = "/task-configs")
//    public ResponseEntity<List<TaskConfig>> getAll() throws Exception {
//        return new ResponseEntity<>(taskConfigService.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/task-configs/{id}")
//    public ResponseEntity<TaskConfig> getById(@PathVariable("id") Integer id) throws Exception {
//        return new ResponseEntity<>(taskConfigService.findById(id), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/task-configs")
//    public ResponseEntity<TaskConfig> add(@RequestBody TaskConfig taskConfig) throws Exception {
//        return new ResponseEntity<>(taskConfigService.create(taskConfig), HttpStatus.OK);
//    }
//
//    @PutMapping(path = "/task-configs/{id}")
//    public ResponseEntity<TaskConfig> update(@PathVariable("id") Integer id, @RequestBody TaskConfig taskConfig) throws Exception {
//        return new ResponseEntity<>(taskConfigService.update(id, taskConfig), HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = "/task-configs/{id}")
//    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws Exception {
//        taskConfigService.deleteById(id);
//        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
//    }
//}
