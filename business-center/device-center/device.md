
**Api Documentation**


**简介**：Api Documentation

**HOST**:192.168.124.20:7001


**联系人**:


**Version**:1.0

**接口路径**：/v2/api-docs


# basic-error-controller

## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`PUT`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`PATCH`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`OPTIONS`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## error


**接口说明**:



**接口地址**:`/error`


**请求方式**：`HEAD`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



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
# device-api-controller

## apCallback


**接口说明**:



**接口地址**:`/alarmpush/callback`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"alarmcode": "",
	"alarmid": "",
	"alarmlevel": 0,
	"alarmtime": "",
	"cleartime": "",
	"confirmer": "",
	"confirmstate": 0,
	"confirmtime": "",
	"descp": "",
	"deveui": "",
	"devtype": "",
	"remark": "",
	"title": "",
	"typeflag": 0
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|alarmpush| alarmpush  | body | true |Alarmpush  | Alarmpush   |

**schema属性说明**



**Alarmpush**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|alarmcode|   | body | false |string  |    |
|alarmid|   | body | false |string  |    |
|alarmlevel|   | body | false |int32  |    |
|alarmtime|   | body | false |string  |    |
|cleartime|   | body | false |string  |    |
|confirmer|   | body | false |string  |    |
|confirmstate|   | body | false |int32  |    |
|confirmtime|   | body | false |string  |    |
|descp|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devtype|   | body | false |string  |    |
|remark|   | body | false |string  |    |
|title|   | body | false |string  |    |
|typeflag|   | body | false |int32  |    |

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
## apiQuery


**接口说明**:



**接口地址**:`/api/query`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"end": 0,
	"queries": [
		{
			"aggregator": "",
			"downsample": "",
			"metric": "",
			"tags": {
				"company": "",
				"host": "",
				"index": "",
				"location": ""
			}
		}
	],
	"start": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|requestModel| requestModel  | body | true |RequestModel  | RequestModel   |

**schema属性说明**



**RequestModel**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|end|   | body | false |int64  |    |
|queries|   | body | false |array  | Querie   |
|start|   | body | false |string  |    |

**Querie**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|aggregator|   | body | false |string  |    |
|downsample|   | body | false |string  |    |
|metric|   | body | false |string  |    |
|tags|   | body | false |Tags  | Tags   |

**Tags**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|company|   | body | false |string  |    |
|host|   | body | false |string  |    |
|index|   | body | false |string  |    |
|location|   | body | false |string  |    |

**响应数据**:

