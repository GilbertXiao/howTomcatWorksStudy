package com.gilxyj.common;

import java.io.IOException;
import java.io.InputStream;

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
 * @create: 2020-05-02 15:35
 **/
public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse(){
        //read a set of characters from socket
        StringBuffer stringBuffer = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i=-1;
        }
        for (int j = 0; j < i; j++) {
            stringBuffer.append((char) buffer[j]);
        }
        System.out.println(stringBuffer.toString());
        uri = parseUri(stringBuffer.toString());

    }

    private String parseUri(String requestString) {
        int index1,index2;
        index1 = requestString.indexOf(Constants.SPACE_CHARACTER);
        if (index1 != -1) {
            index2 = requestString.indexOf(Constants.SPACE_CHARACTER, index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }

        }
        return null;
    }

    public String getUri(){
        return uri;
    }
}
