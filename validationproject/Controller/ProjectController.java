package com.example.validationproject.Controller;

import com.example.validationproject.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project>projects=new ArrayList<>();
    @PostMapping("/add")
    public ResponseEntity addProject(@Valid @RequestBody Project p, Errors error){
        if(error.hasErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        projects.add(p);
        return ResponseEntity.status(HttpStatus.OK).body("project added");
        }
    @GetMapping("/display")
    public ResponseEntity addProject(){
        return  ResponseEntity.status(HttpStatus.OK).body(projects);
    }
    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @Valid @RequestBody Project p, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        projects.set(index,p);
        return ResponseEntity.status(HttpStatus.OK).body("Project updated");
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("project deleted");
    }
    @PutMapping("/change/{index}")
    public ResponseEntity changeProject(@PathVariable int index){
        if(projects.get(index).getStatus().equals("not started")){
            projects.get(index).setStatus("in progress");
        } else if (projects.get(index).getStatus().equals("in progress")){
            projects.get(index).setStatus("completed");
        } else projects.get(index).setStatus("completed");
        return ResponseEntity.status(HttpStatus.OK).body("status changed");
    }
    @GetMapping("/search/{title}")
    public ResponseEntity searchProject(@PathVariable String title){
        for(Project p:projects){
            if(p.getTitle().equals(title)){
                return ResponseEntity.status(HttpStatus.OK).body("project :"+p);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("not found");
    }
    ArrayList<Project>projectsCompany=new ArrayList<>();
    @GetMapping("/company/{name}")
    public ResponseEntity projectCompany(@PathVariable String name){
        for(Project p:projects){
            if(p.getCompanyName().equals(name)){
                projectsCompany.add(p);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(projectsCompany);
    }
    }