```json
[
	{
		"aggregateTags": [],
		"dps": {},
		"metric": "",
		"tags": {
			"company": "",
			"host": "",
			"index": "",
			"location": ""
		}
	}
]
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|aggregateTags|   |array  |    |
|dps|   |object  |    |
|metric|   |string  |    |
|tags|   |Tags  | Tags   |



**schema属性说明**




**Tags**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|company |    |string  |    |
|host |    |string  |    |
|index |    |string  |    |
|location |    |string  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |ResposeModel|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## dpCallback


**接口说明**:



**接口地址**:`/datapush/callback`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"datarows": [
		{
			"collecttime": "",
			"deveui": "",
			"humdity": 0,
			"pressure": 0,
			"tempature": 0
		}
	],
	"devtype": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|datapush| datapush  | body | true |Datapush  | Datapush   |

**schema属性说明**



**Datapush**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|datarows|   | body | false |array  | BusinessData   |
|devtype|   | body | false |string  |    |

**BusinessData**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|collecttime|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|humdity|   | body | false |double  |    |
|pressure|   | body | false |double  |    |
|tempature|   | body | false |double  |    |

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
## dspCallback


**接口说明**:



**接口地址**:`/devstatepush/callback`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"deveui": "",
	"rpttime": "",
	"state": 0
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devstatepush| devstatepush  | body | true |Devstatepush  | Devstatepush   |

**schema属性说明**



**Devstatepush**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deveui|   | body | false |string  |    |
|rpttime|   | body | false |string  |    |
|state|   | body | false |int32  |    |

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
## getDeviceList


**接口说明**:



**接口地址**:`/getDeviceInfo`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## reportData


**接口说明**:



**接口地址**:`/reportdata`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|host| host  | query | false |string  |    |
|limit| limit  | query | false |integer  |    |
|page| page  | query | false |integer  |    |
|start| start  | query | false |string  |    |
|typeId| typeId  | query | true |integer  |    |

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
## test


**接口说明**:



**接口地址**:`/test`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
## test


**接口说明**:



**接口地址**:`/test1`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# device-upgrade-controller

## end


**接口说明**:



**接口地址**:`/mqtt/end`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应数据**:

```json
{
	"data": {},
	"description": "",
	"status": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |object  |    |
|description|   |string  |    |
|status|   |int32  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |HResponse|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getver


**接口说明**:



**接口地址**:`/mqtt/getver`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应数据**:

```json
{
	"data": {},
	"description": "",
	"status": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |object  |    |
|description|   |string  |    |
|status|   |int32  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |HResponse|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## upd


**接口说明**:



**接口地址**:`/mqtt/upd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应数据**:

```json
{
	"data": {},
	"description": "",
	"status": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |object  |    |
|description|   |string  |    |
|status|   |int32  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |HResponse|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## upgrade


**接口说明**:



**接口地址**:`/mqtt/upgrade`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|file| file  | formData | false |file  |    |

**响应数据**:

```json
{
	"data": {},
	"description": "",
	"status": 0
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |object  |    |
|description|   |string  |    |
|status|   |int32  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |HResponse|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# infi-combo-api-controller

## getDevcuralarm


**接口说明**:



**接口地址**:`/infiCombo/alarm/devcuralarm`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deveuis| deveuis  | query | true |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getDevhisalarm


**接口说明**:



**接口地址**:`/infiCombo/alarm/devhisalarm`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|begintime| begintime  | query | true |string  |    |
|deveui| deveui  | query | true |string  |    |
|endtime| endtime  | query | true |string  |    |
|pageindex| pageindex  | query | false |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getlatestalarms


**接口说明**:



**接口地址**:`/infiCombo/alarm/getlatestalarms`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|maxnum| maxnum  | query | true |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getHisalarmlist


**接口说明**:



**接口地址**:`/infiCombo/alarm/hisalarmlist`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|begintime| begintime  | query | true |string  |    |
|endtime| endtime  | query | true |string  |    |
|pageindex| pageindex  | query | false |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## alarmRegiste


**接口说明**:



**接口地址**:`/infiCombo/alarmpush/registe`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## alarmRegistelist


**接口说明**:



**接口地址**:`/infiCombo/alarmpush/registelist`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## alarmUnregiste


**接口说明**:



**接口地址**:`/infiCombo/alarmpush/unregiste`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## getBatchdevdata


**接口说明**:



**接口地址**:`/infiCombo/data/batchdevdata`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|begintime| begintime  | query | true |string  |    |
|deveuis| deveuis  | query | true |string  |    |
|endtime| endtime  | query | true |string  |    |
|pageindex| pageindex  | query | false |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getDevdata


**接口说明**:



**接口地址**:`/infiCombo/data/devdata`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|begintime| begintime  | query | true |string  |    |
|deveui| deveui  | query | true |string  |    |
|endtime| endtime  | query | true |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getDevstatedata


**接口说明**:



**接口地址**:`/infiCombo/data/devstatedata`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deveuis| deveuis  | query | true |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getLatestdevdata


**接口说明**:



**接口地址**:`/infiCombo/data/latestdevdata`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deveuis| deveuis  | query | true |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## dataRegiste


**接口说明**:



**接口地址**:`/infiCombo/datapush/registe`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## getDataRegistelist


**接口说明**:



**接口地址**:`/infiCombo/datapush/registelist`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## dataUnregiste


**接口说明**:



**接口地址**:`/infiCombo/datapush/unregiste`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## getDevs


**接口说明**:



**接口地址**:`/infiCombo/dev/devs`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|batch| batch  | query | false |integer  |    |
|devtype| devtype  | query | false |string  |    |
|pageindex| pageindex  | query | false |integer  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getDevtypes


**接口说明**:



**接口地址**:`/infiCombo/dev/devtypes`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getProjectdevtypes


**接口说明**:



**接口地址**:`/infiCombo/dev/projectdevtypes`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## broadcastcmd


**接口说明**:



**接口地址**:`/infiCombo/devcontrol/broadcastcmd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## devcontrolCmd


**接口说明**:



**接口地址**:`/infiCombo/devcontrol/cmd`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## devRegiste


**接口说明**:



**接口地址**:`/infiCombo/devstatepush/registe`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## devRegistelist


**接口说明**:



**接口地址**:`/infiCombo/devstatepush/registelist`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## devUnregiste


**接口说明**:



**接口地址**:`/infiCombo/devstatepush/unregiste`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"appeui": "",
	"bctimes": 0,
	"command": {},
	"confirm": true,
	"desturl": "",
	"deveui": "",
	"devport": 0,
	"devtype": "",
	"token": ""
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devcontrol| devcontrol  | body | true |Devcontrol  | Devcontrol   |

**schema属性说明**



**Devcontrol**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appeui|   | body | false |string  |    |
|bctimes|   | body | false |int32  |    |
|command|   | body | false |object  |    |
|confirm|   | body | false |boolean  |    |
|desturl|   | body | false |string  |    |
|deveui|   | body | false |string  |    |
|devport|   | body | false |int32  |    |
|devtype|   | body | false |string  |    |
|token|   | body | false |string  |    |

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
## getToken


**接口说明**:



**接口地址**:`/infiCombo/get/token`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|pswd| pswd  | query | true |string  |    |
|username| username  | query | true |string  |    |

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
## getAlarmmodel


**接口说明**:



**接口地址**:`/infiCombo/model/alarmmodel`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devtype| devtype  | query | false |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getControlmodel


**接口说明**:



**接口地址**:`/infiCombo/model/controlmodel`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devtype| devtype  | query | false |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## getDatamodel


**接口说明**:



**接口地址**:`/infiCombo/model/datamodel`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|devtype| devtype  | query | false |string  |    |

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
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## receiveIOT


**接口说明**:



**接口地址**:`/infiCombo/zzkj/iot`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"datarows": [
		{
			"collecttime": 0,
			"deveui": "",
			"map": {}
		}
	],
	"devtype": "",
	"online": true
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|genMessage| genMessage  | body | true |GenMessage  | GenMessage   |

**schema属性说明**



**GenMessage**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|datarows|   | body | false |array  | Datarows   |
|devtype|   | body | false |string  |    |
|online|   | body | false |boolean  |    |

**Datarows**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|collecttime|   | body | false |int64  |    |
|deveui|   | body | false |string  |    |
|map|   | body | false |object  |    |

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



**接口地址**:`/actuator/integrationgraph`


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



**接口地址**:`/actuator/integrationgraph`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
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
暂无



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
# 设备信息

## 地图点位信息


**接口说明**:



**接口地址**:`/deviceinfo/Point`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应数据**:

```json
{
	"features": [
		{
			"geometry": {
				"coordinates": [],
				"type": ""
			},
			"properties": {
				"createBy": "",
				"createTime": "",
				"devceTypeId": 0,
				"deviceInstallInfo": {
					"createTime": "",
					"deviceId": 0,
					"deviceSn": "",
					"id": 0,
					"installAddress": "",
					"installDate": "",
					"latitude": 0,
					"longitude": 0,
					"picUrl": "",
					"updateTime": ""
				},
				"deviceManufacturer": {
					"address": "",
					"contacts": "",
					"createBy": "",
					"createTime": "",
					"description": "",
					"id": 0,
					"name": "",
					"phone": "",
					"remark": "",
					"updateBy": "",
					"updateTime": "",
					"website": ""
				},
				"devicePower": {
					"deviceId": 0,
					"fullCharge": 0,
					"id": 0,
					"standardVoltage": 0,
					"stateOfCharge": 0,
					"updateTime": "",
					"voltage": 0
				},
				"deviceSn": "",
				"deviceStatus": {
					"deviceId": 0,
					"id": 0,
					"remarks": "",
					"status": 0,
					"updateTime": ""
				},
				"deviceTypeName": "",
				"deviceValue": {},
				"equipmentLife": 0,
				"generationDate": "",
				"id": 0,
				"isIotDevice": 0,
				"manufacturerId": 0,
				"model": "",
				"name": "",
				"powerSupplyMode": 0,
				"remark": "",
				"status": 0,
				"style": {
					"createTime": "",
					"id": 0,
					"image": "",
					"name": "",
					"type": 0
				},
				"updateBy": "",
				"updateTime": ""
			},
			"type": ""
		}
	],
	"type": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|features|   |array  | Features   |
|type|   |string  |    |



**schema属性说明**




**Features**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|geometry |    |Geometry  | Geometry   |
|properties |    |device_info  | device_info   |
|type |    |string  |    |

**Geometry**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|coordinates |    |array  |    |
|type |    |string  |    |

**device_info**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createBy | 创建者   |string  |    |
|createTime | 创建时间   |string  |    |
|devceTypeId | 设备类型   |int32  |    |
|deviceInstallInfo | 备注安裝信息   |device_install_info  | device_install_info   |
|deviceManufacturer | 备注出場信息   |device_manufacturer  | device_manufacturer   |
|devicePower | 备注電池信息   |device_power  | device_power   |
|deviceSn | 设备编码   |string  |    |
|deviceStatus | 备注狀態   |device_status  | device_status   |
|deviceTypeName |    |string  |    |
|deviceValue |    |object  |    |
|equipmentLife | 使用年限   |float  |    |
|generationDate | 生产日期   |string  |    |
|id |    |int32  |    |
|isIotDevice | 是否物联网设备   |int32  |    |
|manufacturerId | 设备厂家   |int32  |    |
|model | 设备型号   |string  |    |
|name | 设备名称   |string  |    |
|powerSupplyMode | 供电方式   |int32  |    |
|remark | 备注信息   |string  |    |
|status |    |int32  |    |
|style | 备注類型   |device_type  | device_type   |
|updateBy | 更新者   |string  |    |
|updateTime | 更新时间   |string  |    |

**device_install_info**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间   |string  |    |
|deviceId | 设备ID   |int32  |    |
|deviceSn | 设备编码   |string  |    |
|id |    |int32  |    |
|installAddress | 安装地址   |string  |    |
|installDate | 安装日期   |string  |    |
|latitude | 纬度   |number  |    |
|longitude | 经度   |number  |    |
|picUrl | 图片   |string  |    |
|updateTime | 更新时间   |string  |    |

**device_manufacturer**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|address | 地址   |string  |    |
|contacts | 联系人   |string  |    |
|createBy | 创建者   |string  |    |
|createTime | 创建时间   |string  |    |
|description | 厂家介绍   |string  |    |
|id |    |int32  |    |
|name | 厂家名称   |string  |    |
|phone | 联系电话   |string  |    |
|remark | 备注信息   |string  |    |
|updateBy | 更新者   |string  |    |
|updateTime | 更新时间   |string  |    |
|website | 网站   |string  |    |

**device_power**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|deviceId | 設備id   |int32  |    |
|fullCharge | 电池容量   |int32  |    |
|id |    |int32  |    |
|standardVoltage | 电池额定电压   |int32  |    |
|stateOfCharge | 剩余电量   |float  |    |
|updateTime | 更新时间   |date-time  |    |
|voltage | 当前电压   |int32  |    |

**device_status**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|deviceId | 設備id   |int32  |    |
|id |    |int32  |    |
|remarks | 备注   |string  |    |
|status | 设备状态   |int32  |    |
|updateTime | 更新时间   |date-time  |    |

**device_type**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间   |date-time  |    |
|id |    |int32  |    |
|image | 设备点位类型图   |string  |    |
|name | 类型名称   |string  |    |
|type | 类型   |int32  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Point|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 删除设备信息


**接口说明**:



**接口地址**:`/deviceinfo/deleteDeviceInfoById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 根据id删除  | body | true |integer  |    |

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
## 根据appid权限和deviceSn查询基础信息接口


**接口说明**:



**接口地址**:`/deviceinfo/deviceBasicBySn/{deviceSn}/{appid}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | path | true |integer  |    |
|deviceSn| 根据deviceSn查询基础信息参数  | path | true |string  |    |

**响应数据**:

```json
{
	"createBy": "",
	"createTime": "",
	"devceTypeId": 0,
	"deviceInstallInfo": {
		"createTime": "",
		"deviceId": 0,
		"deviceSn": "",
		"id": 0,
		"installAddress": "",
		"installDate": "",
		"latitude": 0,
		"longitude": 0,
		"picUrl": "",
		"updateTime": ""
	},
	"deviceManufacturer": {
		"address": "",
		"contacts": "",
		"createBy": "",
		"createTime": "",
		"description": "",
		"id": 0,
		"name": "",
		"phone": "",
		"remark": "",
		"updateBy": "",
		"updateTime": "",
		"website": ""
	},
	"devicePower": {
		"deviceId": 0,
		"fullCharge": 0,
		"id": 0,
		"standardVoltage": 0,
		"stateOfCharge": 0,
		"updateTime": "",
		"voltage": 0
	},
	"deviceSn": "",
	"deviceStatus": {
		"deviceId": 0,
		"id": 0,
		"remarks": "",
		"status": 0,
		"updateTime": ""
	},
	"deviceTypeName": "",
	"deviceValue": {},
	"equipmentLife": 0,
	"generationDate": "",
	"id": 0,
	"isIotDevice": 0,
	"manufacturerId": 0,
	"model": "",
	"name": "",
	"powerSupplyMode": 0,
	"remark": "",
	"status": 0,
	"style": {
		"createTime": "",
		"id": 0,
		"image": "",
		"name": "",
		"type": 0
	},
	"updateBy": "",
	"updateTime": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createBy| 创建者  |string  |    |
|createTime| 创建时间  |string  |    |
|devceTypeId| 设备类型  |int32  |    |
|deviceInstallInfo| 备注安裝信息  |device_install_info  | device_install_info   |
|deviceManufacturer| 备注出場信息  |device_manufacturer  | device_manufacturer   |
|devicePower| 备注電池信息  |device_power  | device_power   |
|deviceSn| 设备编码  |string  |    |
|deviceStatus| 备注狀態  |device_status  | device_status   |
|deviceTypeName|   |string  |    |
|deviceValue|   |object  |    |
|equipmentLife| 使用年限  |float  |    |
|generationDate| 生产日期  |string  |    |
|id|   |int32  |    |
|isIotDevice| 是否物联网设备  |int32  |    |
|manufacturerId| 设备厂家  |int32  |    |
|model| 设备型号  |string  |    |
|name| 设备名称  |string  |    |
|powerSupplyMode| 供电方式  |int32  |    |
|remark| 备注信息  |string  |    |
|status|   |int32  |    |
|style| 备注類型  |device_type  | device_type   |
|updateBy| 更新者  |string  |    |
|updateTime| 更新时间  |string  |    |



**schema属性说明**




**device_install_info**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间   |string  |    |
|deviceId | 设备ID   |int32  |    |
|deviceSn | 设备编码   |string  |    |
|id |    |int32  |    |
|installAddress | 安装地址   |string  |    |
|installDate | 安装日期   |string  |    |
|latitude | 纬度   |number  |    |
|longitude | 经度   |number  |    |
|picUrl | 图片   |string  |    |
|updateTime | 更新时间   |string  |    |

**device_manufacturer**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|address | 地址   |string  |    |
|contacts | 联系人   |string  |    |
|createBy | 创建者   |string  |    |
|createTime | 创建时间   |string  |    |
|description | 厂家介绍   |string  |    |
|id |    |int32  |    |
|name | 厂家名称   |string  |    |
|phone | 联系电话   |string  |    |
|remark | 备注信息   |string  |    |
|updateBy | 更新者   |string  |    |
|updateTime | 更新时间   |string  |    |
|website | 网站   |string  |    |

**device_power**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|deviceId | 設備id   |int32  |    |
|fullCharge | 电池容量   |int32  |    |
|id |    |int32  |    |
|standardVoltage | 电池额定电压   |int32  |    |
|stateOfCharge | 剩余电量   |float  |    |
|updateTime | 更新时间   |date-time  |    |
|voltage | 当前电压   |int32  |    |

**device_status**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|deviceId | 設備id   |int32  |    |
|id |    |int32  |    |
|remarks | 备注   |string  |    |
|status | 设备状态   |int32  |    |
|updateTime | 更新时间   |date-time  |    |

**device_type**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间   |date-time  |    |
|id |    |int32  |    |
|image | 设备点位类型图   |string  |    |
|name | 类型名称   |string  |    |
|type | 类型   |int32  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |device_info|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 根据appid权限，查询设备基础信息列表接口


**接口说明**:



**接口地址**:`/deviceinfo/deviceBasicList/{appid}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | path | true |integer  |    |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 查询设备信息列表


**接口说明**:



**接口地址**:`/deviceinfo/deviceInfoList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 根据appid权限和deviceSn查询状态信息接口


**接口说明**:



**接口地址**:`/deviceinfo/deviceStatusBySn/{deviceSn}/{appid}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | path | true |integer  |    |
|deviceSn| 根据deviceSn查询状态信息参数  | path | true |string  |    |

**响应数据**:

```json
{
	"createBy": "",
	"createTime": "",
	"devceTypeId": 0,
	"deviceInstallInfo": {
		"createTime": "",
		"deviceId": 0,
		"deviceSn": "",
		"id": 0,
		"installAddress": "",
		"installDate": "",
		"latitude": 0,
		"longitude": 0,
		"picUrl": "",
		"updateTime": ""
	},
	"deviceManufacturer": {
		"address": "",
		"contacts": "",
		"createBy": "",
		"createTime": "",
		"description": "",
		"id": 0,
		"name": "",
		"phone": "",
		"remark": "",
		"updateBy": "",
		"updateTime": "",
		"website": ""
	},
	"devicePower": {
		"deviceId": 0,
		"fullCharge": 0,
		"id": 0,
		"standardVoltage": 0,
		"stateOfCharge": 0,
		"updateTime": "",
		"voltage": 0
	},
	"deviceSn": "",
	"deviceStatus": {
		"deviceId": 0,
		"id": 0,
		"remarks": "",
		"status": 0,
		"updateTime": ""
	},
	"deviceTypeName": "",
	"deviceValue": {},
	"equipmentLife": 0,
	"generationDate": "",
	"id": 0,
	"isIotDevice": 0,
	"manufacturerId": 0,
	"model": "",
	"name": "",
	"powerSupplyMode": 0,
	"remark": "",
	"status": 0,
	"style": {
		"createTime": "",
		"id": 0,
		"image": "",
		"name": "",
		"type": 0
	},
	"updateBy": "",
	"updateTime": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createBy| 创建者  |string  |    |
|createTime| 创建时间  |string  |    |
|devceTypeId| 设备类型  |int32  |    |
|deviceInstallInfo| 备注安裝信息  |device_install_info  | device_install_info   |
|deviceManufacturer| 备注出場信息  |device_manufacturer  | device_manufacturer   |
|devicePower| 备注電池信息  |device_power  | device_power   |
|deviceSn| 设备编码  |string  |    |
|deviceStatus| 备注狀態  |device_status  | device_status   |
|deviceTypeName|   |string  |    |
|deviceValue|   |object  |    |
|equipmentLife| 使用年限  |float  |    |
|generationDate| 生产日期  |string  |    |
|id|   |int32  |    |
|isIotDevice| 是否物联网设备  |int32  |    |
|manufacturerId| 设备厂家  |int32  |    |
|model| 设备型号  |string  |    |
|name| 设备名称  |string  |    |
|powerSupplyMode| 供电方式  |int32  |    |
|remark| 备注信息  |string  |    |
|status|   |int32  |    |
|style| 备注類型  |device_type  | device_type   |
|updateBy| 更新者  |string  |    |
|updateTime| 更新时间  |string  |    |



**schema属性说明**




**device_install_info**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间   |string  |    |
|deviceId | 设备ID   |int32  |    |
|deviceSn | 设备编码   |string  |    |
|id |    |int32  |    |
|installAddress | 安装地址   |string  |    |
|installDate | 安装日期   |string  |    |
|latitude | 纬度   |number  |    |
|longitude | 经度   |number  |    |
|picUrl | 图片   |string  |    |
|updateTime | 更新时间   |string  |    |

**device_manufacturer**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|address | 地址   |string  |    |
|contacts | 联系人   |string  |    |
|createBy | 创建者   |string  |    |
|createTime | 创建时间   |string  |    |
|description | 厂家介绍   |string  |    |
|id |    |int32  |    |
|name | 厂家名称   |string  |    |
|phone | 联系电话   |string  |    |
|remark | 备注信息   |string  |    |
|updateBy | 更新者   |string  |    |
|updateTime | 更新时间   |string  |    |
|website | 网站   |string  |    |

**device_power**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|deviceId | 設備id   |int32  |    |
|fullCharge | 电池容量   |int32  |    |
|id |    |int32  |    |
|standardVoltage | 电池额定电压   |int32  |    |
|stateOfCharge | 剩余电量   |float  |    |
|updateTime | 更新时间   |date-time  |    |
|voltage | 当前电压   |int32  |    |

**device_status**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|deviceId | 設備id   |int32  |    |
|id |    |int32  |    |
|remarks | 备注   |string  |    |
|status | 设备状态   |int32  |    |
|updateTime | 更新时间   |date-time  |    |

**device_type**

| 参数名称         |  说明          |   类型  |  schema |
| ------------ | ------------------|--------|----------- |
|createTime | 创建时间   |date-time  |    |
|id |    |int32  |    |
|image | 设备点位类型图   |string  |    |
|name | 类型名称   |string  |    |
|type | 类型   |int32  |    |

**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |device_info|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 根据appid权限，查询设备状态信息列表接口


**接口说明**:



**接口地址**:`/deviceinfo/deviceStatusList/{appid}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | path | true |integer  |    |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 数据导出


**接口说明**:



**接口地址**:`/deviceinfo/export`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|params| params  | query | false |object  |    |

**响应数据**:

```json

```

**响应参数说明**:


暂无





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |HttpServletResponse|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 保存设备信息


**接口说明**:



**接口地址**:`/deviceinfo/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createBy| 创建者  | query | false |string  |    |
|createTime| 创建时间  | query | false |string  |    |
|devceTypeId| 设备类型  | query | false |integer  |    |
|deviceInstallInfo.createTime| 创建时间  | query | false |string  |    |
|deviceInstallInfo.deviceId| 设备ID  | query | false |integer  |    |
|deviceInstallInfo.deviceSn| 设备编码  | query | false |string  |    |
|deviceInstallInfo.id|   | query | false |integer  |    |
|deviceInstallInfo.installAddress| 安装地址  | query | false |string  |    |
|deviceInstallInfo.installDate| 安装日期  | query | false |string  |    |
|deviceInstallInfo.latitude| 纬度  | query | false |number  |    |
|deviceInstallInfo.longitude| 经度  | query | false |number  |    |
|deviceInstallInfo.picUrl| 图片  | query | false |string  |    |
|deviceInstallInfo.updateTime| 更新时间  | query | false |string  |    |
|deviceManufacturer.address| 地址  | query | false |string  |    |
|deviceManufacturer.contacts| 联系人  | query | false |string  |    |
|deviceManufacturer.createBy| 创建者  | query | false |string  |    |
|deviceManufacturer.createTime| 创建时间  | query | false |string  |    |
|deviceManufacturer.description| 厂家介绍  | query | false |string  |    |
|deviceManufacturer.id|   | query | false |integer  |    |
|deviceManufacturer.name| 厂家名称  | query | false |string  |    |
|deviceManufacturer.phone| 联系电话  | query | false |string  |    |
|deviceManufacturer.remark| 备注信息  | query | false |string  |    |
|deviceManufacturer.updateBy| 更新者  | query | false |string  |    |
|deviceManufacturer.updateTime| 更新时间  | query | false |string  |    |
|deviceManufacturer.website| 网站  | query | false |string  |    |
|devicePower.deviceId| 設備id  | query | false |integer  |    |
|devicePower.fullCharge| 电池容量  | query | false |integer  |    |
|devicePower.id|   | query | false |integer  |    |
|devicePower.standardVoltage| 电池额定电压  | query | false |integer  |    |
|devicePower.stateOfCharge| 剩余电量  | query | false |number  |    |
|devicePower.updateTime| 更新时间  | query | false |string  |    |
|devicePower.voltage| 当前电压  | query | false |integer  |    |
|deviceSn| 设备编码  | query | false |string  |    |
|deviceStatus.deviceId| 設備id  | query | false |integer  |    |
|deviceStatus.id|   | query | false |integer  |    |
|deviceStatus.remarks| 备注  | query | false |string  |    |
|deviceStatus.status| 设备状态  | query | false |integer  |    |
|deviceStatus.updateTime| 更新时间  | query | false |string  |    |
|deviceTypeName|   | query | false |string  |    |
|deviceValue|   | query | false |object  |    |
|equipmentLife| 使用年限  | query | false |number  |    |
|generationDate| 生产日期  | query | false |string  |    |
|id|   | query | false |integer  |    |
|isIotDevice| 是否物联网设备  | query | false |integer  |    |
|manufacturerId| 设备厂家  | query | false |integer  |    |
|model| 设备型号  | query | false |string  |    |
|name| 设备名称  | query | false |string  |    |
|powerSupplyMode| 供电方式  | query | false |integer  |    |
|remark| 备注信息  | query | false |string  |    |
|status|   | query | false |integer  |    |
|style.createTime| 创建时间  | query | false |string  |    |
|style.id|   | query | false |integer  |    |
|style.image| 设备点位类型图  | query | false |string  |    |
|style.name| 类型名称  | query | false |string  |    |
|style.type| 类型  | query | false |integer  |    |
|updateBy| 更新者  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
## 数据导入


**接口说明**:



**接口地址**:`/deviceinfo/upLoadExcel`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
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
## 修改设备信息


**接口说明**:



**接口地址**:`/deviceinfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createBy| 创建者  | query | false |string  |    |
|createTime| 创建时间  | query | false |string  |    |
|devceTypeId| 设备类型  | query | false |integer  |    |
|deviceInstallInfo.createTime| 创建时间  | query | false |string  |    |
|deviceInstallInfo.deviceId| 设备ID  | query | false |integer  |    |
|deviceInstallInfo.deviceSn| 设备编码  | query | false |string  |    |
|deviceInstallInfo.id|   | query | false |integer  |    |
|deviceInstallInfo.installAddress| 安装地址  | query | false |string  |    |
|deviceInstallInfo.installDate| 安装日期  | query | false |string  |    |
|deviceInstallInfo.latitude| 纬度  | query | false |number  |    |
|deviceInstallInfo.longitude| 经度  | query | false |number  |    |
|deviceInstallInfo.picUrl| 图片  | query | false |string  |    |
|deviceInstallInfo.updateTime| 更新时间  | query | false |string  |    |
|deviceManufacturer.address| 地址  | query | false |string  |    |
|deviceManufacturer.contacts| 联系人  | query | false |string  |    |
|deviceManufacturer.createBy| 创建者  | query | false |string  |    |
|deviceManufacturer.createTime| 创建时间  | query | false |string  |    |
|deviceManufacturer.description| 厂家介绍  | query | false |string  |    |
|deviceManufacturer.id|   | query | false |integer  |    |
|deviceManufacturer.name| 厂家名称  | query | false |string  |    |
|deviceManufacturer.phone| 联系电话  | query | false |string  |    |
|deviceManufacturer.remark| 备注信息  | query | false |string  |    |
|deviceManufacturer.updateBy| 更新者  | query | false |string  |    |
|deviceManufacturer.updateTime| 更新时间  | query | false |string  |    |
|deviceManufacturer.website| 网站  | query | false |string  |    |
|devicePower.deviceId| 設備id  | query | false |integer  |    |
|devicePower.fullCharge| 电池容量  | query | false |integer  |    |
|devicePower.id|   | query | false |integer  |    |
|devicePower.standardVoltage| 电池额定电压  | query | false |integer  |    |
|devicePower.stateOfCharge| 剩余电量  | query | false |number  |    |
|devicePower.updateTime| 更新时间  | query | false |string  |    |
|devicePower.voltage| 当前电压  | query | false |integer  |    |
|deviceSn| 设备编码  | query | false |string  |    |
|deviceStatus.deviceId| 設備id  | query | false |integer  |    |
|deviceStatus.id|   | query | false |integer  |    |
|deviceStatus.remarks| 备注  | query | false |string  |    |
|deviceStatus.status| 设备状态  | query | false |integer  |    |
|deviceStatus.updateTime| 更新时间  | query | false |string  |    |
|deviceTypeName|   | query | false |string  |    |
|deviceValue|   | query | false |object  |    |
|equipmentLife| 使用年限  | query | false |number  |    |
|generationDate| 生产日期  | query | false |string  |    |
|id|   | query | false |integer  |    |
|isIotDevice| 是否物联网设备  | query | false |integer  |    |
|manufacturerId| 设备厂家  | query | false |integer  |    |
|model| 设备型号  | query | false |string  |    |
|name| 设备名称  | query | false |string  |    |
|powerSupplyMode| 供电方式  | query | false |integer  |    |
|remark| 备注信息  | query | false |string  |    |
|status|   | query | false |integer  |    |
|style.createTime| 创建时间  | query | false |string  |    |
|style.id|   | query | false |integer  |    |
|style.image| 设备点位类型图  | query | false |string  |    |
|style.name| 类型名称  | query | false |string  |    |
|style.type| 类型  | query | false |integer  |    |
|updateBy| 更新者  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
# 设备厂家信息

## 根据ID删除设备厂家信息


**接口说明**:



**接口地址**:`/devicemanufacturer/deleteDeviceManufacturerById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 根据id删除  | body | true |integer  |    |

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
## 厂家信息列表


**接口说明**:



**接口地址**:`/devicemanufacturer/deviceManufacturerList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 保存厂家信息


**接口说明**:



**接口地址**:`/devicemanufacturer/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|address| 地址  | query | false |string  |    |
|contacts| 联系人  | query | false |string  |    |
|createBy| 创建者  | query | false |string  |    |
|createTime| 创建时间  | query | false |string  |    |
|description| 厂家介绍  | query | false |string  |    |
|id|   | query | false |integer  |    |
|name| 厂家名称  | query | false |string  |    |
|phone| 联系电话  | query | false |string  |    |
|remark| 备注信息  | query | false |string  |    |
|updateBy| 更新者  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |
|website| 网站  | query | false |string  |    |

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
## 修改厂家信息


**接口说明**:



**接口地址**:`/devicemanufacturer/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|address| 地址  | query | false |string  |    |
|contacts| 联系人  | query | false |string  |    |
|createBy| 创建者  | query | false |string  |    |
|createTime| 创建时间  | query | false |string  |    |
|description| 厂家介绍  | query | false |string  |    |
|id|   | query | false |integer  |    |
|name| 厂家名称  | query | false |string  |    |
|phone| 联系电话  | query | false |string  |    |
|remark| 备注信息  | query | false |string  |    |
|updateBy| 更新者  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |
|website| 网站  | query | false |string  |    |

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
# 设备告警信息

## 根据id删除设备告警信息


**接口说明**:



**接口地址**:`/devicewarning/deleteDeviceWarningById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | query | false |integer  |    |

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
## 设备告警信息列表


**接口说明**:



**接口地址**:`/devicewarning/deviceWarningList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 保存设备告警信息


**接口说明**:



**接口地址**:`/devicewarning/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|content| 告警详情  | query | false |string  |    |
|createTime| 创建时间  | query | false |string  |    |
|deviceId| 类型id  | query | false |integer  |    |
|id|   | query | false |integer  |    |
|warningType| 告警类型  | query | false |integer  |    |

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
## 修改设备告警信息


**接口说明**:



**接口地址**:`/devicewarning/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|content| 告警详情  | query | false |string  |    |
|createTime| 创建时间  | query | false |string  |    |
|deviceId| 类型id  | query | false |integer  |    |
|id|   | query | false |integer  |    |
|warningType| 告警类型  | query | false |integer  |    |

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
# 设备安装信息

## 设备安装信息列表


**接口说明**:



**接口地址**:`/deviceinstallinfo/DeviceInstallInfoList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 根据id删除设备安装信息


**接口说明**:



**接口地址**:`/deviceinstallinfo/deleteDeviceInstallInfoById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 根据id删除  | body | true |integer  |    |

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
## 保存设备安装信息


**接口说明**:



**接口地址**:`/deviceinstallinfo/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | query | false |string  |    |
|deviceId| 设备ID  | query | false |integer  |    |
|deviceSn| 设备编码  | query | false |string  |    |
|id|   | query | false |integer  |    |
|installAddress| 安装地址  | query | false |string  |    |
|installDate| 安装日期  | query | false |string  |    |
|latitude| 纬度  | query | false |number  |    |
|longitude| 经度  | query | false |number  |    |
|picUrl| 图片  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
## 更新或修改设备安装信息


**接口说明**:



**接口地址**:`/deviceinstallinfo/saveOrUpdate`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | query | false |string  |    |
|deviceId| 设备ID  | query | false |integer  |    |
|deviceSn| 设备编码  | query | false |string  |    |
|id|   | query | false |integer  |    |
|installAddress| 安装地址  | query | false |string  |    |
|installDate| 安装日期  | query | false |string  |    |
|latitude| 纬度  | query | false |number  |    |
|longitude| 经度  | query | false |number  |    |
|picUrl| 图片  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
## 修改设备安装信息


**接口说明**:



**接口地址**:`/deviceinstallinfo/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | query | false |string  |    |
|deviceId| 设备ID  | query | false |integer  |    |
|deviceSn| 设备编码  | query | false |string  |    |
|id|   | query | false |integer  |    |
|installAddress| 安装地址  | query | false |string  |    |
|installDate| 安装日期  | query | false |string  |    |
|latitude| 纬度  | query | false |number  |    |
|longitude| 经度  | query | false |number  |    |
|picUrl| 图片  | query | false |string  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
# 设备度量

## 根据id删除设备度量


**接口说明**:



**接口地址**:`/devicemetric/delete/{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 根据id删除  | path | true |integer  |    |

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
## 查询设备度量列表


**接口说明**:



**接口地址**:`/devicemetric/findAll`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 保存设备度量


**接口说明**:



**接口地址**:`/devicemetric/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTime": "",
	"description": "",
	"equipmentType": "",
	"id": 0,
	"metric": "",
	"typeId": 0
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deviceMetric| deviceMetric  | body | true |device_metric  | device_metric   |

**schema属性说明**



**device_metric**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | body | false |date-time  |    |
|description| 设备类型说明  | body | false |string  |    |
|equipmentType| 设备类型  | body | false |string  |    |
|id|   | body | false |int32  |    |
|metric| 度量标准  | body | false |string  |    |
|typeId| 设备类型id  | body | false |int32  |    |

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
## 修改设备度量


**接口说明**:



**接口地址**:`/devicemetric/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
{
	"createTime": "",
	"description": "",
	"equipmentType": "",
	"id": 0,
	"metric": "",
	"typeId": 0
}
```


**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deviceMetric| deviceMetric  | body | true |device_metric  | device_metric   |

**schema属性说明**



**device_metric**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | body | false |date-time  |    |
|description| 设备类型说明  | body | false |string  |    |
|equipmentType| 设备类型  | body | false |string  |    |
|id|   | body | false |int32  |    |
|metric| 度量标准  | body | false |string  |    |
|typeId| 设备类型id  | body | false |int32  |    |

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
# 设备状态

## 根据id删除设备状态


**接口说明**:



**接口地址**:`/devicestatus/deleteDeviceStatusById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 根据id删除  | body | true |integer  |    |

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
## 查询设备状态列表


**接口说明**:



**接口地址**:`/devicestatus/deviceStatusList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 保存设备状态


**接口说明**:



**接口地址**:`/devicestatus/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deviceId| 設備id  | query | false |integer  |    |
|id|   | query | false |integer  |    |
|remarks| 备注  | query | false |string  |    |
|status| 设备状态  | query | false |integer  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
## 修改设备状态


**接口说明**:



**接口地址**:`/devicestatus/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deviceId| 設備id  | query | false |integer  |    |
|id|   | query | false |integer  |    |
|remarks| 备注  | query | false |string  |    |
|status| 设备状态  | query | false |integer  |    |
|updateTime| 更新时间  | query | false |string  |    |

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
# 设备用电信息

## 根据id删除设备用电信息


**接口说明**:



**接口地址**:`/devicepower/deleteDevicePowerById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| 根据id删除  | body | true |integer  |    |

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
## 设备用电信息列表


**接口说明**:



**接口地址**:`/devicepower/devicePowerList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 保存设备用电信息


**接口说明**:



**接口地址**:`/devicepower/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deviceId| 設備id  | query | false |integer  |    |
|fullCharge| 电池容量  | query | false |integer  |    |
|id|   | query | false |integer  |    |
|standardVoltage| 电池额定电压  | query | false |integer  |    |
|stateOfCharge| 剩余电量  | query | false |number  |    |
|updateTime| 更新时间  | query | false |string  |    |
|voltage| 当前电压  | query | false |integer  |    |

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
## 修改设备用电信息


**接口说明**:



**接口地址**:`/devicepower/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|deviceId| 設備id  | query | false |integer  |    |
|fullCharge| 电池容量  | query | false |integer  |    |
|id|   | query | false |integer  |    |
|standardVoltage| 电池额定电压  | query | false |integer  |    |
|stateOfCharge| 剩余电量  | query | false |number  |    |
|updateTime| 更新时间  | query | false |string  |    |
|voltage| 当前电压  | query | false |integer  |    |

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
# 设备类型

## 根据id删除设备类型


**接口说明**:



**接口地址**:`/devicetype/deleteDeviceTypeById{id}`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|id| id  | query | false |integer  |    |

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
## 设备类型列表


**接口说明**:



**接口地址**:`/devicetype/deviceTypeList`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|limit| 分页结束位置  |  | true |Integer  | Integer   |
|page| 分页起始位置  |  | true |Integer  | Integer   |
|params| params  | query | true |object  |    |

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
## 保存设备类型


**接口说明**:



**接口地址**:`/devicetype/save`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | query | false |string  |    |
|id|   | query | false |integer  |    |
|image| 设备点位类型图  | query | false |string  |    |
|name| 类型名称  | query | false |string  |    |
|type| 类型  | query | false |integer  |    |

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
## 修改设备类型


**接口说明**:



**接口地址**:`/devicetype/update`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|createTime| 创建时间  | query | false |string  |    |
|id|   | query | false |integer  |    |
|image| 设备点位类型图  | query | false |string  |    |
|name| 类型名称  | query | false |string  |    |
|type| 类型  | query | false |integer  |    |

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
