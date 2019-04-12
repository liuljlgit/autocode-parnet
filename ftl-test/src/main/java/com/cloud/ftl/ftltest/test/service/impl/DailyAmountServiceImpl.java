package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftltest.test.dao.IDailyAmountDao;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl implements IDailyAmountService {

    private static final Logger logger = LoggerFactory.getLogger(DailyAmountServiceImpl.class);

    @Autowired
    private IDailyAmountDao dailyAmountDao;

    /**
     * 根据主键获取对象
     * @param daId
     * @return
     * @throws Exception
     */
    @Override
    public DailyAmount loadDailyAmountByKey(Long daId) throws Exception {
        if(Objects.isNull(daId)){
            throw new BusiException("请输入要获取的数据的ID");
        }
        DailyAmount dailyAmount = dailyAmountDao.loadDailyAmountByKey(daId);
        if(Objects.isNull(dailyAmount)){
            throw new BusiException("没有符合条件的记录！") ;
        }
        return dailyAmount;
    }

    /**
     * 普通查询获取单个结果
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public DailyAmount selectOneDailyAmount(DailyAmountQuery query) throws Exception {
        List<DailyAmount> list = findDailyAmountList(query);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 分页查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<DailyAmount> getDailyAmountPageList(DailyAmountQuery query) throws Exception {
        if(Objects.isNull(query.getPage()) || Objects.isNull(query.getPageSize())){
            throw new BusiException("page and pageSize can not be null");
        }
        Long total = dailyAmountDao.getTotalDailyAmount(query);
        Long totalPage = (long)Math.ceil((double)total / query.getPageSize());
        List<DailyAmount> dailyAmountList = findDailyAmountList(query);
        return new PageBean<>(totalPage,total,dailyAmountList);
    }


    /**
     * 查询列表
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<DailyAmount> findDailyAmountList(DailyAmountQuery query) throws Exception {
        if(Objects.isNull(query)){
            throw new BusiException("查询参数不能为空");
        }
        return dailyAmountDao.findDailyAmountList(query);
    }
}