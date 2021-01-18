package com.udp.nb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.udp.nb.entity.AirSwitchDO;
import com.udp.nb.entity.DeviceStatusDO;
import com.udp.nb.mapper.AirSwitchMapper;
import com.udp.nb.service.NettyService;
import com.udp.nb.util.MessageUtil;
import com.udp.nb.util.NBUtil;
import com.udp.nb.entity.NbClientVo;
import io.netty.channel.socket.DatagramPacket;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author
 * @email
 * @date 2019-08-19 23:31:53
 */
 
@Controller
@RequestMapping("/nb/airSwitch")
@CrossOrigin(origins = "*")
public class AirSwitchController {

	@Autowired
	private AirSwitchMapper airSwitchMapper;

    private static Logger logger = LoggerFactory.getLogger(AirSwitchController.class);

	/**
	 * 获取设备list(hostid、节点号、在线状态)
	 */
	@ResponseBody
	@RequestMapping("/getDevicelist")
	public HashMap<String, Object> getDevicelist(HttpServletRequest req){

		HashMap<String, Object> map=new HashMap<String, Object>();
		String hostid = req.getParameter("hostid");//设备id
		String nno = req.getParameter("nno");//节点号
		String offset = req.getParameter("offset");//起始位置
		String limit = req.getParameter("limit");//分页值
		String mac = req.getParameter("mac");//设备mac
		Map<String, Object> mapParam=new HashMap<String, Object>();
		mapParam.put("hostid",hostid);
		mapParam.put("nno",nno);
		mapParam.put("mac",mac);
		int ioffset=StringUtil.isBlank(offset)?0:Integer.parseInt(offset);
		int ilimit=StringUtil.isBlank(limit)?10:Integer.parseInt(limit);
		mapParam.put("offset",ioffset);
		mapParam.put("limit",ilimit);
		logger.info("getDevicelist:{}",JSON.toJSONString(mapParam));
		List<DeviceStatusDO> deviceStatusList=airSwitchMapper.getDeviceStatusList(mapParam);
		map.put("result",deviceStatusList);
		map.put("flag", "true");
		map.put("msg", "操作成功！");
		return map;
	}
	/**
	 * 获取设备list(hostid、节点号、在线状态)
	 */
	@ResponseBody
	@RequestMapping("/getDeviceStatus")
	public HashMap<String, Object> getDeviceStatus(HttpServletRequest req){

		HashMap<String, Object> map=new HashMap<String, Object>();
		String hostid = req.getParameter("hostid");//设备id
		String nno = req.getParameter("nno");//节点号
		String offset = req.getParameter("offset");//起始位置
		String limit = req.getParameter("limit");//分页值
		String mac = req.getParameter("mac");//设备mac
		Map<String, Object> mapParam=new HashMap<String, Object>();
		mapParam.put("hostid",hostid);
		mapParam.put("nno",nno);
		mapParam.put("mac",mac);
		int ioffset=StringUtil.isBlank(offset)?0:Integer.parseInt(offset);
		int ilimit=StringUtil.isBlank(limit)?10:Integer.parseInt(limit);
		mapParam.put("offset",ioffset);
		mapParam.put("limit",ilimit);
		List<DeviceStatusDO> deviceStatusList=airSwitchMapper.getDeviceStatusCurrentList(mapParam);
		List<AirSwitchDO> airSwitchList=airSwitchMapper.getAirSwitchCurrentList(mapParam);
		map.put("airSwitchList",airSwitchList);
		map.put("deviceStatusList",deviceStatusList);
		map.put("flag", "true");
		map.put("msg", "操作成功！");
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/deviceControl")
	public String nbWhite(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", "true");
		map.put("msg", "请求成功！");
		try {
			String hostid = req.getParameter("hostid");//设备id
			String nnos = req.getParameter("nnos");//节点号
			String type = req.getParameter("type");//操作类型(1:合闸，2：分闸，3:漏电测试，4:LED 测试)
			String deviceid = req.getParameter("deviceid");//设备id
			String mac = req.getParameter("mac");//设备id
			if(StringUtil.isBlank(mac)){
				map.put("flag", "false");
				map.put("msg", "设备mac信息不存在！");
				return JSON.toJSONString(map);
			}
			NbClientVo nbClientMap = NettyService.nbClientMap.get(mac);
			if (nbClientMap == null) {
				map.put("flag", "false");
				map.put("msg", "暂无该设备网络地址信息！");
			} else {
				/*if(NettyService.userSocketMap.containsKey(mac)){
					map.put("msg", "操作成功！");
					NettyService.userSocketMap.remove(mac);
					return JSON.toJSONString(map);
				}*/
				String cmdver="";
				if("1".equals(type)){
					cmdver="a1";
				}
				if("2".equals(type)){
					cmdver="a2";
				}
				if("3".equals(type)){
					cmdver="a3";
				}
				if("4".equals(type)){
					cmdver="a4";
				}
				String message= MessageUtil.getCommandA1andA4Message(hostid,cmdver,nnos);
                DatagramPacket packet=nbClientMap.getPacket();
                String ip = packet.sender().getHostString();
                String port = String.valueOf(packet.sender().getPort());
                String key = ip+":"+port;
				logger.info("发送控制消息{},{}的16进制请求数据,报文<-------------->:{}", cmdver,key,message);
				NettyService.sendNN0Map.put(mac,nnos);
				NBUtil.doSendMessageByKey(mac,message, nbClientMap.getCtx(), packet, true, ip, port);
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

		return JSON.toJSONString(map);
	}
}
