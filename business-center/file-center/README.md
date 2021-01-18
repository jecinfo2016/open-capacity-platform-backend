# 基于Hbase的海量小文件存储方案

## 技术架构使用Spring Boot + Hbase

## 提供上传、下载浏览、删除三类接口

## 启动

运行主类SmallFileApplication

## API文档

<http://localhost:8008/swagger-ui.html>

**文件中心api**


**简介**：文件中心api

**HOST**:192.168.124.20:5000


**联系人**:


**Version**:1.0

**接口路径**：/v2/api-docs


# FILE API

## 删除文件

**接口说明**:根据文件在Hbase中的唯一ID删除


**接口地址**:`/delete`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|id| id  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除文件

**接口说明**:根据文件在Hbase中的唯一ID删除


**接口地址**:`/delete`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|id| id  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除文件

**接口说明**:根据文件在Hbase中的唯一ID删除


**接口地址**:`/delete`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|id| id  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 删除文件

**接口说明**:根据文件名删除


**接口地址**:`/deleteByName`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除文件

**接口说明**:根据文件名删除


**接口地址**:`/deleteByName`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除文件

**接口说明**:根据文件名删除


**接口地址**:`/deleteByName`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## findFiles


**接口说明**:



**接口地址**:`/files`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|params| params  | query | true |object  |    |

**响应数据**:

