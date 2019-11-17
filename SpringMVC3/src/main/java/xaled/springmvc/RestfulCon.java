package xaled.springmvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc")
public class RestfulCon {
    @GetMapping("/rest/{id}/{name}")
    public String hello(@PathVariable int id,@PathVariable String name){
        return "view";
    }

}
