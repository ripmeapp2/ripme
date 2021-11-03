package com.rarchives.ripme.tst.ripper.rippers;

import java.io.IOException;
import java.net.URL;

import com.rarchives.ripme.ripper.rippers.VidbleRipper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class VidbleRipperTest extends RippersTest {
    @Test
    @Disabled("https://github.com/ripmeapp2/ripme/issues/51")
    public void testVidbleRip() throws IOException {
        VidbleRipper ripper = new VidbleRipper(new URL("http://www.vidble.com/album/y1oyh3zd"));
        testRipper(ripper);
    }

    @Test
    @Disabled("https://github.com/ripmeapp2/ripme/issues/51")
    public void testGetGID() throws IOException {
        URL url = new URL("http://www.vidble.com/album/y1oyh3zd");
        VidbleRipper ripper = new VidbleRipper(url);
        Assertions.assertEquals("y1oyh3zd", ripper.getGID(url));
    }
}

