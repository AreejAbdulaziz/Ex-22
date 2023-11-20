package com.example.validationevent2.Controller;

import com.example.validationevent2.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events=new ArrayList<>();
    @PostMapping("/add")
    public ResponseEntity addEvent(@Valid @RequestBody Event e, Errors error){
        if(error.hasErrors()){
        String message=error.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.add(e);
        return ResponseEntity.status(HttpStatus.OK).body("event added");
    }
    @GetMapping("/display")
    public ResponseEntity display(){
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }
    @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable int index,@Valid @RequestBody Event e,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.set(index,e);
        return ResponseEntity.status(HttpStatus.OK).body("event updated");
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("event deleted");
    }
    @PutMapping("/change/{index}")
    public ResponseEntity change(@PathVariable int index,@Valid@RequestBody int newCapacity,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        events.get(index).setCapacity(newCapacity);
        return ResponseEntity.status(HttpStatus.OK).body("capacity changed");
    }
    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable String id){
        for(Event e:events){
            if(e.getId().equals(id)){
                return ResponseEntity.status(HttpStatus.OK).body("event : "+e);
            }
        } return ResponseEntity.status(HttpStatus.OK).body("not found");
    }
}
