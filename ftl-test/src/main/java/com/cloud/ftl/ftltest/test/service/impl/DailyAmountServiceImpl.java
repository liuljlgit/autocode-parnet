package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.util.CollectionUtils;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.dao.IDailyAmountDao;
import com.cloud.ftl.ftltest.test.query.DailyAmountQuery;
import com.cloud.ftl.ftltest.test.cache.inft.IDailyAmountRedis;
import com.cloud.ftl.ftltest.test.webentity.resp.DailyAmountResp;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl implements IDailyAmountService {

    private static final Logger logger = LoggerFactory.getLogger(DailyAmountServiceImpl.class);

    @Autowired
    private IDailyAmountDao dailyAmountDao;
    @Autowired
    private IDailyAmountRedis dailyAmountRedis;

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
    public PageBean<DailyAmountResp> getDailyAmountPageList(DailyAmountQuery query) throws Exception {
        if(Objects.isNull(query.getPage()) || Objects.isNull(query.getPageSize())){
            throw new BusiException("page and pageSize can not be null");
        }
        Long total = dailyAmountDao.getTotalDailyAmount(query);
        Long totalPage = (long)Math.ceil((double)total / query.getPageSize());
        List<DailyAmountResp> dailyAmountList = findDailyAmountList(query).stream().map(DailyAmountResp::new).collect(Collectors.toList());
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

    /**
     * 新增对象
     * @param dailyAmount
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addDailyAmount(DailyAmount dailyAmount) throws Exception {
        if(Objects.isNull(dailyAmount)){
            return 0;
        }
        if(Objects.isNull(dailyAmount.getDaId())){
            dailyAmount.setDaId(dailyAmountRedis.getDailyAmountId());
        }
        return dailyAmountDao.addDailyAmount(dailyAmount);
    }

    /**
     * 批量新增对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddDailyAmount(List<DailyAmount> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (DailyAmount dailyAmount : list) {
            if(Objects.isNull(dailyAmount.getDaId())){
                dailyAmount.setDaId(dailyAmountRedis.getDailyAmountId());
            }
        }
        dailyAmountDao.batchAddDailyAmount(list);
    }

    /**
     * 更新对象
     * @param dailyAmount
     * @param fullUpdate
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateDailyAmount(DailyAmount dailyAmount,Boolean fullUpdate) throws Exception {
        if(Objects.isNull(dailyAmount)){
            return 0;
        }
        if(Objects.isNull(dailyAmount.getDaId())){
            throw new BusiException("主键不能为空");
        }
        Integer result;
        if(fullUpdate){
            result = dailyAmountDao.fullUpdateDailyAmount(dailyAmount);
        } else {
            result = dailyAmountDao.updateDailyAmount(dailyAmount);
        }
        return result;
    }

    /**
     * 批量更新
     * @param list
     * @param fullUpdate
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateDailyAmount(List<DailyAmount> list,Boolean fullUpdate) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        for (DailyAmount dailyAmount : list) {
            if(Objects.isNull(dailyAmount.getDaId())){
                throw new BusiException("主键不能为空");
            }
        }
        if(fullUpdate){
            dailyAmountDao.batchFullUpdateDailyAmount(list);
        } else {
            dailyAmountDao.batchUpdateDailyAmount(list);
        }
    }

    /**
     * 删除对象
     * @param daId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteDailyAmount(Long daId) throws Exception {
        if(Objects.isNull(daId)){
            throw new BusiException("主键不能为空");
        }
        return dailyAmountDao.deleteDailyAmount(daId);
    }

    /**
     * 批量删除对象
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteDailyAmount(List<Long> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        dailyAmountDao.batchDeleteDailyAmount(list);
    }

    /**
     * 根据ID列表从数据库中查询列表
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public List<DailyAmount> findDailyAmountByIdList(List<Long> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return Collections.EMPTY_LIST;
        }
        return dailyAmountDao.findDailyAmountByIdList(list);
    }

    /**
     * 保存记录
     * @param dailyAmount
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDailyAmount(DailyAmount dailyAmount) throws Exception {
        if(Objects.isNull(dailyAmount)){
           return ;
        }
        if(Objects.isNull(dailyAmount.getDaId())){
            dailyAmount.setDaId(dailyAmountRedis.getDailyAmountId());
            addDailyAmount(dailyAmount);
        }else{
            updateDailyAmount(dailyAmount,false);
        }
    }

    /**
     * 批量保存记录
     * @param list
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDailyAmountList(List<DailyAmount> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        List<DailyAmount> addList = list.stream().filter(e -> Objects.isNull(e.getDaId())).collect(Collectors.toList());
        List<DailyAmount> updateList = list.stream().filter(e -> Objects.nonNull(e.getDaId())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(addList)){
            addList = addList.stream().map(e->{
                e.setDaId(dailyAmountRedis.getDailyAmountId());
                return e;
            }).collect(Collectors.toList());
            batchAddDailyAmount(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdateDailyAmount(updateList,false);
        }
    }

}