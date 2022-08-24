package org.jslee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServerTest {
    Server server;

    Socket socket;
    DataInputStream dataInputStream;

    @BeforeEach
    void init() {
        server = new Server();
        socket = mock(Socket.class);
        dataInputStream = mock(DataInputStream.class);
    }

    @Test
    void recieveMessageHeaderFromClientTest() throws IOException {
        //given
        byte[] headerData = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};

        doAnswer(mockData -> {
            System.arraycopy(headerData, 0, mockData.getArguments()[0], 0, 8);
            return null;
        }).when(dataInputStream).readFully(any(byte[].class), eq(0), eq(8));

        when(socket.getInputStream()).thenReturn(dataInputStream);

        //when
        var header = Server.recieveMessageHeaderFromClient(dataInputStream);

        //then
        assertArrayEquals(header, headerData);
    }
}