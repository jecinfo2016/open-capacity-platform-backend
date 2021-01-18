package com.open.device.controller;

import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceInfo;
import com.open.device.model.DeviceType;
import com.open.device.model.EquipmentTsdbTag;
import com.open.device.model.EquipmentTsdbTask;
import com.open.device.service.TsdbDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.redisson.executor.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
@RestController
@RequestMapping("/tsdbDevice")
@Api("数据处理任务管理相关接口*")
public class TsdbDeviceController {

  @Autowired TsdbDeviceService tsdbDeviceService;

  /**
   * 查询所有的聚合器
   *
   * @return
   */
  @PostMapping("/getAggregators")
  @ApiOperation(value = "查询聚合器列表")
  public Result<List<String>> queryAggregators() {
    List<String> aggregators = tsdbDeviceService.queryAggregators();
    if (null==aggregators || aggregators.isEmpty()) {
      return Result.failed("未查询到聚合器信息。");
    }
    return Result.succeedWith(aggregators, 0, "查询成功");
  }

  /**
   * 查询设备上报的所有类型
   *
   * @return
   */
  @PostMapping("/getEquipmentTsdbTypes")
  @ApiOperation(value = "查询设备上报的所有类型")
  public Result<List<DeviceType>> queryDeviceTsdbType() {
    List<DeviceType> equipmentTsdbTypes = tsdbDeviceService.queryEquipmentType();
    if (equipmentTsdbTypes == null || equipmentTsdbTypes.isEmpty()) {
      return Result.failed("未查询到相关信息");
    }
    return Result.succeedWith(equipmentTsdbTypes, 0, "查询成功");
  }

