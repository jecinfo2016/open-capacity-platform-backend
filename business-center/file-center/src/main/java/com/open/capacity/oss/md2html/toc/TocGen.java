/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.open.capacity.oss.md2html.toc;


import java.util.List;

/**
 *  toc 内容
 *
 * @author bbhou
 * @version 1.0.0
 * @since 1.0.0, 2018/01/30
 */
public class TocGen {

    /**
     * 文件路径
     */
    private final String filePath;

    /**
     * toc 的相关内容
     */
    private final List<String> tocLines;

    public TocGen(String filePath, List<String> tocLines) {
        this.filePath = filePath;
        this.tocLines = tocLines;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<String> getTocLines() {
        return tocLines;
    }

    @Override
    public String toString() {
        return "TocGen{" +
                "filePath='" + filePath + '\'' +
                ", tocLines=" + tocLines +
                '}';
    }
}
