package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


}


