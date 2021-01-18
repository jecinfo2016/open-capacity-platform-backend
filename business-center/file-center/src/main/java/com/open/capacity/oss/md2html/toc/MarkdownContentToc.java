/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.open.capacity.oss.md2html.toc;

import java.util.List;

/**
 * <p> 单个文件生成接口 </p>
 *
 * <pre> Created: 2018/7/27 下午2:53  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 */
public interface MarkdownContentToc {

    /**
     * 获取纯净的 toc 列表
     * @param contentLines 文件内容
     * @return 生成的结果信息
     * @since 1.0.5 调整接口名称
     */
    List<String> getPureTocList(final List<String> contentLines);


}
