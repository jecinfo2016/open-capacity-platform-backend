package com.open.capacity.oss.md2html;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.open.capacity.oss.md2html.impl.AtxMarkdownContentToc;
import com.open.capacity.oss.md2html.toc.MarkdownContentToc;
import com.open.capacity.oss.md2html.toc.TocConfig;
import com.open.capacity.oss.model.CaLog;
import com.open.capacity.oss.model.Catalog;
import com.open.capacity.oss.model.MarkdownEntity;
import com.open.capacity.oss.service.CatalogService;
import com.open.capacity.oss.utils.CatalogUtil;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.open.capacity.oss.utils.FileReadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MarkDown2HtmlWrapper {

    private static String MD_CSS = null;

    @Autowired
    private CatalogService catalogService;
    private static MarkDown2HtmlWrapper markDown2HtmlWrapper;

    public void setUserInfo(CatalogService catalogServicev) {
        this.catalogService = catalogService;
    }


    @PostConstruct
    public void init() {
        markDown2HtmlWrapper = this;
        markDown2HtmlWrapper.catalogService = this.catalogService;
    }

    static {
        try {
            MD_CSS = FileReadUtil.readAll("md/huimarkdown.css");
            MD_CSS = "<style type=\"text/css\">\n" + MD_CSS + "\n</style>\n";
        } catch (Exception e) {
            MD_CSS = "";
        }
    }


    /**
     * 将本地的markdown文件，转为html文档输出
     *
     * @param path 相对地址or绝对地址 ("/" 开头)
     * @return
     * @throws IOException
     */
    public static MarkdownEntity ofFile(String path, String id) throws IOException {
        return ofStream(FileReadUtil.getStreamByFileName(path), id);
    }


    /**
     * 将网络的markdown文件，转为html文档输出
     *
     * @param url http开头的url格式
     * @return
     * @throws IOException
     */
    public static MarkdownEntity ofUrl(String url, String id) throws IOException {
        return ofStream(FileReadUtil.getStreamByFileName(url), id);
    }


    /**
     * 将流转为html文档输出
     *
     * @param stream
     * @return
     */
    public static MarkdownEntity ofStream(InputStream stream, String id) {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(stream, Charset.forName("UTF-8")));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        TocConfig tocConfig = new TocConfig();
        MarkdownContentToc markdownContentToc = new AtxMarkdownContentToc(tocConfig);
        List<String> tocList = markdownContentToc.getPureTocList(lines);
        String tocLists = Joiner.on("\n").join(tocList);
        String content = Joiner.on("\n").join(lines);
        //返回目录
        CaLog caLog = CatalogUtil.mdCatalog(content);
        Catalog catalog = new Catalog();
        catalog.setId(id);
        String o = JSON.toJSONString(caLog.toString());
        catalog.setCatalog(o);
        Catalog catalog1 = markDown2HtmlWrapper.catalogService.findCatalog(id);
        if (catalog1!=null){
            markDown2HtmlWrapper.catalogService.update(catalog);
        }else {
            markDown2HtmlWrapper.catalogService.save(catalog);
        }
        return ofContent(tocLists+content);
    }


    /**
     * 直接将markdown语义的文本转为html格式输出
     *
     * @param content markdown语义文本
     * @return
     */
    public static MarkdownEntity ofContent(String content) {
        String html = parse(content);
        MarkdownEntity entity = new MarkdownEntity();
        entity.setCss(MD_CSS);
        entity.setHtml(html);
        entity.addDivStyle("class", "markdown-body ");
        return entity;
    }


    /**
     * markdown to image
     *
     * @param content markdown contents
     * @return parse html contents
     */
    public static String parse(String content) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);

        // enable table parse!
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));


        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(content);
        return renderer.render(document);
    }

}

