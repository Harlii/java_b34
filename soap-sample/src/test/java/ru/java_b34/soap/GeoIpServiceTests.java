package ru.java_b34.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.238.133.210");
    assertEquals(geoIp, "RUS");
    System.out.println(geoIp);
  }

  @Test
  public void testInvalidIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("77.238.133.xxx");
    assertEquals(geoIp, "RUS");
  }
}
