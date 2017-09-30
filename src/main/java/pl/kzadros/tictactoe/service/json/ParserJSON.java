package pl.kzadros.tictactoe.service.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import pl.kzadros.tictactoe.entities.GameBoard;

/**
 *
 * @author kzadros
 */
public class ParserJSON <T extends Object> {
    
    public String toJSON(T object) throws JsonProcessingException {
        String json = null;
        
        ObjectMapper mapper = new ObjectMapper();
        json = mapper.writeValueAsString(object);
        
        return json;
    }
    /*
    public String toJSONSimple(int[] arr) throws JsonProcessingException, IOException {
        String json = null;
        
        ObjectMapper mapper = new ObjectMapper();
        //Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
        File out = new File("/home/glamorousfinch6/Pulpit/test_json.json");
        mapper.writeValue(out, arr);
        json = mapper.writeValueAsString(arr);
        
        return json;
    }*/
}
