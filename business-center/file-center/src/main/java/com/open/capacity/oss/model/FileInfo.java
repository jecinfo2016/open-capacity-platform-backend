package com.open.capacity.oss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * file实体类
*/
@Data
public class FileInfo implements Serializable {

	private static final long serialVersionUID = -1438078028040922174L;
//  md5字段
	private String id;
//  namespace
	private String name;
	//原始文件名称
	private String fileName;
//	是否图片
	private Boolean isImg;
//	上传文件类型
	private String contentType;
//	文件大小
	private long size;
//  冗余字段
	private String path;
//	oss访问路径 oss需要设置公共读
	private String url;
//	FileType字段
	private String source;

	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date createTime;
	/**
	 * 目录磁盘地址
	 */
	@TableField(exist = false)
	private String pathDir;
}
