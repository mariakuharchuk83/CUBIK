package tasks.first.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import tasks.first.main.Entity;
import tasks.first.main.Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServerTest {
    private Server testServer;
    private final ServerSocketChannel serverSocketChannel = Mockito.mock(ServerSocketChannel.class);
    private final Entity student = new Entity("Mary", 19, "TSHNU");
    private final SelectionKey acceptKey = Mockito.mock(SelectionKey.class);
    private final SelectionKey readKey = Mockito.mock(SelectionKey.class);

    @Before
    public void setUp() throws IOException {
        SocketChannel client = Mockito.mock(SocketChannel.class);
        when(serverSocketChannel.accept()).thenReturn(client);
        this.testServer = Mockito.spy(Server.class);
        when(acceptKey.readyOps()).thenReturn(SelectionKey.OP_ACCEPT);
        when(readKey.readyOps()).thenReturn(SelectionKey.OP_READ);
    }

    @Test
    public void testReadingObject() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(student);
        oos.close();
        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        Serializable studentReceived = testServer.readObject(buffer);
        Assert.assertEquals(studentReceived, student);
    }

    @Test
    public void testSuccessfulResponse() throws IOException {
        SocketChannel client = Mockito.mock(SocketChannel.class);
        testServer.respond(true, client);
        verify(client).write(ByteBuffer.wrap("Received entity".getBytes()));
    }

    @Test
    public void testUnsuccessfulResponse() throws IOException {
        SocketChannel client = Mockito.mock(SocketChannel.class);
        testServer.respond(false, client);
        verify(client).write(ByteBuffer.wrap("Something went wrong".getBytes()));
    }
}