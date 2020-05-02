package com.gilxyj.common;

import java.io.*;

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
 * @create: 2020-05-02 15:52
 **/
/*
  HTTP Response = Status-Line
    *(( general-header | response-header | entity-header ) CRLF)
    CRLF
    [ message-body ]
    Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
*/
public class Response {

    public static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStatticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        File file = new File(Constants.WEB_ROOT, request.getUri());
        try (FileInputStream fis = new FileInputStream(file)) {
            if (file.exists()) {
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }else {
                String errorMsg = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type:text/html\r\n" + "Content-Length:23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
                outputStream.write(errorMsg.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