  /**
   * 根据上报类型查询对应的统计字段
   *
   * @param deviceType:设备类型
   * @return
   */
  @PostMapping("/getEquipmentTsdbTags")
  @ApiOperation(value = "根据设备类型,查询对应的tags字段")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "deviceType", value = "设备类型", required = true, dataType = "Integer"),
  })
  public Result<List<EquipmentTsdbTag>> queryEquipmentTsdbTag(@RequestParam Integer deviceType) {
    if (deviceType == null) {
      return Result.failed("必传参数【deviceType】为空");
    }
    List<EquipmentTsdbTag> equipmentTsdbTags = tsdbDeviceService.queryEquipmentTsdbTag(deviceType);
    if (equipmentTsdbTags == null || equipmentTsdbTags.isEmpty()) {
      return Result.failed("未查询到相关信息");
    }
    return Result.succeedWith(equipmentTsdbTags, 0, "查询成功");
  }

  /**
   * 根据设备类型，查询设备列表
   *
   * @param deviceType
   * @return
   */
  @PostMapping("/getDeviceInfoList")
  @ApiOperation(value = "根据设备类型，查询设备列表")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "deviceType", value = "设备类型", required = true, dataType = "Integer"),
  })
  public Result<List<DeviceInfo>> queryDeviceInfoList(@RequestParam Integer deviceType) {
    if (deviceType == null) {
      return Result.failed("必传参数【deviceType】为空");
    }
    List<DeviceInfo> deviceInfos = tsdbDeviceService.queryDeviceListByType(deviceType);
    if (deviceInfos == null || deviceInfos.isEmpty()) {
      return Result.failed("未查询到相关信息");
    }
    return Result.succeedWith(deviceInfos, 0, "查询成功");
  }

  /**
   * 新增调度任务
   *
   * @param task
   * @return
   */
  @PostMapping("/addDeviceTask")
  @ApiOperation(value = "新增调度任务")
  public Result addDeviceTask(
      @RequestBody @Valid EquipmentTsdbTask task, BindingResult bindingResult) {
    for (ObjectError error : bindingResult.getAllErrors()) {
      return Result.failed(error.getDefaultMessage());
    }
    String cronStr = task.getCronStr();

    if (!CronExpression.isValidExpression(cronStr)) {
      return Result.failed("cron表达式不符合规则");
    }
    int row = tsdbDeviceService.insertDeviceTask(task);
    if (row > 0) {
      return Result.succeed("新增任务调度成功");
    }
    return Result.failed("新增任务调度失败");
  }

  /**
   * 按条件查询任务调度信息
   *
   * @param params ：deviceType，aggregator，flag
   * @return
   */
  @PostMapping("/queryDeviceTaskList")
  @ApiOperation(value = "查询任务调度信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "deviceType", value = "设备类型", required = false, dataType = "Integer"),
          @ApiImplicitParam(name = "aggregator", value = "聚合方式", required = false, dataType = "String"),
          @ApiImplicitParam(name = "flag", value = "调度状态", required = false, dataType = "String"),
          @ApiImplicitParam(name = "clientId", value = "应用ID", required = true, dataType = "String"),
  })
  public Result queryDeviceTaskList(@RequestParam Map params) {
    if (params == null || StringUtils.isEmpty(String.valueOf(params.get("clientId")))) {
      return Result.failed("clientId不能为空");
    }
    List<Map<String, String>> taskList = tsdbDeviceService.queryTaskList(params);
    if (taskList == null || taskList.isEmpty()) {
      return Result.succeed("未查询到相关信息");
    }
    return Result.succeedWith(taskList, 0, "查询成功");
  }

  /**
   * 删除任务调度记录
   *
   * @param id
   * @return
   */
  @PostMapping("/deleteTaskInfo")
  @ApiOperation(value = "删除任务调度记录")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "任务ID", required = true, dataType = "Integer"),
          @ApiImplicitParam(name = "jobInfoId", value = "执行器主键", required = true, dataType = "Integer")
  })
  public Result deleteTaskInfo(@RequestParam Integer id, @RequestParam Integer jobInfoId) {
    if (id == null) {
      return Result.failed("必传参数【id】不能为空");
    }
    if (jobInfoId == null) {
      return Result.failed("必传参数【jobInfoId】不能为空");
    }
    int row = tsdbDeviceService.deleteTaskInfo(id, jobInfoId);
    if (row > 0) {
      return Result.succeed("删除任务调度记录成功");
    }
    return Result.failed("操作失败,此调度记录不存在");
  }

  /**
   * 更新任务调度记录
   *
   * @param task
   * @return
   */
  @PostMapping("/updateTaskInfo")
  @ApiOperation(value = "更新任务调度记录")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "任务ID", required = true, dataType = "Integer"),
          @ApiImplicitParam(name = "jobInfoId", value = "执行器主键", required = true, dataType = "Integer")
  })
  public Result updateTaskInfo(
      @RequestBody @Valid EquipmentTsdbTask task, BindingResult bindingResult) {
    // 参数校验
    for (ObjectError error : bindingResult.getAllErrors()) {
      return Result.failed(error.getDefaultMessage());
    }
    if (task.getJobInfoId() == null) {
      return Result.failed("必传参数【jobInfoId】不能为空");
    }
    if (task.getId() == null) {
      return Result.failed("必传参数【id】不能为空");
    }
    // 定时调度表达式
    String cronStr = task.getCronStr();
    if (!CronExpression.isValidExpression(cronStr)) {
      return Result.failed("cron表达式不符合规则");
    }
    int row = tsdbDeviceService.updateTaskInfo(task);
    if (row > 0) {
      return Result.succeed("更新成功");
    }
    return Result.failed("操作失败,此调度记录不存在");
  }

  /**
   * 更新任务调度状态,开启/关闭任务执行
   *
   * @param taskId
   * @param flag
   * @return
   */
  @PostMapping("/updateTaskFlag")
  @ApiOperation(value = "更新任务调度状态，开启/关闭任务执行")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "Integer"),
          @ApiImplicitParam(name = "flag", value = "状态", required = true, dataType = "String")
  })
  public Result updateTaskFlag(Integer taskId, String flag) {
    if (taskId == null) {
      return Result.failed("必传参数【taskId】不能为空");
    }
    if (StringUtils.isEmpty(flag)) {
      return Result.failed("必传参数【flag】不能为空");
    }
    int row = tsdbDeviceService.updateTaskFlag(taskId,flag);
    if (row > 0) {
      return Result.succeed("更新任务调度状态成功");
    }
    return Result.failed("更新任务调度状态失败。");
  }
}
