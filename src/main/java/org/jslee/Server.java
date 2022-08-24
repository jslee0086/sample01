package org.jslee;

import java.io.DataInputStream;
import java.io.IOException;

public class Server {

    public static byte[] recieveMessageHeaderFromClient(DataInputStream dis) throws IOException {
        byte[] header = new byte[8];
        dis.readFully(header, 0, header.length);
        return header;
    }
}
