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

public class VerifyPath {
    private static final Logger logger = LogManager.getLogger();

    public boolean Verifying(){
        logger.info("** Verifying Path");
        boolean output = Match();
        return output;
    }

    public boolean Match(){
        return true;
    }

    //first need factorized to canonical in case they enter factorial 


}


