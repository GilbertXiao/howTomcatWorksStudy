package com.gilxyj.main;

import com.gilxyj.common.Request;
import com.gilxyj.common.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: howTomcatWorksStudy
 * @description:
 * @作者 飞码录
 * @微信公众号 飞码录
 * @网站 http://www.codesboy.cn
 * @国际站 http://www.codesboy.com
 * @微信 gilbertxy
 * @GitHub https://github.com/GilbertXiao
 * @Gitee https://gitee.com/gilbertxiao
 * @create: 2020-05-02 16:20
 **/
public class HttpServer {
    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    private void await() {
        ServerSocket serverSocket =null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            try (Socket socket= serverSocket.accept();
                 InputStream inputStream=socket.getInputStream();
                 OutputStream outputStream=socket.getOutputStream()){
                // create Request object and parse
                Request request = new Request(inputStream);
                request.parse();

                // create Response object
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStatticResource();

                //check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }



        }


    }
}
