package eu.fp7.scase.base;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

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
        
        assertThat(version, not(isEmptyOrNullString()));
    }

    @Test
    public void testGetBuildTimeStamp()
    {
        LOG.info("testGetBuildTimeStamp()");
        
        String timeStamp = BuildProperties.getBuildTimestamp();
        
        assertThat(timeStamp, not(isEmptyOrNullString()));
    }

}
