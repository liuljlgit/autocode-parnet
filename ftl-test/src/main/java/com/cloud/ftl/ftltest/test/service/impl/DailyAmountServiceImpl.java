package com.cloud.ftl.ftltest.test.service.impl;

import com.cloud.ftl.ftlbasic.exception.BusiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.List;
import org.springframework.util.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.stream.Collectors;
import com.cloud.ftl.ftlbasic.service.AbstractBaseService;
import org.springframework.data.redis.core.RedisTemplate;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import com.cloud.ftl.ftltest.test.dao.IDailyAmountDao;

/**
 * IDailyAmountService service实现类
 * @author lijun
 */
@Service("dailyAmountService")
public class DailyAmountServiceImpl extends AbstractBaseService<DailyAmount> implements IDailyAmountService {

    public DailyAmountServiceImpl(IDailyAmountDao dailyAmountDao,RedisTemplate<String,String> stringRedisTemplate){
        super(dailyAmountDao,stringRedisTemplate);
    }

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;
    @Autowired
    private IDailyAmountDao dailyAmountDao;

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
            dailyAmount.setDaId(selectMaxId());
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
                dailyAmount.setDaId(selectMaxId());
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
            dailyAmount.setDaId(selectMaxId());
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
                e.setDaId(selectMaxId());
                return e;
            }).collect(Collectors.toList());
            batchAddDailyAmount(addList);
        }
        if(!CollectionUtils.isEmpty(updateList)){
            batchUpdateDailyAmount(updateList,false);
        }
    }

}