package eu.fp7.scase.base;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BuildProperties
{

    private static final Logger LOG = LoggerFactory.getLogger(BuildProperties.class);

    private static String buildVersion;
    private static String buildTimestamp;

    private BuildProperties() {};
        
    static {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("build.properties"));
            buildVersion = props.getProperty("build.version");
            buildTimestamp = props.getProperty("build.timestamp");
        } catch (IOException e) {
            String msg = "Kann Properties nicht laden.";
            LOG.error(msg, e); 
        }   
    }   

    public static String getBuildVersion()
    {
        return buildVersion;
    }
    
    public static String getBuildTimestamp()
    {
        return buildTimestamp;
    }
    
}
