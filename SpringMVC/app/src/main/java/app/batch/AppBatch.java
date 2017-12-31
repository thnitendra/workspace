package app.batch;

import app.model.Mutex;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by nitendra.thakur on 2017/12/31.
 */
@Component
@Slf4j(topic = "APP_BATCH_LOG")
public class AppBatch {

    @Autowired
    @Qualifier("mongoTemplate")
    MongoOperations mongoOperation;

    public void execBatch() {

    }

    private synchronized Mutex acquireLockOnMonitor(String hostName) {
        Date fiveMinutesEarlier = DateUtils.addMinutes(new Date(), -5);

        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("hostName").is(null), Criteria.where("updateTime").lt(fiveMinutesEarlier.getTime()));
        Query query = new Query(criteria);

        Update update = new Update();
        update.set("hostName", hostName);
        update.set("updateTime", System.currentTimeMillis());

        Mutex acquired = mongoOperation.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Mutex.class);

        return acquired;
    }
}