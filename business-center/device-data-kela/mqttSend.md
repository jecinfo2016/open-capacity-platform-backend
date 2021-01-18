## mqtt设备上报


**mqtt协议**: tcp://61.164.218.155:1613

**username**: jecinfo

**password**: jecinfo2016

**接口地址**:`/public/mqtt/send`


**请求方式**：`post`



**Message格式**：

| 参数名称         | 类型      |  说明   |
| ------------ |-------|----|
|id| Integer |自增id  |
|title| String |topic  |
|content| String |data数据  |
|type| Integer |类型  |
|sendTime| Date |发送时间  |
|sendUser| Integer |发送人id  |
|appId| Integer |开发者平台申请的应用id|


**请求参数**：

| 参数名称         | 是否必须      |  类型   |
| ------------ |-------|----|
|topic| true |String  |
|data| true |String  |


**响应数据**:

```json
{
	"data": {},
	"status": 0,
	"description": ""
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|data|   |object  |    |
|status|   |int32  |    |
|description|   |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 400 | parameter error  ||

