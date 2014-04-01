package eu.fp7.scase.base;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildPropertiesTest
{

    private static final Logger LOG = LoggerFactory.getLogger(BuildPropertiesTest.class);

    @Test
    public void testGetBuildVersion()
    {
        LOG.info("testGetBuildVersion()");
        
        String version = BuildProperties.getBuildVersion();
        
        assertThat(version).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetBuildTimeStamp()
    {
        LOG.info("testGetBuildTimeStamp()");
        
        String timeStamp = BuildProperties.getBuildTimestamp();
        
        assertThat(timeStamp).isNotNull().isNotEmpty();
    }

}
