package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private static final byte[] OK = "HTTP/1.1 200 OK\r\n".getBytes();

    private static String parse(String header) {
        if (header.startsWith("GET")) {
            return header.split(" ")[1].substring(6);
        }
        return "";
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        String msg = parse(str);
                        System.out.println(msg);
                        if (msg.equals("Exit")) {
                            out.write(OK);
                            out.write("Bye bye\r\n".getBytes());
                            System.exit(0);
                        } else if (msg.equals("Hello")) {
                            out.write(OK);
                            out.write("Hello\r\n".getBytes());
                        } else if (!msg.isEmpty()) {
                            out.write(OK);
                            out.write((msg + "\r\n").getBytes());
                        }
                        System.out.println(str);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Something wrong:", e);
        }
    }
}
