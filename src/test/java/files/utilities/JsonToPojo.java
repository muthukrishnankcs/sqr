package files.utilities;

import com.sun.codemodel.JCodeModel;
import org.apache.commons.io.FileUtils;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonToPojo {
    public static void main(String args[])
    {
        convertJsonToPojo("CreateRepo", "files.POJOClasses.Request");
    }

    public static void convertJsonToPojo(String jsonFileName, String packageName) {
        File inputJson= new File("./src/test/java/files/resources/"+jsonFileName+".json");
        File outputPojoDirectory=new File("./src/test/java");
        File pojoFile = new File(outputPojoDirectory+"/"+packageName+"/"+jsonFileName+".java");
        outputPojoDirectory.mkdirs();
        try {
            if(!pojoFile.exists())
            {
                new JsonToPojo().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
            }
            else
            {
                System.out.println("File already Exists: "+jsonFileName);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Encountered issue while converting to pojo: "+e.getMessage());
            e.printStackTrace();
        }
    }
    public static void convert2JSON(URL inputJson, File outputPojoDirectory, String packageName, String className) throws IOException{
        JCodeModel codeModel = new JCodeModel();
        URL source = inputJson;
        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() { // set config option by overriding method
                return true;
            }
            public SourceType getSourceType(){
                return SourceType.JSON;
            }
        };
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, className, packageName, source);
        codeModel.build(outputPojoDirectory);
    }
}
