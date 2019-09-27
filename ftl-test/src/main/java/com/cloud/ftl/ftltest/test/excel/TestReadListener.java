package com.cloud.ftl.ftltest.test.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.cloud.ftl.ftltest.test.entity.DailyAmount;
import com.cloud.ftl.ftltest.test.service.inft.IDailyAmountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Liulj
 * @version v 0.1 2019/9/26 20:34
 */
@Slf4j
public class TestReadListener extends AnalysisEventListener<TestRead>  {

    //private static final Logger log = LoggerFactory.getLogger(TestReadListener.class);

  private IDailyAmountService dailyAmountService;

    private List<TestRead> list = Lists.newArrayList();

    public TestReadListener(IDailyAmountService dailyAmountService) {
        this.dailyAmountService = dailyAmountService;
    }

    @Override
    public void invoke(TestRead data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= 5) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        DailyAmount dailyAmount = new DailyAmount();
        dailyAmountService.save(dailyAmount);
        log.info("存储数据库成功！");
    }
}
