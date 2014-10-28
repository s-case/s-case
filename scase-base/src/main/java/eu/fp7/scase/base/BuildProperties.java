package eu.fp7.scase.base;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class for providing build information. That are (pom) version and timestamp. 
 * @author rmagnus
 *
 */
public final class BuildProperties
{

    private static final Logger LOG = LoggerFactory.getLogger(BuildProperties.class);

    private static String buildVersion;
    private static String buildTimestamp;

    private BuildProperties() {}
        
    static {
        Properties props = new Properties();
        try {
            props.load(BuildProperties.class.getClassLoader()
                    .getResourceAsStream("build.properties"));
            buildVersion = props.getProperty("build.version");
            buildTimestamp = props.getProperty("build.timestamp");
        } catch (IOException e) {
            String msg = "Kann Properties nicht laden.";
            LOG.error(msg, e); 
        }   
    }   

    
    /**
     * returns the build version.
     * @return build version
     */
    public static String getBuildVersion()
    {
        return buildVersion;
    }
    
    /**
     * returns the build time stamp.
     * @return build time stamp
     */
    public static String getBuildTimestamp()
    {
        return buildTimestamp;
    }
    
}
