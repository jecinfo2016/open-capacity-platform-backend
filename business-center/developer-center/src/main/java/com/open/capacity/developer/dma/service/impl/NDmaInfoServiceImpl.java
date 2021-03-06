package com.open.capacity.developer.dma.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.developer.dma.dao.NDmaInfoDao;
import com.open.capacity.developer.dma.entity.NDmaInfo;
import com.open.capacity.developer.dma.service.NDmaInfoService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NDmaInfoServiceImpl implements NDmaInfoService {

    @Autowired
    private NDmaInfoDao nDmaInfoDao;

    /**
     * 添加
     *
     * @param nDmaInfo
     */
    public int save(NDmaInfo nDmaInfo) {
        NDmaInfo nDmaInfoLast = nDmaInfoDao.queryByParentIdLast(nDmaInfo.getParentId());
        if (null != nDmaInfoLast) {
            nDmaInfo.setSortNo(nDmaInfoLast.getSortNo() + 1);
        } else {
            nDmaInfo.setSortNo(1);
        }
        return nDmaInfoDao.save(nDmaInfo);
    }

    /**
     * 修改
     *
     * @param nDmaInfo
     */
    public int update(NDmaInfo nDmaInfo) {
        return nDmaInfoDao.update(nDmaInfo);
    }


    /**
     * 删除
     *
     * @param id
     */
    public int delete(Long id) {
        return nDmaInfoDao.delete(id);
    }


    /**
     * 列表
     *
     * @param params
     * @return
     */
    public PageResult<NDmaInfo> findAll(Map<String, Object> params) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page") != null && MapUtils.getInteger(params, "limit") != null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);

        List<NDmaInfo> list = nDmaInfoDao.findAll(params);
        PageInfo<NDmaInfo> pageInfo = new PageInfo(list);

        return PageResult.<NDmaInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public List<NDmaInfo> queryTreeList() {
        return nDmaInfoDao.queryTreeList(null);
    }

    @Override
    public int moveDma(NDmaInfo nDmaInfo) {
        return nDmaInfoDao.moveDmaById(nDmaInfo);
    }

}
