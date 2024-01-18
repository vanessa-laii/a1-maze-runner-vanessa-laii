package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;


public class Configuration {

    private static final Logger logger = LogManager.getLogger();

    public Configuration(String[] args){



        try{
            Options options = new Options();
            options.addOption("i", true, "option reaction to i");
            CommandLineParser parser = new DefaultParser();

            CommandLine cmd = parser.parse(options, args);
            String filePath = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file " + filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        }

        catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }


    }

    


}