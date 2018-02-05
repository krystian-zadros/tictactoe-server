package pl.kzadros.tictactoe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kzadros
 */

@RestController
public class MainController {
    
    @RequestMapping("/")
    public String index() {
        return "Hello world, brochacho!";
    }
}
