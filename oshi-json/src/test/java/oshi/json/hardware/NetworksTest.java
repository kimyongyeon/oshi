/**
 * Oshi (https://github.com/dblock/oshi)
 *
 * Copyright (c) 2010 - 2016 The Oshi Project Team
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Maintainers:
 * dblock[at]dblock[dot]org
 * widdis[at]gmail[dot]com
 * enrico.bianchi[at]gmail[dot]com
 *
 * Contributors:
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.json.hardware;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import oshi.json.SystemInfo;

/**
 * Test Networks
 */
public class NetworksTest {
    /**
     * Test network interfaces extraction.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Test
    public void testNetworkInterfaces() throws IOException {
        long timeStamp = System.currentTimeMillis();
        SystemInfo si = new SystemInfo();

        for (NetworkIF net : si.getHardware().getNetworkIFs()) {
            assertNotNull(net.getNetworkInterface());
            assertNotNull(net.getName());
            assertNotNull(net.getDisplayName());
            assertNotNull(net.getMacaddr());
            assertNotNull(net.getIPv4addr());
            assertNotNull(net.getIPv6addr());
            assertTrue(net.getBytesRecv() >= 0);
            assertTrue(net.getBytesSent() >= 0);
            assertTrue(net.getPacketsRecv() >= 0);
            assertTrue(net.getPacketsSent() >= 0);
            assertTrue(net.getSpeed() >= 0);
            assertTrue(net.getMTU() >= 0);
            assertTrue(net.getTimeStamp() >= timeStamp);

            net.setBytesRecv(10L);
            net.setBytesSent(20L);
            net.setPacketsRecv(30L);
            net.setPacketsSent(40L);
            net.setSpeed(50L);
            net.setTimeStamp(timeStamp);

            assertEquals(10L, net.getBytesRecv());
            assertEquals(20L, net.getBytesSent());
            assertEquals(30L, net.getPacketsRecv());
            assertEquals(40L, net.getPacketsSent());
            assertEquals(50L, net.getSpeed());
            assertEquals(timeStamp, net.getTimeStamp());

            net.updateNetworkStats();
            assertTrue(net.getBytesRecv() >= 0);
            assertTrue(net.getBytesSent() >= 0);
            assertTrue(net.getPacketsRecv() >= 0);
            assertTrue(net.getPacketsSent() >= 0);
            assertTrue(net.getSpeed() >= 0);
            assertTrue(net.getTimeStamp() >= timeStamp);
        }
    }
}
