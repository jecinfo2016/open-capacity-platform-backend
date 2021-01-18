
**Api Documentation**


**简介**：Api Documentation

**HOST**:61.164.218.155:3591


**Version**:1.0

## getDevicelist

## 查询应用设备列表(hostid、节点号、在线状态)


**接口说明**:


**接口地址**:`/nb/airSwitch/getDevicelist`


**请求方式**：`get`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|req| 请求对象  |  | true |HttpServletRequest  | HttpServletRequest   |


**响应数据**:

```json
{
    "result": [
        {
            "id": 26556518,
            "deviceId": null,
            "status": 1,
            "temperature": 43.40,
            "current": 0.00,
            "voltage": 387.00,
            "power": 0.00,
            "recordtime": 1592385041000,
            "hostid": 16,
            "nno": "03",
            "mac": "98cc4d205422",
            "type": "84",
            "openstatus": 1,
            "alarmstatus": 0
        }
    ],
    "msg": "操作成功！",
    "flag": "true"
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|hostid|设备id   |string  |    |
|nno|  节点号 |string  |    |
|offset|  起始位置 |string  |    |
|limit|  分页值 |string  |    |
|mac|  设备mac |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |PageResult|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||

## 获取设备list(hostid、节点号、在线状态)


**接口说明**:



**接口地址**:`/nb/airSwitch/getDeviceStatus`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|req| 请求对象  |  | true |HttpServletRequest  | HttpServletRequest   |

**响应数据**:

```json
{
    "msg": "操作成功！",
    "flag": "true",
    "airSwitchList": [
        {
            "alarmId": 459156,
            "type": 1,
            "deviceId": null,
            "alarmType": 1,
            "alarmTime": 1579703697000,
            "hostid": 16,
            "nno": "03",
            "mac": null
        }
    ],
    "deviceStatusList": [
        {
            "id": 22750286,
            "deviceId": 17,
            "status": 1,
            "temperature": 34.70,
            "current": 0.00,
            "voltage": 222.00,
            "power": 0.00,
            "recordtime": 1579052863000,
            "hostid": 16,
            "nno": "03",
            "mac": "98cc4d2053e5",
            "type": "80",
            "openstatus": 1,
            "alarmstatus": 0
        }
    ]
}
```

**响应参数说明**:


| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|hostid|设备id   |string  |    |
|nno|  节点号 |string  |    |
|offset|  起始位置 |string  |    |
|limit|  分页值 |string  |    |
|mac|  设备mac |string  |    |





**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||

## 应用控制


**接口说明**:



**接口地址**:`/nb/airSwitch/deviceControl`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
```
**schema属性说明**



| 参数名称         | 说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|hostid|设备id   |string  |    |
|nno|  节点号 |string  |    |
|type|  操作类型 |string  |  1:合闸，2：分闸，3:漏电测试，4:LED 测试 |
|deviceid|  设备id |string  |    |
|mac|  设备mac |string  |    |



**响应状态码说明**:


| 状态码         | 说明                             |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |Result|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||