```json
{
	"code": 0,
	"count": 0,
	"current": 0,
	"data": [
		{
			"contentType": "",
			"createTime": "",
			"id": "",
			"isImg": true,
			"name": "",
			"path": "",
			"pathDir": "",
			"size": 0,
			"source": "",
			"url": ""
		}
	],
	"size": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |int32  |    |
|count|   |int64  |    |
|current|   |int32  |    |
|data|   |array  | FileInfo   |
|size|   |int32  |    |



**schema属性说明**




**FileInfo**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |
|createTime |    |date-time  |    |
|id |    |string  |    |
|isImg |    |boolean  |    |
|name |    |string  |    |
|path |    |string  |    |
|pathDir |    |string  |    |
|size |    |int64  |    |
|source |    |string  |    |
|url |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |PageResult«FileInfo»|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## upload


**接口说明**:



**接口地址**:`/files-anon`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|file| file  | formData | true |file  |    |

**响应数据**:

```json
{
	"contentType": "",
	"createTime": "",
	"id": "",
	"isImg": true,
	"name": "",
	"path": "",
	"pathDir": "",
	"size": 0,
	"source": "",
	"url": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|contentType|   |string  |    |
|createTime|   |date-time  | date-time   |
|id|   |string  |    |
|isImg|   |boolean  |    |
|name|   |string  |    |
|path|   |string  |    |
|pathDir|   |string  |    |
|size|   |int64  |    |
|source|   |string  |    |
|url|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |FileInfo|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## save


**接口说明**:



**接口地址**:`/files-save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|contentType|   | query | false |string  |    |
|createTime|   | query | false |string  |    |
|id|   | query | false |string  |    |
|isImg|   | query | false |boolean  |    |
|name|   | query | false |string  |    |
|path|   | query | false |string  |    |
|pathDir|   | query | false |string  |    |
|size|   | query | false |integer  |    |
|source|   | query | false |string  |    |
|url|   | query | false |string  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## uploadLayui


**接口说明**:



**接口地址**:`/files/layui`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|file| file  | formData | true |file  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## md转html

**接口说明**:通过hbase下载接口获取地址转换


**接口地址**:`/files/markdown2html`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|fileUrl| fileUrl  | query | true |string  |    |
|id| id  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## md转html

**接口说明**:通过hbase下载接口获取地址转换


**接口地址**:`/files/markdown2html`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|fileUrl| fileUrl  | query | true |string  |    |
|id| id  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 更新文档

**接口说明**:字符串转MultipartFile更新


**接口地址**:`/files/updateMd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/json"]`


**请求示例**：
```json
{
	"content": "",
	"id": 0,
	"namespace": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|fileUpdate| fileUpdate  | body | true |FileUpdate  | FileUpdate   |

**schema属性说明**



**FileUpdate**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|content|   | body | false |string  |    |
|id|   | body | false |int32  |    |
|namespace|   | body | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## upload


**接口说明**:



**接口地址**:`/files/upload`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|file| file  | formData | true |file  |    |
|namespace| namespace  | query | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## upload


**接口说明**:



**接口地址**:`/files/upload`


**请求方式**：`PUT`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|file| file  | formData | true |file  |    |
|namespace| namespace  | query | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 上传文件

**接口说明**:支持多个文件批量上传


**接口地址**:`/files/upload2`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## delete


**接口说明**:



**接口地址**:`/files/{id}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|id| id  | path | true |string  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 下载文件

**接口说明**:根据文件在Hbase中的唯一ID下载


**接口地址**:`/list/{namespace}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|id| id  | path | true |string  |    |
|namespace| namespace  | path | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 下载文件

**接口说明**:根据文件在Hbase中的唯一ID下载


**接口地址**:`/show/{namespace}/{id}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|downAddress| downAddress  | query | false |string  |    |
|endSuffix| endSuffix  | query | false |string  |    |
|id| id  | path | true |string  |    |
|namespace| namespace  | path | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`PUT`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`PATCH`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`OPTIONS`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 下载文件

**接口说明**:根据文件名下载


**接口地址**:`/showByName`


**请求方式**：`HEAD`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|name| name  | query | true |string  |    |
|namespace| namespace  | query | true |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# MODULE API

## 删除模块


**接口说明**:



**接口地址**:`/module/delete`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|Id| Id  | query | true |integer  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 根据id查询模块详情


**接口说明**:



**接口地址**:`/module/get`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|Id| Id  | query | true |integer  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取文档目录


**接口说明**:



**接口地址**:`/module/getCatalog`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|id| id  | query | true |string  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 获取列表

**接口说明**:根据分页等查询条件查询列表


**接口地址**:`/module/list`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTime": "",
	"creater": "",
	"documentAddress": "",
	"icon": "",
	"id": 0,
	"limit": 0,
	"name": "",
	"page": 0,
	"showUrl": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|moduleInfo| moduleInfo  | body | true |module_info  | module_info   |

**schema属性说明**



**module_info**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | body | false |date-time  |    |
|creater| 创建人  | body | false |string  |    |
|documentAddress| 模块文档地址  | body | false |string  |    |
|icon| 模块图标  | body | false |string  |    |
|id| 模块id  | body | false |int32  |    |
|limit|   | body | false |int32  |    |
|name| 模块名称  | body | false |string  |    |
|page|   | body | false |int32  |    |
|showUrl| 模块文档展示静态文档地址  | body | false |string  |    |

**响应数据**:

```json
{
	"code": 0,
	"count": 0,
	"current": 0,
	"data": [],
	"size": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code|   |int32  |    |
|count|   |int64  |    |
|current|   |int32  |    |
|data|   |array  |    |
|size|   |int32  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |PageResult|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 新增模块


**接口说明**:



**接口地址**:`/module/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTime": "",
	"creater": "",
	"documentAddress": "",
	"icon": "",
	"id": 0,
	"limit": 0,
	"name": "",
	"page": 0,
	"showUrl": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|moduleInfo| moduleInfo  | body | true |module_info  | module_info   |

**schema属性说明**



**module_info**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | body | false |date-time  |    |
|creater| 创建人  | body | false |string  |    |
|documentAddress| 模块文档地址  | body | false |string  |    |
|icon| 模块图标  | body | false |string  |    |
|id| 模块id  | body | false |int32  |    |
|limit|   | body | false |int32  |    |
|name| 模块名称  | body | false |string  |    |
|page|   | body | false |int32  |    |
|showUrl| 模块文档展示静态文档地址  | body | false |string  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 修改模块


**接口说明**:



**接口地址**:`/module/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTime": "",
	"creater": "",
	"documentAddress": "",
	"icon": "",
	"id": 0,
	"limit": 0,
	"name": "",
	"page": 0,
	"showUrl": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|moduleInfo| moduleInfo  | body | true |module_info  | module_info   |

**schema属性说明**



**module_info**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | body | false |date-time  |    |
|creater| 创建人  | body | false |string  |    |
|documentAddress| 模块文档地址  | body | false |string  |    |
|icon| 模块图标  | body | false |string  |    |
|id| 模块id  | body | false |int32  |    |
|limit|   | body | false |int32  |    |
|name| 模块名称  | body | false |string  |    |
|page|   | body | false |int32  |    |
|showUrl| 模块文档展示静态文档地址  | body | false |string  |    |

**响应数据**:

```json
{
	"datas": {},
	"resp_code": 0,
	"resp_msg": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|datas|   |object  |    |
|resp_code|   |int32  |    |
|resp_msg|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# basic-error-controller

## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`GET`


**consumes**:``


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`PUT`


**consumes**:`["application/json"]`


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`PATCH`


**consumes**:`["application/json"]`


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`OPTIONS`


**consumes**:`["application/json"]`


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## errorHtml


**接口说明**:



**接口地址**:`/error`


**请求方式**：`HEAD`


**consumes**:`["application/json"]`


**produces**:`["text/html"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json
{
	"empty": true,
	"model": {},
	"modelMap": {},
	"reference": true,
	"status": "",
	"view": {
		"contentType": ""
	},
	"viewName": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|empty|   |boolean  |    |
|model|   |object  |    |
|modelMap|   |object  |    |
|reference|   |boolean  |    |
|status|   |string  |    |
|view|   |View  | View   |
|viewName|   |string  |    |



**schema属性说明**




**View**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|contentType |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ModelAndView|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
# operation-handler
    

## handle


**接口说明**:



**接口地址**:`/actuator/archaius`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/auditevents`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/beans`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/caches`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/caches`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## handle


**接口说明**:



**接口地址**:`/actuator/caches/{cache}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/caches/{cache}`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## handle


**接口说明**:



**接口地址**:`/actuator/conditions`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/configprops`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/env`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/env`


**请求方式**：`POST`


**consumes**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/env`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## handle


**接口说明**:



**接口地址**:`/actuator/env/{toMatch}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/features`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/health`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/health/{component}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/health/{component}/{instance}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/heapdump`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/octet-stream"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/httptrace`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/info`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/loggers`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/loggers/{name}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/loggers/{name}`


**请求方式**：`POST`


**consumes**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/mappings`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/metrics`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/metrics/{requiredMetricName}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/prometheus`


**请求方式**：`GET`


**consumes**:``


**produces**:`["text/plain;version=0.0.4;charset=utf-8"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/refresh`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/scheduledtasks`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/service-registry`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/service-registry`


**请求方式**：`POST`


**consumes**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## handle


**接口说明**:



**接口地址**:`/actuator/threaddump`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |
|body| body  | body | false |  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# web-mvc-links-handler

## links


**接口说明**:



**接口地址**:`/actuator`


**请求方式**：`GET`


**consumes**:``


**produces**:`["application/json","application/vnd.spring-boot.actuator.v2+json"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|Authorization| 令牌  | header | false |string  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||


